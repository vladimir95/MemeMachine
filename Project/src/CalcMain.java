import javax.sound.midi.ControllerEventListener;

public class CalcMain {

	public static void main(String[] args) 
	{
		//Main app in which the controller will be instantiated
		CalcController controller = new CalcController(); 
	
		controller.numericButton("2");
		controller.numericButton(".");
		controller.numericButton("5");
		controller.numericButton(".");
		controller.numericButton("5");
		controller.enter();
		controller.numericButton("3");
		controller.multiply();
	}
}
