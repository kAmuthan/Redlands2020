import java.util.ArrayList;

public class SalesSlip{
	
	public ArrayList<String> items;
	public ArrayList<Double> cost;
	public ArrayList<Integer> quantity;
	public String costString;
	public String str = "";
	
	public SalesSlip() {
		items = new ArrayList <String>();
		cost = new ArrayList<Double>();
		quantity = new ArrayList<Integer>();
	}
	
	public void addItem (String item, double cost1, int amtItem) { 
		items.add(item);
		cost.add(cost1);
		costString = String.format("%.2f", cost.get(cost.size()-1)); //given calculation
		quantity.add(amtItem);
	}
	
	public String total () { //find the total sale
		double sum = 0;
		for (int i = 0; i<cost.size(); i++) {
			sum = sum + (cost.get(i)*quantity.get(i));
		}
		String formatSum = String.format("%.2f", sum); //reformat to fit dollar value
		String result = "$"+formatSum;
		return result;
	}
	
	public String display () {
		str = str +  items.get(items.size()-1) + "                $" +costString + "               " + quantity.get(quantity.size()-1) + " \n";
		return str;
	}
}