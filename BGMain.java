import java.util.Scanner;

public class BGMain {

	public static void main(String[] args) {


		
		Scanner in = new Scanner (System.in);

		System.out.println("Put your first number here!");
		String r = in.nextLine();
		System.out.println("Put your second number here!");
		String p = in.nextLine();
		System.out.println("Your numbers are " + r + " , " + p);
		
		BigNumber tester = new BigNumber(r);
		BigNumber tester2 = new BigNumber(p);
		
		System.out.println("Let's try adding them!");
		tester.add(tester2);
		tester.printNumber();
	}

}
