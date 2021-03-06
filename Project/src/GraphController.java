
public class GraphController {
	
	private CalcController controller;
	private CalcView view;
	private CalcFavorites favoritesView;
	
	/**
	 * Creates a new graph controller.
	 * The graph controller is the mediator between the 3 views (main, graph and favorites).
	 * @param calcController - the calculator controller. Used as a reference to get data from the model.
	 * @param theView - the main view. Used as a referece that is passed to either graph view or favorites view.
	 */
	public GraphController(CalcController calcController, CalcView theView)
	{
		controller =  calcController;
		view = theView;
		favoritesView = new CalcFavorites(view);
	}
	
	/**
	 * Creates a new instance of DrawFunction and displays it in the main application frame.
	 */
	public void graph()
	{
		new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, this);
	}
	
	/**
	 * Displays the Favorites View in the main application frame.
	 * @param graph - The calling view of this function. Used to get back to the caller view.
	 */
	public void openFavorites(DrawFunction graph)
	{
		favoritesView.displayFavorites(graph);
	}
	
	/**
	 * Displays the Favorites View in the main application frame,
	 * with the new Favorite Value with parameters functionName and yPoints, 
	 * added to the favorites list.
	 * @param graph - The calling view of this function. Used to get back to the caller view.
	 * @param functionName - The String representation of the function.
	 * @param yPoints - the y - axis data points of the function.
	 */
	public void addToFavourites(DrawFunction graph, String functionName, double[] yPoints)
	{
		favoritesView.addToFavourites(graph, functionName, yPoints);
	}
}
