
public class MathValue
{
	private final double MAX_RANGE = 50.0;
	private final double MIN_RANGE = -50.0;
	public static final int NUMBER_OF_POINTS = 1000;
	
	private boolean isVariable;
	private double[] value = new double[NUMBER_OF_POINTS];
	
	/**
	 * Creates a constant value
	 * @param constatnt - the constant value to be added to each point in the array
	 */
	public MathValue(double constatnt)
	{
		for(int i = 0; i < NUMBER_OF_POINTS; i++)
			value[i] = constatnt;
		isVariable = false;
	}
	
	/**
	 * Creates a variable value corresponding to y=x function
	 */
	public MathValue()
	{
		for(int i = 0; i < NUMBER_OF_POINTS; i++)
			value[i] = ((MAX_RANGE - MIN_RANGE) / NUMBER_OF_POINTS) * i + MIN_RANGE;
		isVariable = true;
	}
	
	/**
	 * Creates a value that is the result of an operation, the value is either a variable or a constant
	 * @param result - the array resulting from an operation
	 * @param variable - defines the resulting value as either a constant or a variable
	 */
	public MathValue(double[] result, boolean variable)
	{
		value = result;
		isVariable = variable;
	}
	
	/**
	 * Returns the double array of this value
	 * @return - double array of points of this value
	 */
	public double[] getValue()
	{
		return value;
	}
	
	/**
	 * Tells if this value is a variable or not
	 * @return - true if value is variable, false if not
	 */
	public boolean isVariable()
	{
		return isVariable;
	}
}
