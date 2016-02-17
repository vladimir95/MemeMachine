import javax.sound.midi.ControllerEventListener;

public class CalcMain {

	public static void main(String[] args) 
	{
		//Main app in which the controller will be instantiated
		CalcController controller = new CalcController(); 
	
		controller.numericButton("2");
		controller.enter();
		controller.undo();
		controller.undo();
		controller.undo();
	}
}
