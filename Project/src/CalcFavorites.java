import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CalcFavorites extends JPanel
{
	//Set number of favorites to 15
	final int MAX_LIST_SIZE = 15;
	
	//The favorites list. Stores the favorites that were added from the graph
	private List<FavoriteValue> favorites = new ArrayList<FavoriteValue>(); 
	
	private CalcView view;
	private DrawFunction graphPanel;
	JPanel favoritesPanel;
	JTextField message;
	private String full = "The Favorites List is Full";
	private String empty = "No Favorites Yet";
	
	/**
	 * Creates a new favorites view
	 * The favorites view displays the saved favorites in its own JPanel.
	 * @param theView - a reference to the main view. Used to display this favorites view on the main frame.
	 */
	public CalcFavorites(CalcView theView)
	{
		view = theView;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); //Setting the layout of the favorites view
        setBackground(Color.BLACK);
        
        //Main Panel of favorites
        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.X_AXIS)); 
        board.setBackground(Color.BLACK);
        
        //Create a Panel for a message in case if Favorites are full/empty
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        messagePanel.setBackground(Color.BLACK);
        
        //Format the message text itself and add it to the Panel
        message = new JTextField(full);
        message.setPreferredSize(new Dimension(250, 40));
		message.setEditable(false);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setOpaque(false);
		message.setBackground(Color.BLACK);
		message.setForeground(view.buttonTextColor);
		message.setFocusable(false);
		message.setBorder(null);
		messagePanel.add(message);
		message.setVisible(false);
        
		
		
		favoritesPanel = new JPanel();
	    //set # of favorites to 15
	    favoritesPanel.setLayout(new GridLayout(15,1,0,3));
	    favoritesPanel.setBackground(Color.BLACK);
	    
	    //Add an extra Panel for the Back button
        JPanel button = new JPanel();
        button.setBackground(Color.BLACK);
        button.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        
        //Create and configure and the button itself
        JButton back = new JButton("< Back");
        new ButtonAdapter(back) {
			public void pressed(){back();}};
        back.setPreferredSize(new Dimension(130,40));
        back.setBackground(view.functionColor);
        back.setForeground(view.buttonTextColor);
        back.setFocusable(false);
        back.setBorder(null);
        back.setFont(back.getFont().deriveFont(20f));
        
        //Keep the same fonts on the button and the message
        message.setFont(back.getFont().deriveFont(20f));
       
        //Add everything to the Panels
        button.add(back);
        board.add(button);
        board.add(messagePanel);
        add(board);
        add(favoritesPanel);
	}
	
	/**
	 * Sets the display to the graph view
	 */
	protected void back()
	{
		view.frame.setContentPane(graphPanel);
		view.frame.revalidate();
	}

	/**
	 * Sets the display to this favorites view
	 * @param theGraph - graph panel is passed as the caller of the favorites view. Used to go back to this view from favorites view.
	 */
	public void displayFavorites(DrawFunction theGraph)
	{
		graphPanel = theGraph;
		showFavoritesList();
		view.frame.setContentPane(this);
		view.frame.revalidate();
	}
	
	/**
	 * Checks if the function of the current graph is already present in the favorites list.
	 * The function will be added only if it is NOT already in the list, to avoid duplicate entries.
	 * @param theGraph - graph panel is passed as the caller of the favorites view. Used to go back to this view from favorites view.
	 * @param name - String representation of the function name
	 * @param yPoints - The y - axis data points, used to draw the graph
	 */
	public void addToFavourites(DrawFunction theGraph, String name, double[] yPoints)
	{
		boolean found = false;
		if(favorites.size() < MAX_LIST_SIZE)
		{
			for(int i = 0; i < favorites.size(); i++)
			{
				if(favorites.get(i).getEquation().equals(name))
					found = true;
			}
			if(!found)
				favorites.add(new FavoriteValue(name, yPoints));
		}
		displayFavorites(theGraph);
	}
	
	/**
	 * Updates and displays the current favorite graphs in this view.
	 */
	public void showFavoritesList()
	{
		//Add the message if favorites are full or empty
		if(favorites.size() == MAX_LIST_SIZE){
			message.setText(full);
			message.setVisible(true);
		}
		else if(favorites.size() == 0){
			message.setText(empty);
			message.setVisible(true);
		}
		else{
			message.setVisible(false);
		}
		
		
		favoritesPanel.removeAll();
		for(int i = 0; i < favorites.size(); i++)
		{
			//Generate the Panel with a favorite item
			JPanel favoriteItem = new JPanel();
			favoriteItem.setBackground(Color.BLACK);
			
			//Add graph and delete buttons and configure both buttons
			JButton delete = new JButton("Delete");
			JButton graph = new JButton("Graph");
		
			new ButtonAdapter(delete, i) {
				public void pressed(){delete(buttonIndex);}};
			new ButtonAdapter(graph, i) {
				public void pressed(){graph(buttonIndex);}};
		
			graph.setPreferredSize(new Dimension(100,40));
			graph.setBackground(view.enterColor);
			graph.setForeground(view.buttonTextColor);
			graph.setFocusable(false);
			graph.setBorder(null);
			graph.setFont(graph.getFont().deriveFont(20f));
			
			delete.setPreferredSize(new Dimension(100,40));
			delete.setBackground(view.functionColor);
			delete.setForeground(view.buttonTextColor);
			delete.setFocusable(false);
			delete.setBorder(null);
			delete.setFont(delete.getFont().deriveFont(20f));
			
			//Add a Text Field for the graph name to be stored in favorites
			JTextField graphName = new JTextField(favorites.get(i).getEquation());
			graphName.setEditable(false);
			graphName.setBackground(Color.BLACK);
			graphName.setForeground(Color.WHITE);
			graphName.setPreferredSize(new Dimension(415,40));
			graphName.setFont(new Font("Arial Rounded", Font.BOLD,18));
			
			
			//Set Layout of the item and add elements to it
			favoriteItem.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
			favoriteItem.add(graphName);
			favoriteItem.add(graph);
			favoriteItem.add(delete);
			
			
			favoritesPanel.add(favoriteItem);
		}
	}
	
	/**
	 * Deletes the specific favorite entry from the favorites list
	 * @param index - the index of the entry in the favorites list to be deleted
	 */
	public void delete(int index)
	{
		favorites.remove(index);
		showFavoritesList();
		view.frame.setContentPane(this);
		view.frame.revalidate();
	}
	
	/**
	 * graphs the specific favorite entry in the favorites list
	 * @param index - the index of the entry in the favorites list to be graphed
	 */
	public void graph(int index)
	{
		view.frame.setContentPane(new DrawFunction(favorites.get(index).getPoints(), 
				favorites.get(index).getEquation(), view, graphPanel.getGraphController()));
		
		view.frame.revalidate();
	}
}