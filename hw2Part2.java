
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class hw2Part2 {

	private JFrame frame;
	private JTextField inputPassword;
	private JButton btnBuild;
	private JLabel passwordLabel;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw2Part2 window = new hw2Part2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public hw2Part2() {
		initialize();
		createEvents();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel title = new JLabel("Password Strength Detector");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(148, 6, 198, 16);
		frame.getContentPane().add(title);
		passwordLabel = new JLabel("Input Password Here:");
		passwordLabel.setBounds(15, 81, 141, 16);
		frame.getContentPane().add(passwordLabel);
		inputPassword = new JTextField();
		inputPassword.setBounds(168, 76, 232, 26);
		frame.getContentPane().add(inputPassword);
		inputPassword.setColumns(10);
		btnBuild = new JButton("DETECT!");
		btnBuild.setBounds(168, 128, 117, 29);
		frame.getContentPane().add(btnBuild);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 191, 389, 53);
		frame.getContentPane().add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	public void createEvents() { //create button
		btnBuild.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				buildOutput();
			}
		});

	}

	public void buildOutput() { //checking password
		String password = inputPassword.getText();
		String str2 = "Password: " + password;
		int count = 1;
		int max = 1;
		for (int i = 0; i<password.length()-1; i++) { //check for repeating consecutives
			if (password.charAt(i) == password.charAt(i+1)) {
				max = count;
				count++;
			}	
		}
		if (password.length() < 7 || password.length() > 12) {
			textArea.setText("ERROR INVALID PASSWORD LENGTH!");
		}
		else if (password.contains(" ")){
			textArea.setText("NO SPACES ALLOWED IN THE PASSWORD");
		}
		else {
			String output = "";
			if (max <= 2) {
				output = "Output:  The largest block in the password is "+ max + ". This is a decent password.";
			}
			else {
				int temp = 2;
				int diff = max - temp; //in case password too small
				output = "Output:  The largest block in the password is "+ max + ". This password can be made stronger by reducing this block by " + diff+ ".";
			}
			textArea.setText(str2 + "\n" + output);
		}
	}
}