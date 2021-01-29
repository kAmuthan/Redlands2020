
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class hw6 {

	//variables to filter by

	public static int totalLines = 0;
	public static int countComments = 0;
	public static int countBlankLines = 0;
	public static int finalLineCount = 0;
	public static int forLoopcount = 0;
	public static int whileLoopCount = 0;
	public static int ifStatementCount = 0;

	public static void main(String[] args) {
		// TASK 1: SELECT THE FILE AND STORE INTO STRING
		String filePath = chooseFile();
		// TASK 2: RUN TRY CATCH STATEMENT
		if (!filePath.equals("selected cancel")) {
			System.out.println("File path: " + filePath);
			LineNumberReader reader = null;
			try {
				reader = new LineNumberReader(new FileReader(new File(filePath))); //start reading file
				String str;
				while ((str = reader.readLine()) != null) { //loop until end of file
					str = str.replaceAll("\\s+", ""); //remove white space
					totalLines++; 
					if (str.equals("")) {
						countBlankLines++;
					}
					if ((str.length() >= 2) && (str.startsWith("//"))) { countComments++;}
					if (str.contains("for")) { forLoopcount++; }
					if (str.contains("while")) { whileLoopCount++; }
					if (str.contains("if")) { ifStatementCount++; }

					else if (str.contains("/*")) {
						countComments++;
						while (((str = reader.readLine()) != null) && !(str.endsWith("*/"))) { //check for astericks comments
							totalLines++;
							countComments++;
						}
						totalLines++;
						countComments++;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			System.out.println("Total number of lines: " + totalLines); //output code
			System.out.println("Number of lines with blanks: " + countBlankLines);
			System.out.println("Number of lines with comments: " + countComments);
			finalLineCount = totalLines - countBlankLines - countComments;
			System.out.println("Lines with 'for' loops in it: " + forLoopcount);
			System.out.println("Lines with 'while' loops in it: " + whileLoopCount);
			System.out.println("Lines with 'if' statements in it: " + ifStatementCount);
			System.out.println("Total number of lines without comments and blanks: " + finalLineCount);

		}else{
			System.out.println("PROCESS CANCELED");
		}
	}

	public static String chooseFile() { //select which file to take
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JAVA FILES", "java", "java");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new java.io.File("/Users/andrewyip/eclipse-workspace/"));
		fc.setDialogTitle("Select a File: ");
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath(); // once you click open, this gets returned
		} else {
			fc.hide();
			return "selected cancel";
		}
	}
}