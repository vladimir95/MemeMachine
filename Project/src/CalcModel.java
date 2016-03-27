import java.text.DecimalFormat;
import java.util.Stack;

public class CalcModel 
{
	private StringBuilder inputValue;	//value displayed as user pressing numeric and decimal buttons
	private StringBuilder historyValue; //string representation of historyStack
	private Stack<String> historyStack; //history stored when user completes input or performs arithmetic operation
	private Stack<String> printStack;	//temp. stack used to print the contents of the historyStack
	private Stack<MathValue> calcStack;	//user input is stored in a stack data type
	private Stack<MathValue> preStack;		//stack that holds the numeric value of previous calculations and input
	
	private final String INITIAL_DISPLAYED_VALUE = "0";
	private final String INITIAL_DISPLAYED_HISTORY = "Start New Calculation";
	
	private final String BINARY = "+- × ÷";	//The space in the middle is important for checking operator precedence
	private final String UNARY  = "sincos!CS";
	private final String FACT	= "!";
	private final String PI 	= "π";
	private final String CHANGE_SIGN = "CS";
	private final String X = "x";
	
	private boolean valueResetFlag;		//true if inputValue needs to be reset, false if inputValue needs to be appended.
	private boolean historyResetFlag; 	//true if historyValue needs to be reset, false if historyValue needs to be appended.
	private boolean mathErrorFlag;		//true if a mathematical error was made
	
	private Stack<Integer> commaStack;	//tracks the location of the right most comma in the historyValue.
	
	/**
	 * Creates a new model with initial user and history values, and initializes empty stacks.
	 */
	CalcModel()
	{
		inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
		historyValue = new StringBuilder(INITIAL_DISPLAYED_HISTORY);
		calcStack = new Stack<MathValue>();
		historyStack = new Stack<String>();
		printStack = new Stack<String>();
		preStack = new Stack<MathValue>();
		commaStack = new Stack<Integer>();
		valueResetFlag = true;
		historyResetFlag = true;
		mathErrorFlag = false;
	}
	
