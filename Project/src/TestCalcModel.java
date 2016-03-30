
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;


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
		model.numericButton("1");
		model.numericButton("2");
		model.numericButton("3");
		model.numericButton("4");
		model.numericButton("5");
		model.numericButton("6");	
		model.numericButton(".");
		model.numericButton("7");
		model.numericButton("8");
		model.numericButton("9");
		model.numericButton(".");
		assertEquals(model.getInputValue(), "123456.789");	
	}
	
	/**
	 * Tests sum() by comparing the value with the expected sum.
	 * Also checks the history expression for infix notation.
	 */
	@Test
	public void testSum()
	{
		model.numericButton("1"); //input normally
		model.enter();
		model.numericButton("2");
		model.changeSign();
		model.sum();
		assertEquals(model.getHistoryValue(), "1 + -2 =");
		
		model.clear(); //press sum first
		model.sum();
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "0 + 0, 1, 1");
		
		model.clear(); //press sum after
		model.numericButton("1");
		model.enter();
		model.sum();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 + 0, 1");
		
		model.clear(); //press sum twice
		model.numericButton("1");
		model.enter();
		model.sum();
		model.sum();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 + 0 + 0, 1");
	}
	
	/**
	 * Tests subtract().
	 * Subtracts two numbers and compares the value to the expected difference.
	 * Also checks the history expression for infix notation.
	 */
	@Test
	public void testSubtract()
	{ 
		model.numericButton("5"); //input normally
		model.numericButton(".");
		model.numericButton("5");
		model.enter();
		model.numericButton("2");
		model.numericButton(".");
		model.numericButton("5");
		model.subtract();
		assertEquals(model.getInputValue(), "3");
		assertEquals(model.getHistoryValue(), "5.5 - 2.5 =");
		
		model.clear(); //press sub first
		model.subtract();
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "0 - 0, 1, 1");
		
		model.clear(); //press sub after
		model.numericButton("1");
		model.enter();
		model.subtract();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 - 0, 1");
		
		model.clear(); //press sub twice
		model.numericButton("1");
		model.enter();
		model.subtract();
		model.subtract();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 - 0 - 0, 1");
	}
	
	/**
	 * Tests multiply().
	 * Checks the history expression for infix notation.
	 * Checks to make sure it has precedence over sum() and subtract().
	 */
	@Test
	public void testMultiply()
	{
		model.numericButton("5"); //input normally
		model.enter();
		model.numericButton("5");
		model.changeSign();
		model.multiply();									// Input is 5 5 +/- ×
		assertEquals(model.getInputValue(), "-25");			// Expected value is -25
		assertEquals(model.getHistoryValue(), "5 × -5 =");	// Expected expression is 5 × -5 =
		
		model.clear(); //press mul first
		model.multiply();
		model.numericButton("1");
		model.enter();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "0 × 0, 1, 1");
		
		model.clear(); //press mul after
		model.numericButton("1");
		model.enter();
		model.multiply();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 × 0, 1");
		
		model.clear(); //press mul twice
		model.numericButton("1");
		model.enter();
		model.multiply();
		model.multiply();
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 × 0 × 0, 1");
		
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
		
		model.clear(); //press did first
		model.divide();
		assertEquals(model.getInputValue(), "MATH ERROR");
		assertEquals(model.getHistoryValue(), "0 ÷ 0 =");
		
		model.clear(); //press did after
		model.numericButton("1");
		model.enter();
		model.divide();
		assertEquals(model.getInputValue(), "MATH ERROR");
		model.numericButton("1");
		model.enter();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 ÷ 0, 1");
		
		/*
		 * model.clear(); //press did twice
		 
		model.divide();
		model.divide();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "1 × 0 × 0, 1");
		*/
		
		model.clear();				// did by 0 
		model.numericButton("7");	// Expected value is MATH ERROR
		model.enter();
		model.numericButton("0");
		model.enter();
		model.divide();										
		assertEquals(model.getInputValue(), "MATH ERROR");	
	}
	
	/**
	 * Tests sine().
	 * Uses the sin operation on a number in radians and compares the value with the expected value.
	 * Ensures the operator is shown in prefix (sin goes before the operand) i.e. sin(x) where x is the operand.
	 */
	@Test
	public void testSine()
	{
		model.pi(); //normally input
		model.numericButton("2");
		model.divide();
		model.sine();											// Input is π 2 ÷ sin
		assertEquals(model.getInputValue(), "1");				// Expected value is 1
		assertEquals(model.getHistoryValue(), "sin(π ÷ 2) =");	// Expected expression is sin(π ÷ 2) =
		
		model.clear(); //sin first
		model.sine();
		assertEquals(model.getInputValue(), "0");				
		assertEquals(model.getHistoryValue(), "sin(0) =");
		
		model.clear(); //sin twice sin( sin(0) / 2) => sin(0) => 0
		model.sine();
		assertEquals(model.getInputValue(), "0");				
		assertEquals(model.getHistoryValue(), "sin(0) =");
		model.numericButton("2");
		model.divide();
		model.sine();
		assertEquals(model.getInputValue(), "0");				
		assertEquals(model.getHistoryValue(), "sin(sin(0) ÷ 2) =");
		
		model.clear(); //for making the valueResetFlag become false
		model.numericButton(".");
		model.sine();
		assertEquals(model.getInputValue(), "0");				
		assertEquals(model.getHistoryValue(), "sin(0.0) =");
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
		
		model.clear(); //cos first
		model.cosine();
		assertEquals(model.getInputValue(), "1");				
		assertEquals(model.getHistoryValue(), "cos(0) =");
		
		model.clear(); //sin twice cos( cos(0) × π) => cos(π) => 1
		model.cosine();
		assertEquals(model.getInputValue(), "1");				
		assertEquals(model.getHistoryValue(), "cos(0) =");
		model.pi();;
		model.multiply();
		model.cosine();
		assertEquals(model.getInputValue(), "-1");				
		assertEquals(model.getHistoryValue(), "cos(cos(0) × π) =");
		
		model.clear(); //for making the valueResetFlag become false
		model.numericButton(".");
		model.cosine();
		assertEquals(model.getInputValue(), "1");				
		assertEquals(model.getHistoryValue(), "cos(0.0) =");
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
		//normally input
		model.numericButton("6");
		model.changeSign();
		assertEquals(model.getInputValue(), "-6");
		assertEquals(model.getHistoryValue(), "");
		model.enter();
		
		model.numericButton("7"); 
		model.sum();
		assertEquals(model.getInputValue(), "1");
		assertEquals(model.getHistoryValue(), "-6 + 7 =");
		
		model.changeSign(); //change sign for the equation
		assertEquals(model.getHistoryValue(), "-(-6 + 7) =");
		
		model.changeSign(); //change sign for the equation again
		assertEquals(model.getHistoryValue(), "-6 + 7 =");
		
		model.clear(); //some number bigger
		model.numericButton("1");
		model.numericButton("0");
		model.numericButton("0");
		model.enter();
		model.changeSign(); //change its sign first
		assertEquals(model.getInputValue(), "-100");
		assertEquals(model.getHistoryValue(), "-100 =");
		
		model.changeSign();//change it back
		assertEquals(model.getInputValue(), "100");
		assertEquals(model.getHistoryValue(), "100 =");
		
		model.clear();//some number smaller
		model.changeSign();
		model.numericButton("6");
		model.enter();
		model.changeSign(); //change its sign first
		assertEquals(model.getInputValue(), "-6");
		assertEquals(model.getHistoryValue(), "-6 =");
		
		model.changeSign();//change it back
		assertEquals(model.getInputValue(), "6");
		assertEquals(model.getHistoryValue(), "6 =");
		
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
		assertEquals(model.getInputValue(), "3.1415926536");
		assertEquals(model.getHistoryValue(), "π");
		
		// Inputing a number before π automatically multiplies the two values
		model.clear();
		model.numericButton("2");
		model.pi();
		assertEquals(model.getInputValue(), "6.2831853072");
		assertEquals(model.getHistoryValue(), "2 × π =");
	}

	/**
	 * Tests x().
	 * Calculates factorial on a number and checks for the expected value.
	 * If the input is not a positive integer the value displays MATH ERROR.
	 */
	@Test
	public void testX()
	{
		model.clear();//make the historyResetFlag true
		model.x(); //normally input
		assertEquals(model.getInputValue(), "0");
		assertEquals(model.getHistoryValue(), "x");
		
		model.x();
		assertEquals(model.getInputValue(), "0");
		assertEquals(model.getHistoryValue(), "x, x");
	}
	
	/**
	 * Tests factorial().
	 * Calculates factorial on a number and checks for the expected value.
	 * If the input is not a positive integer the value displays MATH ERROR.
	 */
	@Test
	public void testFacorial()
	{
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
		
		//negative number should be MATH ERROR
		model.clear();
		model.numericButton("2");
		model.changeSign();
		model.enter();
		model.factorial();
		assertEquals(model.getInputValue(), "MATH ERROR");
		
		model.clear();//when the input bigger than 170
		model.numericButton("1");
		model.numericButton("7");
		model.numericButton("1");
		model.factorial();
		assertEquals(model.getInputValue(), "∞");
		
		model.clear();//when the input bigger than 170
		model.numericButton("1");
		model.numericButton("7");
		model.numericButton("0");
		model.factorial();
		assertEquals(model.getInputValue(), "7.257E306");
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
		model.numericButton("1");
		model.enter();
		model.numericButton("2");
		model.changeSign();
		model.enter();
		model.numericButton("3");
		model.numericButton(".");
		model.numericButton("5");
		model.enter();
		model.numericButton(".");
		model.numericButton("5");
		model.enter();
		model.numericButton("6");
		model.numericButton(".");
		model.enter();
		assertEquals(model.getHistoryValue(), "1, -2, 3.5, 0.5, 6.0");
		
		model.clear(); //input "." first
		model.numericButton(".");
		model.enter();
		assertEquals(model.getHistoryValue(), "0.0");
		
		model.clear(); //input "." in the end
		model.numericButton("5");
		model.numericButton("5");
		model.numericButton(".");
		model.enter();
		assertEquals(model.getHistoryValue(), "55.0");
	
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
		assertEquals(model.getHistoryValue(), "Start New Calculation");
	}

	/**
	 * Tests undo().
	 * If the most recent input is a number or decimal, the character is removed from the end of the value.
	 * If the previous input is an enter, pressing undo should delete the last value in the history.
	 * If the previous input is an operator, it is reverted.
	 * 
	 */
	@Test
	public void testUndo()
	{
		model.numericButton("1");//undo normally 1 + 7 = 8
		model.enter();
		model.numericButton("7");
		model.sum();
		assertEquals(model.getInputValue(), "8");
		assertEquals(model.getHistoryValue(), "1 + 7 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "1, 7 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "1 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "Start New Calculation");
		
		model.clear(); //undo negative number -6 + 5 = -1
		model.numericButton("6");
		model.changeSign();
		model.enter();
		model.numericButton("5");
		model.sum();
		assertEquals(model.getInputValue(), "-1");
		assertEquals(model.getHistoryValue(), "-6 + 5 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "-6, 5 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "-6 =");
		model.undo();
		assertEquals(model.getHistoryValue(), "Start New Calculation");
		
		model.clear(); //undo while typing a negative number
		model.numericButton("6");
		model.numericButton("5");
		model.changeSign();
		assertEquals(model.getInputValue(), "-65");
		model.undo();
		assertEquals(model.getInputValue(), "-6");
		model.undo();
		assertEquals(model.getInputValue(), "0");
		model.undo();
		assertEquals(model.getInputValue(), "0");
		
		model.clear();//undo while typing a positive number
		model.numericButton("6");
		model.numericButton("5");
		assertEquals(model.getInputValue(), "65");
		model.undo();
		assertEquals(model.getInputValue(), "6");
		model.undo();
		assertEquals(model.getInputValue(), "0");
		model.undo();
		assertEquals(model.getInputValue(), "0");
		
		model.clear(); //i tried to find a case that suit for line 515 but i failed
		model.numericButton("0");
		model.undo();
		assertEquals(model.getInputValue(), "0");
		assertEquals(model.getHistoryValue(), "Start New Calculation");
		
		model.changeSign(); //undo math error 
		model.numericButton("7");
		model.divide();
		assertEquals(model.getInputValue(), "MATH ERROR");
		model.undo();
		assertEquals(model.getHistoryValue(), "7, 0 =");
		
		//i cant find a case that suit for line 539
		
		model.clear(); //undo unary
		model.numericButton("0");
		model.cosine();
		assertEquals(model.getInputValue(), "1");
		model.undo();
		assertEquals(model.getInputValue(), "0");
	}

	/**
	 * Tests to make sure getFunction returns an array of correct y values of a specific function.
	 */
	@Test
	public void testGetFunction()
	{
		//testing getFunction on no input
		double[] array1 = new double[1000];
		for (int i = 0; i < 1000; i++)
		{
			array1[i] = 0.0;
		}
		assertTrue(Arrays.equals(model.getFunction(), array1));
		
		//testing getFunction on input
		model.numericButton("6");
		model.enter();
		double[] array2 = new double[1000];
		for (int i = 0; i < 1000; i++)
		{
			array2[i] = 6.0;
		}
		assertTrue(Arrays.equals(model.getFunction(), array2));
	}
	
	/**
	 * Tests to make sure getEquation returns a string representation of the function.
	 */
	@Test
	public void testGetEquation() 
	{
		model.numericButton("7"); //input normally
		model.enter();
		model.numericButton("6");
		model.sum();
		assertEquals(model.getEquation(), "y = 7 + 6");
		
		model.clear();
		model.numericButton("7"); //only one input
		model.enter();
		assertEquals(model.getEquation(), "y = 7");
		
		model.clear(); //no input
		assertEquals(model.getEquation(), "y = 0");
		
		model.clear();
		model.numericButton("7"); //multi  input
		model.enter();
		model.numericButton("6");
		model.enter();
		assertEquals(model.getEquation(), "y = 6");
		
		//have no idea what is the suit case for line 611,614
	}
	
	
	/**
	 * Tests the input 2 1 4 × ÷. 
	 * The expression should give 2 ÷ (1 × 4) =
	 * The value should give 0.5
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
		assertEquals(model.getHistoryValue(), "2 ÷ (1 × 4) =");	
	}
	
	/**
	 * Testing the input 2 2 + 2 2 × ÷
	 * Expected expression is (2 + 2) ÷ 2 × 2 =
	 * Expected value is 1
	 * 
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
		assertEquals(model.getHistoryValue(), "(2 + 2) ÷ (2 × 2) =");	
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
		assertEquals(model.getHistoryValue(), "1 × 1 - (1 ÷ 1 + 1 × 1) =");
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
