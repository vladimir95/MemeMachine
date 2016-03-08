
public class MathValue
{
	private final double MAX_RANGE = 50.0;
	private final double MIN_RANGE = -50.0;
	public static final int NUMBER_OF_POINTS = 1000;
	
	private boolean isVariable;
	private double[] value = new double[NUMBER_OF_POINTS];
	
	MathValue(double constatnt)
	{
		for(int i = 0; i < NUMBER_OF_POINTS; i++)
			value[i] = constatnt;
		isVariable = false;
	}
	
	MathValue()
	{
		for(int i = 0; i < NUMBER_OF_POINTS; i++)
			value[i] = ((MAX_RANGE - MIN_RANGE) / NUMBER_OF_POINTS) * i + MIN_RANGE;
		isVariable = true;
	}
	
	MathValue(double[] result, boolean variable)
	{
		value = result;
		isVariable = variable;
	}
	
	public double[] getValue()
	{
		return value;
	}
	
	public boolean isVariable()
	{
		return isVariable;
	}
}
