import org.junit.*;
import static org.junit.Assert.*;


public class TestCalcModel 
{
	private CalcModel model;
	
	/**
	 * Constructs new instance of CalcModel before testing.
	 * @throws Exception
	 */
	@Before 
	public void setUp() throws Exception
	{
		model = new CalcModel();
	}
	
	/**
	 * Tests numericButton().
	 * Ensures that the buttons pressed determine the value.
	 * Also checks to make sure extra zeros and decimal points are removed.
	 */
	@Test
	public void testNumericButton()
	{
		model.numericButton("0");
		model.numericButton("0");
		model.numericButton("4");
		model.numericButton("1");
		model.numericButton("6");
		model.numericButton(".");
		model.numericButton("2");
		model.numericButton(".");						// input is 00416.2.
		assertEquals(model.getInputValue(), "416.2");	// expected value is 416.2
	}
	
	/**
	 * Tests sum().
	 * Sums up three numbers and compares the value with the expected sum.
	 * Also checks the history expression for infix notation.
	 */
	@Test
	public void testSum()
	{
		model.numericButton("6");
		model.enter();
		model.numericButton("1");
		model.numericButton("6");
		model.numericButton(".");
		model.numericButton("5");
		model.enter();
		model.numericButton("9");
		model.sum();
		model.sum();												// Input is 6 16.5 9 + +
		assertEquals(model.getInputValue(), "31.5");				// Expected value is 31.5
		assertEquals(model.getHistoryValue(), "6 + 16.5 + 9 =");	// Expected expression is 6 + 16.5 + 9 =
	}
	
	/**
	 * Tests subtract().
	 * Subtracts two numbers and compares the value to the expected difference.
	 * Also checks the history expression for infix notation.
	 */
	@Test
	public void testSubtract()
	{ 
		model.numericButton("2");
		model.numericButton("0");
		model.enter();
		model.numericButton("1");
		model.numericButton("5");
		model.subtract();									// Input is 20 15 -
		assertEquals(model.getHistoryValue(), "20 - 15 =");	// Expected expression is 20 - 15 =
		assertEquals(model.getInputValue(), "5");			// Expected value is 5
	}
	
	/**
	 * Tests multiply().
	 * Multiplies two numbers and compares the value with the expected product.
	 * Also checks the history expression for infix notation.
	 */
	@Test
	public void testMultiply()
	{
		model.numericButton("5");
		model.enter();
		model.numericButton("5");
		model.changeSign();
		model.multiply();									// Input is 5 5 +/- ×
		assertEquals(model.getInputValue(), "-25");			// Expected value is -25
		assertEquals(model.getHistoryValue(), "5 × -5 =");	// Expected expression is 5 × -5 =
	}
	
	/**
	 * Tests divide().
	 * Divides two numbers and compares the value with the expected quotient.
	 * Checks the history expression as well.
	 * Also tests the case of a number divided by zero which should give a MATH ERROR.
	 */
	@Test
	public void testDivide()
	{
		model.numericButton("7");
		model.enter();
		model.numericButton("2");
		model.divide();										// Input is 7 2 ÷
		assertEquals(model.getInputValue(), "3.5");			// Expected value is 3.5	
		assertEquals(model.getHistoryValue(), "7 ÷ 2 =");	// Expected expression is 7 ÷ 2 =
		
		model.clear();
		model.numericButton("7");
		model.enter();
		model.numericButton("0");
		model.enter();
		model.divide();										// Input is 7 0 ÷
		assertEquals(model.getInputValue(), "MATH ERROR");	// Expected value is MATH ERROR
	}
	
	/**
	 * Tests sine().
	 * Uses the sin operation on a number in radians and compares the value with the expected value.
	 * Ensures the operator is shown in prefix (sin goes before the operand) i.e. sin(x) where x is the operand.
	 */
	@Test
	public void testSine()
	{
		model.pi();
		model.numericButton("2");
		model.divide();
		model.sine();											// Input is π 2 ÷ sin
		assertEquals(model.getInputValue(), "1");				// Expected value is 1
		assertEquals(model.getHistoryValue(), "sin(π ÷ 2) =");	// Expected expression is sin(π ÷ 2) =
	}
	
	/**
	 * Tests cosine().
	 * Uses the cos operation on a number in radians and compares the value with the expected value.
	 * Ensures the operator is shown in prefix (cos goes before the operand) i.e. cos(x) where x is the operand.
	 */
	@Test
	public void testCosine()
	{
		model.pi();
		model.cosine();										// Input is π cos
		assertEquals(model.getInputValue(), "-1");			// Expected value is -1
		assertEquals(model.getHistoryValue(), "cos(π) =");	// Expected expression is cos(π) =
	}
	
