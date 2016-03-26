
public class FavoriteValue
{
	private String equation;
	private double[] points;
	
	FavoriteValue(String name, double[] yPoints)
	{
		equation = name;
		points = yPoints;
	}
	
	public String getEquation()
	{
		return equation;
	}
	
	public double[] getPoints()
	{
		return points;
	}
}
