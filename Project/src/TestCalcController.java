import static org.junit.Assert.*;

import org.junit.*;

public class TestCalcController 
{
	private CalcController controller;
	/*
	 * This class only has one test - testUpdateView(). The other methods are identical to the methods in CalcModel which
	 * is already tested in TestCalcModel. So the only test needed for CalcController is one that makes sure the view
	 * gets updated properly with each method.
	 */
	
	/**
	 * Creates new instance of controller before every test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		controller = new CalcController();
	}
	
	/**
	 * Tests that the view gets update with the proper value and history. Incorporates all operators within the equation.
	 */
	@Test
	public void testUpdateView()
	{
		controller.numericButton("1");
		controller.enter();
		controller.numericButton("2");
		controller.changeSign();
		controller.sum();
		controller.numericButton("3");
		controller.enter();
		controller.numericButton("4");
		controller.subtract();
		controller.multiply();
		controller.pi();
		controller.numericButton("2");
		controller.divide();
		controller.sine();
		controller.pi();
		controller.cosine();
		controller.multiply();
		controller.divide();
		controller.numericButton("0");
		controller.enter();
		controller.factorial();
		controller.multiply();
		assertEquals(controller.getDisplayText(), "-1");
		assertEquals(controller.getHistoryText(), "((1 + -2) × (3 - 4)) ÷ (sin(π ÷ 2) × cos(π)) × 0! =");
	}
}