	/**
	 * Tests changeSign().
	 * Uses the change sign operation on numerical button entries and compares the value with the expected negative value.
	 * If the operation is used in the middle of inputting numerical/decimal entries, the entry's sign is changed and the user is 
	 * able to continue with their entries.
	 */
	@Test
	public void testChangeSign()
	{
		// Passes if the operation changes the sign of the value
		model.numericButton("6");
		model.changeSign();
		model.enter();
		model.numericButton("3");
		model.changeSign();
		model.enter();
		assertEquals(model.getInputValue(), "-3");
		assertEquals(model.getHistoryValue(), "-6, -3");
		
		// Passes if the user can continue to enter numbers after changing sign
		model.clear();
		model.numericButton("6");
		model.changeSign();
		model.numericButton(".");
		model.numericButton("5");
		model.enter();
		assertEquals(model.getInputValue(), "-6.5");
		assertEquals(model.getHistoryValue(), "-6.5");
		
		// Passes if equation involving change sign gives the correct value
		model.clear();
		model.numericButton("9");
		model.changeSign();
		model.enter();
		model.numericButton("2");
		model.sum();
		model.numericButton("3");
		model.changeSign();
		model.enter();
		model.numericButton("4");
		model.subtract();
		model.multiply();
		assertEquals(model.getInputValue(), "49");
		assertEquals(model.getHistoryValue(), "(-9 + 2) × (-3 - 4) =");
	}

	/**
	 * Tests pi().
	 * Ensures the operation gives the correct value.
	 * If a number is input directly before π, the value is the product the number and π
	 */
	@Test
	public void testPi()
	{
		// Passes if pi equals its correct value to the 15th decimal point
		model.pi();
		assertEquals(Double.parseDouble(model.getInputValue()), Math.PI, 0.000000000000001);
		assertEquals(model.getHistoryValue(), "π");
		
		// Inputing a number before π automatically multiplies the two values
		model.clear();
		model.numericButton("2");
		model.pi();
		assertEquals(Double.parseDouble(model.getInputValue()), 2*Math.PI, 0.000000000000001);
		assertEquals(model.getHistoryValue(), "2 × π =");
	}

	/**
	 * Tests factorial().
	 * Calculates factorial on a number and checks for the expected value.
	 * If the input is not an integer, the value displays MATH ERROR.
	 */
	@Test
	public void testFacorial()
	{
		// 0! should equal 1
		model.clear();
		model.factorial();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "0! =");
		
		// 5! should equal 120
		model.clear();
		model.numericButton("5");
		model.factorial();
		assertEquals(model.getInputValue(), "120");
		assertEquals(model.getHistoryValue(), "5! =");
		
