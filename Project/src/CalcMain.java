
public class CalcMain {

	public static void main(String[] args) 
	{
		//Main app in which the controller will be instantiated
		CalcModel model = new CalcModel();
		
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("2");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("1");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("3");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.sum();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		//model.sum();
		//System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("4");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("1");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.divide();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.subtract();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.multiply();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		//model.sum();
		//System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
		/*model.numericButton("2");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton(".");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.changeSign();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.changeSign();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.pi();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.sine();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("1");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.sum();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("1");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
		/*model.clear();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.divide();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton("2");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
		/*model.numericButton("2");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.changeSign();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.pi();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.cosine();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
		/*model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.factorial();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.undo();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
		/*model.numericButton("5");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton(".");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.numericButton(".");
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());
		model.enter();
		System.out.println("History: " + model.getHistoryValue() + "\t" +  "Value: " + model.getInputValue());*/
	}

}
