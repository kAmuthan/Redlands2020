import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class hw2 {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnBuild;
	private JLabel lblNewLabel;
	private String result;

	public static void main(String[] args) { //launcher
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw2 window = new hw2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public hw2() {
		initialize();
		createEvents();
	}

	public void initialize() { //create Jframe
		
		result = "";
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scrabble App");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(179, 6, 89, 16);
		frame.getContentPane().add(lblNewLabel);
		
		btnBuild = new JButton("Show output");
		btnBuild.setBounds(179, 93, 117, 29);
		frame.getContentPane().add(btnBuild);
		
		JLabel lblNewLabel_1 = new JLabel("Input 4 letters here:");
		lblNewLabel_1.setBounds(6, 60, 135, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(143, 55, 284, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 157, 395, 100);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
		
	public void createEvents() { //invokes button creation and use
		btnBuild.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});

	}
	
	public void buildOutput() { //checks text for items we need
		String text = textField.getText(); 
		int len = text.length(); 
		if (len >4 || text.contains("1")|| text.contains("2")|| text.contains("3")|| text.contains("4")||
				text.contains("5")|| text.contains("6")|| text.contains("7")|| text.contains("8")
				|| text.contains("9")) {
			if (len > 4) {
				textArea.setText("you entered more then four letters");
			}else{
				textArea.setText("you entered a number into your text. that is wrong.");
			}
		}else{ //if everything safe, permute
			permute(text, 0,len-1);
			textArea.setText(result);
		}
	}
	
	public void permute (String str, int left, int right) { //permutation function
		if (left==right) { //checks if end pieces are same
			result+=str; 
			result+=" "; 
		}else{
			for (int i = left; i<=right; i++) { //shuffles to show all possible cases
				str = swap(str,left,i); //calls SWAP function
				permute(str,left+1, right); 
				str = swap(str,left,i);
			}
		}
	}	
	public String swap (String str, int i, int j) {
		char temp;
		char[] charArray = str.toCharArray();
		temp = charArray[i];
		charArray[i]=charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
}