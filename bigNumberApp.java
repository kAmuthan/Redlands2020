import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class bigNumberApp {

	private JFrame frame;
	private JTextField textFieldCount;
	private JTextField textFieldText;
	private JButton btnBuild;
	private JTextArea textAreaOutput;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bigNumberApp window = new bigNumberApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public bigNumberApp() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter ONLY positive numbers");
		lblNewLabel.setBounds(118, 11, 290, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(35, 66, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textFieldCount = new JTextField();
		textFieldCount.setBounds(76, 63, 332, 20);
		frame.getContentPane().add(textFieldCount);
		textFieldCount.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Y");
		lblNewLabel_2.setBounds(35, 104, 96, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textFieldText = new JTextField();
		textFieldText.setBounds(76, 101, 332, 20);
		frame.getContentPane().add(textFieldText);
		textFieldText.setColumns(10);
		
		btnBuild = new JButton("Add");
		btnBuild.setBounds(139, 160, 122, 23);
		frame.getContentPane().add(btnBuild);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 184, 403, 66);
		frame.getContentPane().add(scrollPane);
		
		textAreaOutput = new JTextArea();
		scrollPane.setViewportView(textAreaOutput);
	}
	
	
	private void createEvents() {
		btnBuild.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	private void buildOutput() {
		//Task 1: get count
		BigNumber X = new BigNumber(textFieldCount.getText());
		BigNumber Y = new BigNumber(textFieldText.getText());
		X.add(Y);
		textAreaOutput.setText(X.stringNumber());
		
	}
}