	/**
	 * Reads the user input when the user presses numeric or decimal buttons.
	 * Validates the input to avoid entering a number with leading zeroes (0) and multiple decimal points.
	 * @param buttonName - The name of the button as seen in the View.
	 * @post. inputValue' = inputValue + buttonName IF buttonName != "." AND "." is NOT in inputValue AND inputValue != buttonName != 0;
	 */
	public void numericButton(String buttonName)
	{
		if(valueResetFlag)			//If first button press, update inputValue with buttonName instead of appending
		{
				if(buttonName.equals("."))
				{
					inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
					inputValue.append(buttonName);
					valueResetFlag = false;
				}
				else
				{
					inputValue = new StringBuilder(buttonName);
					checkEqualSign();
					if(!buttonName.equals(INITIAL_DISPLAYED_VALUE))
						valueResetFlag = false;
				}
		}
		else if(inputValue.indexOf(".") == -1 || !buttonName.equals(".")) //If not first button press, append buttonName
			inputValue.append(buttonName);
		
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
		}
	}
	
	/**
	 * Adds the 2 top values of the stack and pushes the result back to the top of the stack.
	 * @post. calcStack.TOP' = calcStack.SECONDTOP + calcStack.TOP;
	 * 		  preStack.TOP = calcStack.TOP;
	 *        preStack.SEOCNDTOP = calcStack.SECONDTOP;
	 *        historyStack.TOP = "+";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void sum()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)		 //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] + top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());//The result is variable if one of the operands is a variable
		calcStack.push(result);																	   //and a constant otherwise
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(0) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Subtracts the 2 top values of the stack and pushes the result back to the top of the stack.
	 * @post. calcStack.TOP' = calcStack.SECONDTOP - calcStack.TOP;
	 * 		  preStack.TOP = calcStack.TOP;
	 *        preStack.SEOCNDTOP = calcStack.SECONDTOP;
	 *        historyStack.TOP = "-";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void subtract()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)		 //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] - top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());//The result is variable if one of the operands is a variable
		calcStack.push(result);																	   //and a constant otherwise
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(1) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Multiplies the 2 top values of the stack and pushes the result back to the top of the stack.
	 * @post. calcStack.TOP' = calcStack.SECONDTOP * calcStack.TOP;
	 * 		  preStack.TOP = calcStack.TOP;
	 *        preStack.SEOCNDTOP = calcStack.SECONDTOP;
	 *        historyStack.TOP = "×";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void multiply()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)      //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] * top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable()); //The result is variable if one of the operands is a variable
		calcStack.push(result);																	   //and a constant otherwise
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(3) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Divides the 2 top values of the stack and pushes the result back to the top of the stack.
	 * If a division by 0 occurs, the calculator is reset and an error message is displayed.
	 * @post. calcStack.TOP' = calcStack.SECONDTOP / calcStack.TOP;
	 * 		  preStack.TOP = calcStack.TOP;
	 *        preStack.SEOCNDTOP = calcStack.SECONDTOP;
	 *        historyStack.TOP = "÷";
	 *        inputValue = calcStack.TOP';
	 *        historyValue' = printHistory();
	 *        
	 *        IF calcStack.SECONDTOP == calcStack.TOP == CONSTANT AND calcStack.TOP == 0
	 *        calcStack' = calcStack;
	 *        inputValue = "MATH ERROR"
	 *        historyStack.TOP = "÷";
	 *        historyValue = printHistory();
	 *        mathErrorFlag = true;
	 */
	public void divide()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		boolean divisionByZero = false;
		
		if(!valueResetFlag)      //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 1; i < MathValue.NUMBER_OF_POINTS; i++)
			if(top.getValue()[i] == 0)  //during the calculation of the division operation, if division by 0 occurs 
			{							//a large number is substituted instead of throwing an error but a flag is set to indicate that division by 0 occurred
				resultArray[i] = 1E6;
				resultArray[i-1] = -1E6;
				divisionByZero = true;
			}
			else
				resultArray[i] = secondTop.getValue()[i] / top.getValue()[i];
		if(!top.isVariable() && !secondTop.isVariable() && divisionByZero) //if division by zero occurred and both values are constants, MATH ERROR occurs
		{																   //The user can undo the mathErrorFlag with the UNDO() method
			mathErrorFlag = true;
			historyStack.push(BINARY.charAt(5) + "");
			calcStack.push(secondTop);
			calcStack.push(top);
			inputValue = new StringBuilder("MATH ERROR");
			printHistory();
		}
		else
		{
			MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());//The result is variable if one of the operands is a variable
			calcStack.push(result);																	   //and a constant otherwise
			preStack.push(secondTop);
			preStack.push(top);
			historyStack.push(BINARY.charAt(5) + "");
			printHistory();
			updateOperationValue(result);
		}
	}
	
	/**
	 * Calculates the sine of the top value of the stack and pushes the result back to the top of the stack.
	 * @post. calcStack.TOP' = Math.sin(calcStack.TOP);
	 * 		  preStack.TOP = calcStack.TOP;
	 *        historyStack.TOP = "sin";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void sine()
	{
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag)		 //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] = Math.sin(top.getValue()[i]);
		MathValue result = new MathValue(resultArray, top.isVariable());//The result is variable if the top operand is a variable
		calcStack.push(result);										    //and a constant otherwise
		preStack.push(top);
		historyStack.push(UNARY.substring(0, 3));
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Calculates the cosine of the top value of the stack and pushes the result back to the top of the stack.
	 * @post. calcStack.TOP' = Math.cos(calcStack.TOP);
	 * 		  preStack.TOP = calcStack.TOP;
	 *        historyStack.TOP = "cos";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void cosine()
	{
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag)      //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] = Math.cos(top.getValue()[i]);
		MathValue result = new MathValue(resultArray, top.isVariable());//The result is variable if the top operand is a variable
		calcStack.push(result);										    //and a constant otherwise
		preStack.push(top);
		historyStack.push(UNARY.substring(3, 6));
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Changes the sign of the inputValue without pushing the result into a stack.
	 * @post. inputValue' = "-" + inputValue  IF valueResetFlag == false AND inputValue does NOT have "-";
	 *        inputValue' = inputValue - "-"  IF valueResetFlag == false AND inputValue HAS "-";
	 *        IF valueResetFlag == true
	 *        calcStack.TOP' = -1 * calcStack.TOP;
	 *        preStack.TOP = calcStack.TOP;
	 *        historyStack.TOP = "CS";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void changeSign()
	{
		String value;
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag) //If the user is currently typing input, the sign of the input will change
		{
			value = getInputValue();
			if(!value.isEmpty())
			{
				if((value.length() != 1) || (value.charAt(0) != '-'))
				{
					if(value.charAt(0) == '-')
						value = value.substring(1);
					else
						value = "-" + value;
					updateOperationValue(new MathValue(Double.parseDouble(value)));
				}
			}
		}
		else		//If the user is NOT currently typing input, the sign of the top value in the calcStack will change and will be updated in the historyValue
		{
			if(!calcStack.empty())
			{
				top = calcStack.pop();
				for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
					resultArray[i] = -1 * top.getValue()[i];
				MathValue result = new MathValue(resultArray, top.isVariable());
				calcStack.push(result);
				preStack.push(top);
				historyStack.push(CHANGE_SIGN);
				printHistory();
				updateOperationValue(result);
			}
		}
	}
	
	/**
	 * Pushes the Pi constant into the stack.
	 * If user was typing an input, pushes the input into the stack and multiplies it by pi (3*pi).
	 * @post. calcStack.TOP' = Math.PI;
	 *        historyStack.TOP = "π";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void pi()
	{
		if(!valueResetFlag) //If the user was typing input before pressing the constant button the input will be pushed into calcStack before
		{      	            // the constant is pushed in calcStack. The 2 values will then be multiplied and the result will be at the top of calcStack
			enter();
			calcStack.push(new MathValue(Math.PI));
			historyStack.push(PI);
			multiply();
		}
		else
		{
			if (historyResetFlag)
			{
				historyValue = new StringBuilder();
				historyResetFlag = false;
			}
			calcStack.push(new MathValue(Math.PI));
			historyStack.push(PI);
			printHistory();
			updateOperationValue(new MathValue(Math.PI));
			
		}
	}
	/**
	 * Pushes the y=x function into the stack.
	 * @post. calcStack.TOP' = new MathValue();
	 *        historyStack.TOP = "x";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 */
	public void x()
	{
		MathValue x = new MathValue();
		
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
			historyResetFlag = false;
		}
		calcStack.push(x);
		historyStack.push(X);
		printHistory();
		updateOperationValue(x);
	}
	
	/**
	 * Calculates the factorial of the top value of the stack, and pushes the result back to the top of the stack.
	 * If the value is not a whole number, the calculator is reset and an error message is displayed.
	 * @post. calcStack.TOP' = calcStack.TOP!;
	 * 		  preStack.TOP = calcStack.TOP;
	 *        historyStack.TOP = "!";
	 *        inputValue = calcStack.TOP';
	 *        historyValue = printHistory();
	 *        
	 *        IF calcStack.TOP == CONSTANT AND calcStack.TOP != whole number
	 *        calcStack' = calcStack;
	 *        inputValue = "MATH ERROR"
	 *        historyStack.TOP = "!";
	 *        historyValue = printHistory();
	 *        mathErrorFlag = true;
	 */
	public void factorial()
	{
		MathValue top;
		MathValue resultCalc = new MathValue(1);
		boolean undefinedValueCalculated = false;
		
		if(!valueResetFlag)      //If the user was typing input before pressing the operation button
			enter();        	 //the input will be pushed into calcStack before performing the operation
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			if(top.getValue()[i] < 0 || top.getValue()[i] != Math.floor(top.getValue()[i]))
			{
				resultCalc.getValue()[i] = Double.NaN;      //during the calculation of the factorial operation, if a factorial of a whole number occurs 
				undefinedValueCalculated = true;		//Double.NAN is substituted instead of throwing an error but a flag is set to indicate that an undefined
			}											//value was calculated
			else
			{
				if(top.getValue()[i] > 170)				//Factorial of values larger than 170 will cause the double type to overflow, so instead of calculating
														//the actual value which can take a long time, Double.POSITIVE_INFINITY is returned as the result
					resultCalc.getValue()[i] = Double.POSITIVE_INFINITY;
				else if (Double.isNaN(top.getValue()[i])) //Handles the factorial calculation of NaN.
					resultCalc.getValue()[i] = Double.NaN;
				else
				{
					for(int j = 1; j <= (int)top.getValue()[i]; j++)
						resultCalc.getValue()[i] *= j;
				}
			}
		MathValue result = new MathValue(resultCalc.getValue(), top.isVariable());
		if(!top.isVariable() && undefinedValueCalculated)//if factorial of undefined value occurred and both values are constants, MATH ERROR occurs
		{								  				 //The user can undo the mathErrorFlag with the UNDO() method
			mathErrorFlag = true;
			historyStack.push(FACT);
			calcStack.push(top);
			inputValue = new StringBuilder("MATH ERROR");
			printHistory();
		}
		else
		{
			calcStack.push(result);
			preStack.push(top);
			historyStack.push(FACT);
			printHistory();
			updateOperationValue(result);
		}
	}
	
	/**
	 * Pushes the user value to the top of the valueStack and the historyStack.
	 * If the input has a decimal point, validates and corrects the input into proper decimal format.
	 * @post. calcStack.TOP = inputValue;
	 */
	public void enter()
	{
		calcStack.push(new MathValue(Double.parseDouble(inputValue.toString())));
		valueResetFlag = true;
		if(inputValue.charAt(0) == '.')						//The user might enter values such as .5 or 5. to mean 0.5 and 5.0 respectively
			inputValue.insert(0, '0');						//The parser will understand these values regardless, but we would like to display them as
		if(inputValue.charAt(inputValue.length() - 1) == '.')//0.5 and 5.0 instead of .5 and 5.
			inputValue.append('0');
		historyStack.push(inputValue.toString());
		printHistory();
		historyResetFlag = false;
		
	}
	
	/**
	 * Clears the data in all stacks and resets the history and input value to the initial configuration.
	 * @post. CalcModel object set to the same initial configuration as set by the class constructor CalcModel();
	 */
	public void clear()
	{
		inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
		historyValue = new StringBuilder(INITIAL_DISPLAYED_HISTORY);
		calcStack = new Stack<MathValue>();
		historyStack = new Stack<String>();
		printStack = new Stack<String>();
		preStack = new Stack<MathValue>();
		commaStack = new Stack<Integer>();
		valueResetFlag = true;
		historyResetFlag = true;
		mathErrorFlag = false;
	}
	
	/**
	 * Undoes the last user operation. 
	 * If the user was typing an input, removes the last digit typed.
	 * If the user was not typing, removes the previous arithmetic or enter operation.
	 * @post. IF valueResetFlag == false
	 *        inputValue' = inputValue - lastChar;
	 *        
	 *        IF valueResetFlag == true AND maethErrorFlag == true AND calcStack.size > 0;
	 *        historyStack.TOP' = historyStack.SECONDTOP AND historyStack.size' = historyStack.size - 1;
	 *        historyValue = printHistory() + " =";
	 *        inputValue = calcStack.TOP;
	 *        
	 *        IF valueResetFlag == true AND maethErrorFlag == true AND calcStack.size == 0;
	 *        clear();
	 *        
	 *        IF valueResetFlag == true AND maethErrorFlag == false AND calcStack.size > 0;
	 *        historyStack.TOP' = historyStack.SECONDTOP AND historyStack.size' = historyStack.size - 1;
	 *        calcStack.TOP' = calcStack.SECONDTOP AND calcStack.size' = calcStack.size - 1;
	 *        calcStack.TOP' = preStack.TOP AND preStack.size' = preStack.size - 1 AND calcStack.size' = calcStack.size + 1
	 *        OR calcStack.TOP' = preStack.TOP AND calcStack.SECONDTOP' = preStack.SECONDTOP AND preStack.size' = preStack.size -2
	 *        AND calcStack.size' = calcStack.size + 2; 
	 *        historyValue = printHistory() + " =";
	 *        inputValue = calcStack.TOP;
	 */
	public void undo()
	{
		String value;
		MathValue top, secondTop;
		
		if(!valueResetFlag) //if the user is typing input, the last digit will be removed
		{
			inputValue.deleteCharAt(inputValue.length() - 1);
			if(inputValue.length() == 1 && inputValue.charAt(0) == '-') //in a negative value, if the last digit is removed, the "-" sign is removed as well
				inputValue.deleteCharAt(inputValue.length() - 1);
			if(inputValue.length() == 1 && inputValue.charAt(0) == '0')
				valueResetFlag = true;
			if(inputValue.length() == 0) //if the last digit is removed, a zero is placed instead of leaving a black inputValue
			{
				updateOperationValue(new MathValue(Double.parseDouble(INITIAL_DISPLAYED_VALUE)));
				valueResetFlag = true;
			}
		}
		else if(historyStack.empty())
				clear();
		/*else if(inputValue.length() == 0)
		{
			printHistory();
			if(calcStack.empty())
				updateOperationValue(Double.parseDouble(INITIAL_DISPLAYED_VALUE));
			else
				updateOperationValue(calcStack.peek());
		}*/
		else if(mathError()) //if a mathErrorFlag was set, the operation that caused it to be set will be reverted and the user can continue as normal
		{
			historyStack.pop();
			printHistory();
			checkEqualSign();
			historyValue.append(" =");
			if(calcStack.empty())
				clear();
			else
				updateOperationValue(calcStack.peek());
			mathErrorFlag = false;
		}
		else
		{
			calcStack.pop();
			value = historyStack.pop();
			if(BINARY.indexOf(value) != -1) //if the reverted operation was binary, pops the top two value of preStack and puts then into calcStack
			{
				top = preStack.pop();
				secondTop = preStack.pop();
				calcStack.push(secondTop);
				calcStack.push(top);
			}
			if(UNARY.indexOf(value) != -1) //if the reverted operation was unary, pops the top value of preStack and puts it into calcStack
			{
				top = preStack.pop();
				calcStack.push(top);
			}
			printHistory();
			checkEqualSign();
			historyValue.append(" =");
			if(calcStack.empty())
				clear();
			else
				updateOperationValue(calcStack.peek());
		}
	}
	
	/**
	 * Returns the inputValue.
	 * @return - String representation of the inputValue.
	 */
	public String getInputValue()
	{
		return inputValue.toString();
	}
	
	/**
	 * Returns the historyValue.
	 * @return - String representation of the historyValue.
	 */
	public String getHistoryValue()
	{
		return historyValue.toString();
	}
	
	/**
	 * Returns the array of points of the top value of calcStack.
	 * @return - double array of points of the top value of calcStack
	 */
	public double[] getFunction()
	{
		if(calcStack.empty())
			return new MathValue(0).getValue();
		else
			return calcStack.peek().getValue();
	}
	
	/**
	 * returns the equation of the top value of calcStack.
	 * @return - String representation of the function stored at the top of calcStack
	 */
	public String getEquation()
	{
		String equation;
		if(historyValue.indexOf("=") != -1)
		{
			checkEqualSign();
			if(historyStack.empty())
				equation = "y = 0";
			else
				if(commaStack.peek() == 0)
					equation = "y = " + historyValue.substring(commaStack.peek());
				else
					equation = "y = " + historyValue.substring(commaStack.peek() + 2);
			historyValue.append(" =");
		}
		else
		{
			if(historyStack.empty())
				equation = "y = 0";
			else
				if(commaStack.peek() == 0)
					equation = "y = " + historyValue.substring(commaStack.peek());
				else
					equation = "y = " + historyValue.substring(commaStack.peek() + 2);
		}
		return equation;
	}
	
	/**
	 * Checks if a math error has occurred.
	 * @return - True if math error occurred and false otherwise.
	 */
	public boolean mathError()
	{
		return mathErrorFlag;
	}
	
	/**
	 * Checks to see if the historyValue has a "=" sign in it and deletes it. 
	 * @post. IF inputValue HAS "=" inputValue' = inputValue - "=";
	 */
	private void checkEqualSign()
	{
		if(historyValue.indexOf(" =") != -1)
		{
			historyValue.delete(historyValue.indexOf(" ="), historyValue.length());
		}
	}
	
	/**
	 * prints the data in the historyStack into the historyValue in infix representation
	 * @post. historyValue = infix notation of historyStack;
	 *        hitoryStack.size' = historyStack.size;
	 */
	private void printHistory()
	{
		String value;
		int commaPos;
		int currentPrecedence;
		int lastOperatorValue = 10;
		String lastExpr = ""; //used for printing change sign, by cycling between 2 options, one with a "-" sign and one without
		
		Stack<Integer> operatorStack = new Stack<Integer>();	//Holds precedence values for operators
		historyValue = new StringBuilder();
		while(!historyStack.empty())							//shuffles the historyStack such that the bottom value is now on top
		{
			value = historyStack.pop();
			printStack.push(value);
		}
		while(!printStack.empty())								//while shuffling the historyStack back to the original state, each value is checked
		{														//and printed accordingly
			value = printStack.pop();
			checkEqualSign();
			if(BINARY.indexOf(value) != -1) //For a binary operation, a precedence of the operation is calculated first
			{								//base on that, the brackets are printed in a certain order
				commaPos = commaStack.pop();
				currentPrecedence = checkPrecedence(operatorStack, value);
				if(currentPrecedence == 0)
				{
					historyValue.replace(commaPos, commaPos + 1, " " + value);
				}
				if(currentPrecedence == 1)
				{
					historyValue.replace(commaPos, commaPos + 1, ") " + value);
					commaPos = commaStack.pop();
					if(commaPos == 0)
						historyValue.insert(commaPos, "(");
					else
						historyValue.insert(commaPos + 2, "(");
					commaStack.push(commaPos);
				}
				if(currentPrecedence == 2)
				{
					historyValue.replace(commaPos, commaPos + 2, " " + value + " (");
					historyValue.append(")");
				}
				if(currentPrecedence == 3)
				{
					historyValue.replace(commaPos, commaPos + 2, ") " + value + " (");
					commaPos = commaStack.pop();
					if(commaPos == 0)
						historyValue.insert(commaPos, "(");
					else
						historyValue.insert(commaPos + 2, "(");
					historyValue.append(")");
					commaStack.push(commaPos);
				}
				historyValue.append(" =");
			}
			else if(UNARY.indexOf(value) != -1)
			{
				if(value.equals(FACT))
				{
					operatorStack.pop();	
					operatorStack.push(10); //precedence of unary operators is set to highest to avoid additional brackets
					if(BINARY.indexOf(historyStack.peek()) != -1) //checks if the factorial is being operated on a binary expression
					{											  //adds brackets around the expression accordingly
						commaPos = commaStack.pop();
						if(commaPos == 0)
							historyValue.insert(commaPos, "(");
						else
							historyValue.insert(commaPos + 2, "(");
						historyValue.append(")!");
						commaStack.push(commaPos);
					}
					else
						historyValue.append(value); //appends the "!" sign at the end otherwise
				}
				else if(value.equals(CHANGE_SIGN))
				{
					if(BINARY.indexOf(historyStack.peek()) != -1 || historyStack.peek().equals(FACT)) //checks if the change sign was used on a binary or factorial expression
					{ 																				  //adds brackets around the expression accordingly
						lastOperatorValue = operatorStack.pop();
						operatorStack.push(10); //precedence of unary operators is set to highest to avoid additional brackets
						commaPos = commaStack.pop();
						if(commaPos == 0)
						{
							lastExpr = historyValue.substring(commaPos);
							historyValue.insert(commaPos, "-(");
						}
						else
						{
							lastExpr = historyValue.substring(commaPos + 2);
							historyValue.insert(commaPos + 2, "-(");
						}
						historyValue.append(")");
						commaStack.push(commaPos);
					}
					else if(historyStack.peek().equals(CHANGE_SIGN)) //checks to see if multiple change sign operations were performed in sequence
					{												 //and shuffles between the current string in historyValue and lastExpr
						int t = operatorStack.pop();
						operatorStack.push(lastOperatorValue);
						lastOperatorValue = t;
						commaPos = commaStack.pop();
						String temp = "";
						if(commaPos == 0)
							temp = historyValue.substring(commaPos);
						else
							temp = historyValue.substring(commaPos + 2);
						if(commaPos == 0)
							historyValue.replace(commaPos, historyValue.length(), lastExpr);
						else
							historyValue.replace(commaPos + 2, historyValue.length(), lastExpr);
						lastExpr = temp;
						commaStack.push(commaPos);
					}
					else								//if the change sign was used on an operand, constant or variable, simply adds a "-" sign in front of it
					{
						commaPos = commaStack.pop();
						if(commaPos == 0)
						{
							lastExpr = historyValue.substring(commaPos);
							if(historyValue.charAt(commaPos) == '-')
								historyValue.deleteCharAt(commaPos);
							else
								historyValue.insert(commaPos, "-");
						}
						else
						{
							lastExpr = historyValue.substring(commaPos + 2);
							if(historyValue.charAt(commaPos + 2) == '-')
								historyValue.deleteCharAt(commaPos + 2);
							else
								historyValue.insert(commaPos + 2, "-");
						}
						commaStack.push(commaPos);
					}
				}
				else		//for unary operations other than factorial and change sign, simply adds brackets around the operand (expression or constant)
				{	
					operatorStack.pop();
					operatorStack.push(10);
					commaPos = commaStack.pop();
					if(commaPos == 0)
						historyValue.insert(commaPos, value + "(");
					else
						historyValue.insert(commaPos + 2, value + "(");
					historyValue.append(")");
					commaStack.push(commaPos);
				}
				historyValue.append(" =");
			}
			else  //if the historyStack value is neither a binary of unary operator, appends the value to historyValue with the right placement of the comma
			{
				if(historyValue.length() == 0)
				{
					historyValue.append(value);
					operatorStack.push(10); //precedence of constants is set to highest to avoid additional brackets
					commaPos = 0;
					commaStack.push(commaPos);
				}
				else
				{
					commaPos = historyValue.length();
					operatorStack.push(10); //precedence of constants is set to highest to avoid additional brackets
					commaStack.push(commaPos);
					historyValue.append(", ");
					historyValue.append(value);
				}
			}
			historyStack.push(value);
		}
	}
	
	/**
	 * Checks if enough operands exist in the calcStack for a binary operation.
	 * If there are, does nothing. If not, substitutes missing operands with zeroes (0).
	 * @post. calcStack.size >= 2;
	 */
	private void enoughOperandsBinary()
	{
		MathValue top;
		
		if(calcStack.empty())
		{
			calcStack.push(new MathValue(0.0));
			calcStack.push(new MathValue(0.0));
			historyStack.push("0");
			historyStack.push("0");
		}
		else
		{
			top = calcStack.pop();
			if(calcStack.empty())
			{
				calcStack.push(top);
				calcStack.push(new MathValue(0.0));
				historyStack.push("0");
			}
			else
			{
				calcStack.push(top);
			}
		}
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
			historyResetFlag = false;
		}
	}
	
	/**
	 * Checks if an operand exists in the calcStack for a unary operation.
	 * If so, does nothing. If not, substitutes a zero (0).
	 * @post. calcStack.size >= 1;
	 */
	private void enoughOperandsUnary()
	{
		if(calcStack.empty())
		{
			calcStack.push(new MathValue(0.0));
			historyStack.push("0");
		}
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
			historyResetFlag = false;
		}
	}
	
	/**
	 * Updates the Value with the result of an arithmetic operation with the correct format type.
	 * @param result - the result of the arithmetic operation.
	 * @post. inputValue = result IF result == constant;
	 *        inputValue = INITIAL_DISPLAYED_VALUE IF result != constant;
	 */
	private void updateOperationValue(MathValue result)
	{
		DecimalFormat form = new DecimalFormat();
		if(result.isVariable())
			inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE); //displays the initial 0 if the result is a variable
		else
		{ //if the result is whole and not bigger than absolute value of integer, displays the result as an integer
			if(result.getValue()[1] == Math.floor(result.getValue()[1]) && !(result.getValue()[1] > Integer.MAX_VALUE) && 
					!(result.getValue()[1] < Integer.MIN_VALUE))
				inputValue = new StringBuilder(Integer.toString((int)result.getValue()[1])); 
			else //otherwise displays the results as a real, and in scientific notation if the value is bigger than 1E10 or smaller than 1E10
			{
				if(Math.abs(result.getValue()[1]) > 1E10 || Math.abs(result.getValue()[1]) < 1E-10)
					form.applyPattern("#.###E0");
				else
					form.applyLocalizedPattern("#.##########");
				inputValue = new StringBuilder(form.format(result.getValue()[1]));
			}
		}
	}
	
	/**
	 * Checks the precedence of the current operator, compared to previous ones.
	 * @param precedenceStack - Holds precedence values for operators 
	 * @param value - The string representation of the current binary operator
	 * @return 0 - If the current operator does not have precedence
	 * 		   1 - If the current operator has precedence over the left expression.
	 * 	       2 - If the current operator has precedence over the right expression.
	 * 		   3 - If the current operator has precedence over both expressions.
	 * @post. precedenceStack.size' = precedenceStack.size - 1;
	 *        precedenceStack.TOP = precedence of value;
	 */
	private int checkPrecedence(Stack<Integer> precedenceStack, String value)
	{
		int leftExp, rightExp;	//Precedence value of left and right expression relative to a binary operator
		int operatorValue;
		int precedence = 0;
		
		rightExp = precedenceStack.pop();
		leftExp = precedenceStack.pop();
		operatorValue = BINARY.indexOf(value);
		if(leftExp + 1 > operatorValue && rightExp + 1 > operatorValue)
		{
			precedence = 0;
		}
		if(leftExp + 1 < operatorValue && rightExp + 1 > operatorValue)
		{
			precedence = 1;
		}
		if(leftExp == 10 && rightExp > operatorValue && rightExp != 10)
		{
			precedence = 2;
		}
		if(rightExp == operatorValue && (operatorValue == 1 || operatorValue == 5))
		{
			precedence = 2;
		}
		if(leftExp + 1 > operatorValue && rightExp < operatorValue)
		{
			precedence = 2;
		}
		if(leftExp + 1 < operatorValue && rightExp + 1 < operatorValue)
		{
			precedence = 3;
		}
		precedenceStack.push(operatorValue);
		return precedence;
	}
}
