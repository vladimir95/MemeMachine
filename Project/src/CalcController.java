
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
	/**
	 * Implement all the Controller methods. In View, MyButtonAdapter has all the methods
	 * that Controller needs from the View. 
	 * 
	 * Add text displaying methods in View if needed, use Model to perform actions.  
	 * 
	 * 
	**/
	public void sum(){ 		//Confirm with MikePod9 how model operates with inputs.
		
		model.sum();
		//view.setText();  <-- this method has to be implemented in View.
	}
	
	
	
}
