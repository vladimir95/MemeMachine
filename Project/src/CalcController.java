
public class CalcController {

	private CalcView view;
	private CalcModel model; 
	
	
	public CalcController(){
		
		//view = new CalcView(this); --> after we're done with View
		view = new CalcView();
		model = new CalcModel();
		//view.setVisible(true); --> Option of launching the view from Controller. 
		// I guess we could do it this way, but only when the View is fully implemented. 
	}
	/*
	 * Implement all the Controller methods. In View, MyButtonAdapter has all the methods
	 * that Controller needs from the View. 
	 * 
	 * Confirm with MikePod9 how model operates with inputs. 
	 * 
	 * Add text displaying methods in View if needed, use Model to perform actions.  
	 * 
	 * 
	 */
	
	/*
	 * Takes an input and outputs it on the display. Edit this if it's wrong. 
	 * 
	 * Check whether setDisplayText is implemented in a right way. Edit, if needed.
	 */
	public void input(){
		
		view.setDisplayText(model.getInputValue());   
	}
	/*
	 * 
	 * Check whether setHIstoryText is implemented in a right way. Edit, if needed. 
	 */
	public void sum(){ 		
		
		model.sum();
		view.setHistoryText(model.getHistoryValue()); 
	}
	
	
	
	
}
