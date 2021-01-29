import java.util.ArrayList;


public class BigNumber {

	private ArrayList<String> temp =  new ArrayList<String>();
	private ArrayList<Integer> bigList =  new ArrayList<Integer>();
	//  holders for the number's value
	
	public BigNumber (String input) {
		//separates the string into each number, still as a string value
		for(int i =0; i<input.length()-1; i++) {
			temp.add(input.substring(i, i+1));
		}
		temp.add(input.substring(input.length()-1));
		
		//turns the string array into an Integer array which we'll use
		for(int i=0; i<temp.size(); i++) {
			//System.out.println(i);
			bigList.add((Integer.parseInt(temp.get(i))));
		}
	}
	
	public ArrayList<Integer> getNumber() {
		return bigList;
	}
	
	public void printNumber() { //prints as a single number with no spaces or commas
		for(int i =0; i<bigList.size();i++) {
			System.out.print(bigList.get(i));
		}
	}
	
	public String stringNumber() {
		String temp = "";
		for(int i =0; i<bigList.size();i++) {
			temp+=bigList.get(i);
		}
		return temp;
	}
	
	
	public int getSize() {
		int size = 0;
		size = bigList.size();
		return size;
	}
	
	
	public void add(BigNumber n) {
		boolean carry = false; //temporary value equivalent to 0 or 1 (if true)
		BigNumber bigger; //temporary values to compare which number is larger/smaller
		BigNumber smaller;
		ArrayList<Integer> big;
		ArrayList<Integer> small;
		boolean compare = false; //boolean to check which is larger
		int k = 1;
		if(this.getSize() < n.getSize()) {
			compare = true;
		}
		
		if(compare) { //assigning which number is larger to make the adding process easier
			bigger = n;
			smaller = this;
		}else {
			bigger = this;
			smaller = n;
		}
		big = bigger.getNumber();
		small = smaller.getNumber();
		
		//process ofadding each number separately and incrementing if the carry value is true
		for(int i = small.size()-1;i>=0;i--) {
			int holder = small.get(i)+big.get(big.size()-k);
			if(carry) {
				holder++;
			}
			
			if((holder/10)>0) {
				carry = true;
			}else {
				carry = false;
			}
			holder = holder%10;
			big.set(big.size()-k, holder);
			k++;
		}
		
		bigList = big;
	}
}
