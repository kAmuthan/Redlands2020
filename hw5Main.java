
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class hw5Main {

	public JFrame frame;
	public JButton btnBuild;
	public LinkedList list;
	public File inputFile;
	public Scanner fileScanner; // scans the file
	public JTextField readFileTextInput;
	public String temp = "";
	public ArrayList<Integer> values;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	public String readfromInput = "";
	private JScrollPane scrollPane_1;
	private JTextArea output_text;
	public JButton open;
	public JFileChooser fc;
	private JButton btnFileOpener;
	public String filePath = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw5Main window = new hw5Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public hw5Main() {
		initialize();
		createEvents();
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("mean & standard deviation");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 6, 173, 16);
		frame.getContentPane().add(titleLabel);

		JLabel inputFileLabel = new JLabel("input from file:");
		inputFileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		inputFileLabel.setBounds(6, 43, 106, 16);
		frame.getContentPane().add(inputFileLabel);

		btnBuild = new JButton("calculate");
		btnBuild.setBounds(142, 112, 117, 29);
		frame.getContentPane().add(btnBuild);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 43, 230, 61);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea(readfromInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 153, 341, 104);
		frame.getContentPane().add(scrollPane_1);

		output_text = new JTextArea();
		scrollPane_1.setViewportView(output_text);

		btnFileOpener = new JButton("select a file");
		btnFileOpener.setBounds(-5, 61, 117, 29);
		frame.getContentPane().add(btnFileOpener);

		list = new LinkedList();
		values = new ArrayList<Integer>();

	}

	public void createEvents() {

		btnFileOpener.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				filePath = chooseFile(); 
				if (filePath.equals("cancelled")) {
					textArea.setText("cancel button selected");
					output_text.setText("cancel button selected");
				}else{
					System.out.println("File path: " + filePath); //FILE SCANNING
					try {
						String readfromInput = "";
						inputFile = new File(filePath);
						fileScanner = new Scanner(inputFile); //checks input file
						readfromInput = readFile();
						
						// FOR EMPTY FILES
						
						if (readfromInput.equals("") || readfromInput.equals(" ")) {
							textArea.setText(readfromInput);
							output_text.setText("Empty file. Clicking 'calculate' will throw an error. ");
							return;
							}else{
							textArea.setText(readfromInput);
						}
					} catch (FileNotFoundException a) {
						System.out.println("Error - This file could not be found.");
					} finally {
						fileScanner.close();
					}
				}
			}
		});
		btnBuild.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				buildOutput();
			}
		});
	}

	public void buildOutput() {
		assert list.count()>0: "INVALID INPUT. CAN'T CALCULATE AVERAGE AND STANDARD DEVIATION!";
		if (!temp.equals("invalid")) {
				int sum = list.LinkedListSum(); //sum function
				int avg = sum / list.count();
				double standardDeviation = list.standardDeviation(); //standard deviation
				output_text.setLineWrap(true); 
				output_text.setWrapStyleWord(true);
				output_text.setText("values: " + list.displayFormatted() + "\nmean: " + avg + "\n" + "standard deviation: "
						+ standardDeviation);
				return;
		}else{
			output_text.setText("invalid input");
			assert false: "CAN'T CALCULATE AN INVALID INPUT"; //if bad input
			return;
		}
	}
	
	public String readFile() { // method for reading the file
		String s = ""; 
		String z = ""; 

		while (fileScanner.hasNext()) { 
			z = fileScanner.next();
			if (isNumeric(z)) {
				int element = Integer.parseInt(z); //parses input
				values.add(element);
				list.addNode(element); 
				s = s + z + "\n"; //function to put together
			} else {
				temp = "invalid"; 
				s = s + z + "\n";
			}
		}
		return s;
	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum); //d unused, only for try/catch
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public String chooseFile() {

		// file chooser
		open = new JButton();
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new java.io.File("/Users/andrewyip/Desktop"));
		fc.setDialogTitle("Select a File: ");
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath(); // once you click open, this gets returned
		}else{
			fc.hide();
			return "cancelled";
		}
	}
}