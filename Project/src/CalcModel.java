import java.util.Stack;

public class CalcModel 
{
	private StringBuilder inputValue;	//value displayed as user pressing numeric and decimal buttons
	private StringBuilder historyValue; //string representation of historyStack
	private Stack<String> historyStack; //history stored when user completes input or performs arithmetic operation
	private Stack<String> printStack;	//temp. stack used to print the contents of the historyStack
	private Stack<Double> calcStack;	//user input is stored in a stack data type
	private Stack<Double> preStack;		//stack that holds the numeric value of previous calculations and input
	
	private final String INITIAL_DISPLAYED_VALUE = "0";
	private final String INITIAL_DISPLAYED_HISTORY = "start new calculation";
	
	private final String BINARY = "+- */";	//The space in the middle is important for checking operator precedence
	private final String UNARY  = "sincos!";
	private final String FACT	= "!";
	private final String PI 	= "π";
	
	private boolean valueResetFlag;		//true if inputValue needs to be reset, false if inputValue needs to be appended.
	private boolean historyResetFlag; 	//true if historyValue needs to be reset, false if historyValue needs to be appended.
	
	private Stack<Integer> commaStack;	//tracks the location of the right most comma in the historyValue.
	
	/**
	 * Creates a new model with initial user and history values, and initializes empty stacks.
	 */
	CalcModel()
	{
		inputValue = new StringBuilder(INITIAL_DISPLAYED_VALUE);
		historyValue = new StringBuilder(INITIAL_DISPLAYED_HISTORY);
		calcStack = new Stack<Double>();
		historyStack = new Stack<String>();
		printStack = new Stack<String>();
		preStack = new Stack<Double>();
		commaStack = new Stack<Integer>();
		valueResetFlag = true;
		historyResetFlag = true;
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
				inputValue = new StringBuilder(buttonName);
				checkEqualSign();
				if(!buttonName.equals(INITIAL_DISPLAYED_VALUE))
					valueResetFlag = false;
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
		double top, secondTop, sum;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		sum = secondTop + top;
		calcStack.push(sum);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push("+");
		printHistory();
		updateOperationValue(sum);
	}
	
	/**
	 * Subtracts the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void subtract()
	{
		double top, secondTop, diff;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		diff = secondTop - top;
		calcStack.push(diff);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push("-");
		printHistory();
		updateOperationValue(diff);
	}
	
	/**
	 * Multiplies the 2 top values of the stack and pushes the result back to the top of the stack.
	 */
	public void multiply()
	{
		double top, secondTop, result;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		secondTop = calcStack.pop();
		result = secondTop * top;
		calcStack.push(result);
		preStack.push(secondTop);
		preStack.push(top);
		historyStack.push("*");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Divides the 2 top values of the stack and pushes the result back to the top of the stack.
	 * If a division by 0 occurs, the calculator is reset and an error message is displayed.
	 */
	public void divide()
	{
		double top, secondTop, result;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsBinary();
		top = calcStack.pop();
		if(top == 0)
		{
			clear();
			inputValue = new StringBuilder("MATH ERROR");
		}
		else
		{
			secondTop = calcStack.pop();
			result = secondTop / top;
			calcStack.push(result);
			preStack.push(secondTop);
			preStack.push(top);
			historyStack.push("/");
			printHistory();
			updateOperationValue(result);
		}
	}
	
	/**
	 * Calculates the sine of the top value of the stack and pushes the result back to the top of the stack.
	 */
	public void sine()
	{
		double top, result;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		result = Math.sin(top);
		calcStack.push(result);
		preStack.push(top);
		historyStack.push("sin");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Calculates the cosine of the top value of the stack and pushes the result back to the top of the stack.
	 */
	public void cosine()
	{
		double top, result;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		result = Math.cos(top);
		calcStack.push(result);
		preStack.push(top);
		historyStack.push("cos");
		printHistory();
		updateOperationValue(result);
	}
	
	/**
	 * Changes the sign of the inputValue without pushing the result into a stack.
	 */
	public void changeSign()
	{
		double value;
		value = Double.parseDouble(getInputValue());
		value *= -1;
		updateOperationValue(value);
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
			calcStack.push(Math.PI);
			historyStack.push(PI);
			multiply();
		}
		else
		{
			calcStack.push(Math.PI);
			historyStack.push(PI);
			printHistory();
			updateOperationValue(Math.PI);
		}
	}
	
	/**
	 * Calculates the factorial of the top value of the stack, and pushes the result back to the top of the stack.
	 * If the value is not a whole number, the calculator is reset and an error message is displayed.
	 */
	public void factorial()
	{
		double top, result;
		
		if(!valueResetFlag)
			enter();
		enoughOperandsUnary();
		top = calcStack.pop();
		if(top < 0 || top != Math.floor(top))
		{
			clear();
			inputValue = new StringBuilder("MATH ERROR");
		}
		else
		{
			result = 1;
			if((int)top == 0)
				result = 1;
			else
				for(int i = 1; i <= (int)top; i++)
					result *= i;
			calcStack.push(result);
			preStack.push(top);
			historyStack.push("!");
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
		calcStack.push(Double.parseDouble(inputValue.toString()));
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
		calcStack = new Stack<Double>();
		historyStack = new Stack<String>();
		printStack = new Stack<String>();
		preStack = new Stack<Double>();
		commaStack = new Stack<Integer>();
		valueResetFlag = true;
		historyResetFlag = true;
	}
	
	/**
	 * Undoes the last user operation. 
	 * If the user was typing an input, removes the last digit typed.
	 * If the user was not typing, removes the previous arithmetic or enter operation.
	 */
	public void undo()
	{
		String value;
		double top, secondTop;
		
		if(!valueResetFlag)
		{
			inputValue.deleteCharAt(inputValue.length() - 1);
			if(inputValue.length() == 0)
				valueResetFlag = true;
		}
		else if(inputValue.length() == 0)
		{
			printHistory();
			if(calcStack.empty())
				updateOperationValue(Double.parseDouble(INITIAL_DISPLAYED_VALUE));
			else
				updateOperationValue(calcStack.peek());
		}
		else if(historyStack.empty())
				clear();
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
			if(calcStack.empty())
				updateOperationValue(Double.parseDouble(INITIAL_DISPLAYED_VALUE));
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
		double top;
		
		if(calcStack.empty())
		{
			calcStack.push(0.0);
			calcStack.push(0.0);
			historyStack.push("0");
			historyStack.push("0");
		}
		else
		{
			top = calcStack.pop();
			if(calcStack.empty())
			{
				calcStack.push(top);
				calcStack.push(0.0);
				historyStack.push("0");
			}
			else
			{
				calcStack.push(top);
			}
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
			calcStack.push(0.0);
			historyStack.push("0");
		}
	}
	
	/**
	 * Updates the Value with the result of an arithmetic operation with the correct format type.
	 * @param result - the result of the arithmetic operation.
	 */
	private void updateOperationValue(double result)
	{
		if(result == Math.floor(result))
			inputValue = new StringBuilder(Integer.toString((int)result));
		else
			inputValue = new StringBuilder(Double.toString(result));
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
		if(leftExp + 1 == operatorValue && rightExp + 1 > operatorValue)
		{
			precedence = 2;
		}
		if(leftExp + 1 > operatorValue && rightExp + 1 < operatorValue)
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
