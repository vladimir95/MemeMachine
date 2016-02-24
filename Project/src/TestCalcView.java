import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TestCalcView {
	private CalcView viewTest;
	@Before 
	public void setUp() throws Exception
	{
		CalcController theController = new CalcController();
		viewTest = new CalcView(theController);
	}

	@Test
	public void testSetDisplayText() {
		viewTest.setDisplayText("");
		assertEquals("",  viewTest.display.getText());
		viewTest.setDisplayText("abc");
		assertEquals("abc",  viewTest.display.getText());
		viewTest.setDisplayText("");
		assertEquals("",  viewTest.display.getText());
	}

	@Test
	public void testSetHistoryText() {
		viewTest.setHistoryText("");
		assertEquals("",  viewTest.historyDisplay.getText());
		viewTest.setHistoryText("abc");
		assertEquals("abc",  viewTest.historyDisplay.getText());
		viewTest.setHistoryText("");
		assertEquals("",  viewTest.historyDisplay.getText());
	}

	@Test
	public void testBetterViewDesign() {
		viewTest.betterViewDesign();
		assertEquals(Color.BLACK,  viewTest.panel2.getBackground());
		assertEquals(Color.BLACK,  viewTest.historyDisplay.getBackground());
		assertEquals(Color.WHITE,  viewTest.historyDisplay.getForeground());
		assertEquals(Color.BLACK,  viewTest.display.getBackground());
		assertEquals(Color.WHITE,  viewTest.display.getForeground());
		assertEquals(null,  viewTest.display.getBorder());
		assertEquals(null,  viewTest.historyDisplay.getBorder());
		
		assertEquals(new Color(24,29,33),  viewTest.mainpanel.getBackground());
		assertEquals(new Color(24,29,33),  viewTest.panel.getBackground());
		assertEquals(new Color(24,29,33),  viewTest.numbers.getBackground());
		assertEquals(new Color(24,29,33),  viewTest.buttons.getBackground());
		assertEquals(new Color(24,29,33),  viewTest.operators.getBackground());
		
	}

	@Test
	public void testButtonColor() {
		viewTest.buttonColor();
		assertEquals(new Color(218,100,2),  viewTest.benter.getBackground());
		assertEquals(new Color(250,251,255),  viewTest.benter.getForeground());
		assertEquals(false,  viewTest.benter.isFocusable());
		assertEquals(null,  viewTest.benter.getBorder());
	}

}
