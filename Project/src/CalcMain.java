import javax.sound.midi.ControllerEventListener;

public class CalcMain {

	public static void main(String[] args) 
	{
		//Main app in which the controller will be instantiated
		CalcController controller = new CalcController(); 
	
		controller.numericButton("2");
		controller.factorial();
		controller.numericButton(".");
		controller.numericButton("5");
		controller.numericButton(".");
		controller.numericButton("5");
		controller.pi();
		controller.sine();
		controller.cosine();
		controller.numericButton("3");
		controller.multiply();
		controller.numericButton("3");
		controller.sum();
	}
}
