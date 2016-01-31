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
	
	private final String BINARY = "+-*/";
	private final String UNARY  = "sincos!";
	private final String FACT	= "!";
	
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
		else
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
		enoughOperands();
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
	 * Pushes the user value to the top of the valueStack and the historyStack.
	 */
	public void enter()
	{
		calcStack.push(Double.parseDouble(inputValue.toString()));
		valueResetFlag = true;
		historyStack.push(inputValue.toString());
		printHistory();
		historyResetFlag = false;
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
				historyValue.replace(commaPos, commaPos + 1, " " + value);
				commaPos = commaStack.pop();
				if(commaPos == 0)
					historyValue.insert(commaPos, "(");
				else
					historyValue.insert(commaPos + 2, "(");
				historyValue.append(")");
				commaStack.push(commaPos);
				historyValue.append(" =");
			}
			else if(UNARY.indexOf(value) != -1)
			{
				if(value.equals(FACT))
					historyValue.append(value);
				else
				{	
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
					commaPos = 0;
					commaStack.push(commaPos);
				}
				else
				{
					commaPos = historyValue.length();
					commaStack.push(commaPos);
					historyValue.append(", ");
					historyValue.append(value);
				}
			}
			historyStack.push(value);
		}
	}
	
	/**
	 * Checks if enough operands exist in the calcStack.
	 * If there are, does nothing. If not, substitutes missing operands with zeroes (0).
	 */
	private void enoughOperands()
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
}
