
public class FavoriteValue
{
	private String equation; //The name of the stored function
	private double[] points; //The y - axis data points of the stored function
	
	/**
	 * Creates a new favorite value
	 * @param name - the function name
	 * @param yPoints - the y - axis data points of the function
	 */
	public FavoriteValue(String name, double[] yPoints)
	{
		equation = name;
		points = yPoints;
	}
	
	/**
	 * Gets the function name of the stored favorite value
	 * @return - String representation of the function name
	 */
	public String getEquation()
	{
		return equation;
	}
	
	/**
	 * Gets the y - axis data points of the stored favorite value
	 * @return - data points of type double[]
	 */
	public double[] getPoints()
	{
		return points;
	}
}