		// Using factorial on a number with a decimal should result in MATH ERROR
		model.clear();
		model.numericButton("5");
		model.numericButton(".");
		model.numericButton("8");
		model.factorial();
		assertEquals(model.getInputValue(), "MATH ERROR");
		assertEquals(model.getHistoryValue(), "start new calculation");
	}
	
	/**
	 * Tests enter().
	 * Ensures that the method inserts the input values onto the expression history. 
	 * If the value contains a lone decimal point, the operation makes it is in a proper format.
	 */
	@Test
	public void testEnter()
	{
		// A zero is added before a starting decimal point and after an ending decimal point
		model.numericButton(".");
		model.numericButton("8");
		model.enter();
		model.numericButton("6");
		model.numericButton(".");
		model.enter();
		model.numericButton("2");
		model.enter();
		assertEquals(model.getHistoryValue(), "0.8, 6.0, 2");
	}

	/**
	 * Tests clear().
	 * Passes if a model, after using clear, has value 0 and a cleared expression history
	 */
	@Test
	public void testClear()
	{
		model.numericButton("8");
		model.enter();
		model.clear();
		assertEquals(model.getInputValue(), "0");
		assertEquals(model.getHistoryValue(), "start new calculation");
	}

	/**
	 * Tests undo().
	 * If the most recent input is a number or decimal, the character is removed from the end of the value.
	 * If the previous input is an enter, pressing undo should delete the last value in the history.
	 * If the previous input is an operator, it is reverted.
	 * 
	 * FAILURE: the undo button doesn't work for change sign.
	 * The operation undoes the number but not the sign.
	 * i.e. pressing undo on -6
	 * expected value: 6
	 * value from operation: -
	 */
	@Test
	public void testUndo()
	{
		// Pressing undo during an input deletes the previous entry until it is empty
		model.numericButton("5");
		model.enter();
		model.numericButton("6");
		model.numericButton(".");
		model.numericButton("3");					// Input is 6.3
		model.undo();								
		assertEquals(model.getInputValue(), "6.");	// undo should give 6.
		model.undo();								
		assertEquals(model.getInputValue(), "6");	// undo should give 6
		model.undo();
		assertEquals(model.getInputValue(), "");	// undo should make value empty
		assertEquals(model.getHistoryValue(), "5");	// The current expression is shown
		
		// If the entries are 5, 6, 7, 8 pressing undo twice should give 5, 6 as the history
		model.clear();
		model.numericButton("5");
		model.enter();
		model.numericButton("6");
		model.enter();
		model.numericButton("7");
		model.enter();
		model.numericButton("8");
		model.enter();
		model.undo();
		model.undo();
		assertEquals(model.getHistoryValue(), "5, 6");
		model.numericButton("7");							// The user is then free to input additional values
		model.enter();
		assertEquals(model.getHistoryValue(), "5, 6, 7");
		
		// Passes if pressing undo works for all binary operations
		model.clear();
		model.numericButton("1");
		model.enter();
		model.numericButton("2");
		model.enter();
		model.numericButton("3");
		model.enter();
		model.numericButton("4");
		model.enter();
		model.numericButton("5");
		model.sum();
		model.subtract();
		model.multiply();
		model.divide();
		model.undo();
		model.undo();
		model.undo();
		model.undo();
		assertEquals(model.getInputValue(), "5");
		assertEquals(model.getHistoryValue(), "1, 2, 3, 4, 5");
		
		// Passes if pressing undo works for all unary operations
		model.clear();
		model.numericButton("5");
		model.factorial();
		model.sine();
		model.cosine();
		model.undo();
		model.undo();
		model.undo();
		assertEquals(model.getInputValue(), "5");
		assertEquals(model.getHistoryValue(), "5");
		
		// Passes if pressing undo works for change sign
		model.numericButton("6");
		model.changeSign();
		model.undo();
		assertEquals(model.getInputValue(), "6");
	}

	/**
	 * Tests the input 2 1 4 × ÷. 
	 * The expression should give 2 ÷ (1 × 4) =
	 * The value should give 0.5
	 * 
	 * FAILURE: it gives 2 ÷ 1 × 4 = 
	 * There should be brackets around the 1 and 4
	 * The equation gives the right value just not the right expression.
	 */
	@Test
	public void testEquation1()
	{
		model.numericButton("2");
		model.enter();
		model.numericButton("1");
		model.enter();
		model.numericButton("4");
		model.enter();
		model.multiply();
		model.divide();
		assertEquals(model.getInputValue(), "0.5");				//Passes
		assertEquals(model.getHistoryValue(), "2 ÷ (1 × 4)");	//Fails
	}
	
	/**
	 * Testing the input 2 2 + 2 2 × ÷
	 * Expected expression is (2 + 2) ÷ 2 × 2 =
	 * Expected value is 1
	 * 
	 * FAILURE: it gives 2 + 2 ÷ 2 × 2 =
	 * There should be brackets around the 2 + 2
	 */
	@Test
	public void testEquation2()
	{
		model.numericButton("2");
		model.enter();				
		model.numericButton("2");
		model.sum();				
		model.numericButton("2");
		model.enter();				
		model.numericButton("2");
		model.enter();
		model.multiply();
		model.divide();
		assertEquals(model.getInputValue(), "1");					//Passes
		assertEquals(model.getHistoryValue(), "(2 + 2) ÷ 2 × 2 =");	//Fails
	}
	
	/**
	 * Tests the input 1 1 × 1 1 ÷ 1 1 × + -
	 * Expected value is -1
	 * Expected expression is 1 × 1 - 1 ÷ 1 + 1 × 1 =
	 */
	@Test
	public void testEquation3()
	{
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.multiply();
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.divide();
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.multiply();
		model.sum();
		model.subtract();
		assertEquals(model.getInputValue(), "-1");
		assertEquals(model.getHistoryValue(), "1 × 1 - 1 ÷ 1 + 1 × 1 =");
	}
	
	/**
	 * Testing the input 1 1 + 1 2 - ÷
	 * Expected value is -2
	 * Expected expressions is (1 + 1) ÷ (1 - 2) =
	 */
	@Test
	public void testEquation4()
	{
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.sum();
		model.numericButton("1");
		model.enter();
		model.numericButton("2");
		model.subtract();
		model.divide();
		assertEquals(model.getInputValue(), "-2");
		assertEquals(model.getHistoryValue(), "(1 + 1) ÷ (1 - 2) =");
	}
}