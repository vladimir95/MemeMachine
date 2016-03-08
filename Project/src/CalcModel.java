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
	 */
	public void numericButton(String buttonName)
	{
		if(valueResetFlag)
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
		else if(inputValue.indexOf(".") == -1 || !buttonName.equals("."))
			inputValue.append(buttonName);
		
		if (historyResetFlag)
		{
			historyValue = new StringBuilder();
		}
	}
	
	/**
	 * Adds the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void sum()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] + top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());
		calcStack.push(result);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(0) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Subtracts the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void subtract()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] - top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());
		calcStack.push(result);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(1) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Multiplies the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void multiply()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] =secondTop.getValue()[i] * top.getValue()[i];
		MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());
		calcStack.push(result);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push(BINARY.charAt(3) + "");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Divides the 2 top values of the stack and pushes the result back to the top of the stack.
	 * If a division by 0 occurs, the calculator is reset and an error message is displayed.
	 */
	public void divide()
	{
		MathValue top, secondTop;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		boolean divisionByZero = false;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			if(top.getValue()[i] == 0)
			{
				resultArray[i] = Double.POSITIVE_INFINITY;
				divisionByZero = true;
			}
			else
				resultArray[i] =secondTop.getValue()[i] / top.getValue()[i];
		if(!top.isVariable() && !secondTop.isVariable() && divisionByZero)
		{
			mathErrorFlag = true;
			historyStack.push(BINARY.charAt(5) + "");
			calcStack.push(secondTop);
			calcStack.push(top);
			inputValue = new StringBuilder("MATH ERROR");
			printHistory();
		}
		else
		{
			MathValue result = new MathValue(resultArray, top.isVariable() || secondTop.isVariable());
			calcStack.push(result);
			preStack.push(secondTop);
			preStack.push(top);
			historyStack.push(BINARY.charAt(0) + "");
			printHistory();
			updateOperationValue(result);
		}
	}
	
	/**
	 * Calculates the sine of the top value of the stack and pushes the result back to the top of the stack.
	 */
	public void sine()
	{
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] = Math.sin(top.getValue()[i]);
		MathValue result = new MathValue(resultArray, top.isVariable());
		calcStack.push(result);
		preStack.push(top);
		historyStack.push(UNARY.substring(0, 3));
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Calculates the cosine of the top value of the stack and pushes the result back to the top of the stack.
	 */
	public void cosine()
	{
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			resultArray[i] = Math.cos(top.getValue()[i]);
		MathValue result = new MathValue(resultArray, top.isVariable());
		calcStack.push(result);
		preStack.push(top);
		historyStack.push(UNARY.substring(0, 3));
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Changes the sign of the inputValue without pushing the result into a stack.
	 */
	public void changeSign()
	{
		String value;
		MathValue top;
		double[] resultArray = new double[MathValue.NUMBER_OF_POINTS];
		
		if(!valueResetFlag)
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
		else
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
	
	/**
	 * Pushes the Pi constant into the stack.
	 * If user was typing an input, pushes the input into the stack and multiplies it by pi (3*pi).
	 */
	public void pi()
	{
		if(!valueResetFlag)
		{
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
	 */
	public void factorial()
	{
		MathValue top;
		MathValue result = new MathValue(1);
		boolean undefinedValueCalculated = false;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		for(int i = 0; i < MathValue.NUMBER_OF_POINTS; i++)
			if(top.getValue()[i] < 0 || top.getValue()[i] != Math.floor(top.getValue()[i]))
			{
				result.getValue()[i] = Double.NaN;
				undefinedValueCalculated = true;
			}
			else
			{
				if(top.getValue()[i] > 170)
					result.getValue()[i] = Double.POSITIVE_INFINITY;
				else
				{
					for(int j = 1; j <= (int)top.getValue()[i]; j++)
						result.getValue()[i] *= j;
				}
			}
		if(!top.isVariable() && undefinedValueCalculated)
		{
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
	 */
	public void enter()
	{
		calcStack.push(new MathValue(Double.parseDouble(inputValue.toString())));
		valueResetFlag = true;
		if(inputValue.charAt(0) == '.')
			inputValue.insert(0, '0');
		if(inputValue.charAt(inputValue.length() - 1) == '.')
			inputValue.append('0');
		historyStack.push(inputValue.toString());
		printHistory();
		historyResetFlag = false;
		
	}
	
	/**
	 * Clears the data in all stacks and resets the history and input value to the initial configuration.
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
	 */
	public void undo()
	{
		String value;
		MathValue top, secondTop;
		
		if(!valueResetFlag)
		{
			inputValue.deleteCharAt(inputValue.length() - 1);
			if(inputValue.length() == 1 && inputValue.charAt(0) == '-')
				inputValue.deleteCharAt(inputValue.length() - 1);
			if(inputValue.length() == 1 && inputValue.charAt(0) == '0')
				valueResetFlag = true;
			if(inputValue.length() == 0)
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
		else if(mathError())
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
			if(BINARY.indexOf(value) != -1)
			{
				top = preStack.pop();
				secondTop = preStack.pop();
				calcStack.push(secondTop);
				calcStack.push(top);
			}
			if(UNARY.indexOf(value) != -1)
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
	
	public double[] getFunction()
	{
		return calcStack.peek().getValue();
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
	 */
	private void printHistory()
	{
		String value;
		int commaPos;
		int currentPrecedence;
		int lastOperatorValue = 10;
		String lastExpr = "";
		
		Stack<Integer> operatorStack = new Stack<Integer>();	//Holds precedence values for operators
		historyValue = new StringBuilder();
		while(!historyStack.empty())
		{
			value = historyStack.pop();
			printStack.push(value);
		}
		while(!printStack.empty())
		{
			value = printStack.pop();
			checkEqualSign();
			if(BINARY.indexOf(value) != -1)
			{
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
					operatorStack.push(10);
					if(BINARY.indexOf(historyStack.peek()) != -1)
					{
						commaPos = commaStack.pop();
						if(commaPos == 0)
							historyValue.insert(commaPos, "(");
						else
							historyValue.insert(commaPos + 2, "(");
						historyValue.append(")!");
						commaStack.push(commaPos);
					}
					else
						historyValue.append(value);
				}
				else if(value.equals(CHANGE_SIGN))
				{
					if(BINARY.indexOf(historyStack.peek()) != -1 || historyStack.peek().equals(FACT))
					{
						lastOperatorValue = operatorStack.pop();
						operatorStack.push(10);
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
					else if(historyStack.peek().equals(CHANGE_SIGN))
					{
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
					else
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
				else
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
			else
			{
				if(historyValue.length() == 0)
				{
					historyValue.append(value);
					operatorStack.push(10);
					commaPos = 0;
					commaStack.push(commaPos);
				}
				else
				{
					commaPos = historyValue.length();
					operatorStack.push(10);
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
	 */
	private void updateOperationValue(MathValue result)
	{
		DecimalFormat form = new DecimalFormat();
		if(result.isVariable())
			inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
		else
		{
			if(result.getValue()[0] == Math.floor(result.getValue()[0]) && !(result.getValue()[0] > Integer.MAX_VALUE) && 
					!(result.getValue()[0] < Integer.MIN_VALUE))
				inputValue = new StringBuilder(Integer.toString((int)result.getValue()[0]));
			else
			{
				if(Math.abs(result.getValue()[0]) > 1E10 || Math.abs(result.getValue()[0]) < 1E-10)
					form.applyPattern("#.###E0");
				else
					form.applyLocalizedPattern("#.##########");
				inputValue = new StringBuilder(form.format(result.getValue()[0]));
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
