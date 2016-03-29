/*
 * This class only has one test - testUpdateView(). The other methods are identical to the methods in CalcModel which
 * is already tested in TestCalcModel. So the only test needed for CalcController is one that makes sure the view
 * gets updated properly with each method.
 */

import static org.junit.Assert.*;

import org.junit.*;

public class TestCalcController 
{
	private CalcController controller;
	
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
	 * Tests 
	 */
	@Test
	public void testSum()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.sum();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");//normal input
		controller.enter();
		controller.numericButton("1");
		controller.sum();
		assertEquals(controller.getModel().getInputValue(), "8");
		assertEquals(controller.getHistoryText(), "7 + 1 =");
	}
	
	@Test
	public void testSubtract()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.subtract();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");//normal input
		controller.enter();
		controller.numericButton("1");
		controller.subtract();
		assertEquals(controller.getModel().getInputValue(), "6");
		assertEquals(controller.getHistoryText(), "7 - 1 =");
	}
	
	@Test
	public void testMultiply()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.multiply();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");//normal input
		controller.enter();
		controller.numericButton("1");
		controller.multiply();
		assertEquals(controller.getModel().getInputValue(), "7");
		assertEquals(controller.getHistoryText(), "7 × 1 =");
	}
	
	@Test
	public void testDivide()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.divide();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");//normal input
		controller.enter();
		controller.numericButton("1");
		controller.divide();
		assertEquals(controller.getModel().getInputValue(), "7");
		assertEquals(controller.getHistoryText(), "7 ÷ 1 =");
	}
	
	@Test
	public void testUndo()
	{
		controller.numericButton("7");
		controller.undo();
		assertEquals(controller.getHistoryText(), "");
	}
	
	@Test
	public void testEnter()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.enter();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");
		controller.enter();
		assertEquals(controller.getHistoryText(), "7");
	}
	
	@Test
	public void testSine()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.sine();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("0");
		controller.sine();
		assertEquals(controller.getHistoryText(), "sin(0) =");
	}
	
	@Test
	public void testCosine()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.cosine();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("0");
		controller.cosine();
		assertEquals(controller.getHistoryText(), "cos(0) =");
	}
	
	@Test
	public void testPi()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.pi();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.pi();
		controller.enter();
		assertEquals(controller.getHistoryText(), "π, 3.1415926536");
	}
	
	@Test
	public void testFactorial()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.factorial();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("3");
		controller.factorial();
		assertEquals(controller.getHistoryText(), "3! =");
	}
	
	@Test
	public void testChangeSign()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.changeSign();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");
		controller.changeSign();
		controller.enter();
		assertEquals(controller.getHistoryText(), "-7");
	}
	
	@Test
	public void testNumericButton()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.numericButton("1");
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.numericButton("7");
		controller.enter();
		assertEquals(controller.getHistoryText(), "7");
	}
	
	@Test
	public void testGetDisplayText()
	{
		controller.numericButton("7"); 
		controller.enter();
		assertEquals(controller.getHistoryText(), controller.getDisplayText());
	}
	
	@Test
	public void testX()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.x();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.x();
		assertEquals(controller.getHistoryText(), "x");
	}
	
	@Test
	public void testGraph()
	{
		controller.numericButton("7"); // Math Error
		controller.divide();
		controller.graph();
		assertEquals(controller.getModel().getInputValue(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
		
		controller.clear();
		controller.graph();
		assertEquals(controller.getHistoryText(), "Start New Calculation");
	}
}
