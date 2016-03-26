import javax.swing.*;

public class GraphController {
	
	private CalcController controller;
	private CalcView view;
	private CalcFavorites favoritesView;
	
	
	public GraphController(CalcController calcController, CalcView theView)
	{
		controller =  calcController;
		view = theView;
		favoritesView = new CalcFavorites(view);
	}
	
	public void graph()
	{
		new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, this);
	}
	
	public void openFavorites(DrawFunction graph)
	{
		favoritesView.displayFavorites(graph);
	}
	
	public void addToFavourites(DrawFunction graph, String functionName, double[] yPoints)
	{
		favoritesView.addToFavourites(graph, functionName, yPoints);
	}
}
