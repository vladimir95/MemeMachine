import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcFavorites extends JPanel
{
	final int MAX_LIST_SIZE = 10;
	List<FavoriteValue> favorites = new ArrayList<FavoriteValue>();
	CalcView view;
	DrawFunction graphPanel;
	
	JPanel favoritesPanel;
	
	CalcFavorites(CalcView theView)
	{
		view = theView;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        JPanel button = new JPanel();
        favoritesPanel = new JPanel();
        favoritesPanel.setLayout(new GridLayout(10, 3));
        favoritesPanel.setBackground(Color.BLACK);
        button.setOpaque(false);
        button.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        
        JButton back = new JButton("< Back");
        
        new ButtonAdapter(back) {
			public void pressed(){back();}};
        
        back.setPreferredSize(new Dimension(200,40));
        back.setBackground(view.functionColor);
        back.setForeground(view.buttonTextColor);
        back.setFocusable(false);
        back.setBorder(null);
        back.setFont(back.getFont().deriveFont(20f));
       
        button.add(back);
        add(button);
        add(favoritesPanel);
	}
	
	protected void back()
	{
		view.frame.setContentPane(graphPanel);
		view.frame.revalidate();
	}

	public void displayFavorites(DrawFunction theGraph)
	{
		graphPanel = theGraph;
		showFavoritesList();
		view.frame.setContentPane(this);
		view.frame.revalidate();
	}
	
	public void addToFavourites(DrawFunction theGraph, String name, double[] yPoints)
	{
		if(favorites.size() < MAX_LIST_SIZE)
		{
			favorites.add(new FavoriteValue(name, yPoints));
		}
		displayFavorites(theGraph);
	}
	
	public void showFavoritesList()
	{
		
		favoritesPanel.removeAll();
		for(int i = 0; i < favorites.size(); i++)
		{
			JPanel favoriteItem = new JPanel();
			favoriteItem.setBackground(Color.BLUE);
			JButton delete = new JButton("Delete");
			JButton graph = new JButton("Graph");
		
			new ButtonAdapter(delete, i) {
				public void pressed(){delete(buttonIndex);}};
			new ButtonAdapter(graph, i) {
				public void pressed(){graph(buttonIndex);}};
			
			graph.setPreferredSize(new Dimension(150,40));
			graph.setBackground(view.functionColor);
			graph.setForeground(view.buttonTextColor);
			graph.setFocusable(false);
			graph.setBorder(null);
			//graph.setFont(back.getFont().deriveFont(20f));
			
			delete.setPreferredSize(new Dimension(150,40));
			delete.setBackground(view.functionColor);
			delete.setForeground(view.buttonTextColor);
			delete.setFocusable(false);
			delete.setBorder(null);
			//delete.setFont(back.getFont().deriveFont(20f));
			
			JTextField graphName = new JTextField(favorites.get(i).getEquation(),30);
			favoriteItem.setLayout(new GridLayout(1,3,5,0));
			graphName.setBackground(Color.BLACK);
			graphName.setForeground(Color.WHITE);
			graphName.setSize(new Dimension(320,40));
			graphName.setFont(new Font("Arial Rounded", Font.BOLD,18));
			
			favoriteItem.add(graphName);
			favoriteItem.add(graph);
			favoriteItem.add(delete);
			//favourites.add(Box.createRigidArea(new Dimension(0,5)));
			
			
			//favoritesPanel.add(Box.createRigidArea(new Dimension(0,600)));
			favoritesPanel.add(favoriteItem);
		}
	}
	
	public void delete(int index)
	{
		favorites.remove(index);
		showFavoritesList();
		view.frame.setContentPane(this);
		view.frame.revalidate();
	}
	
	public void graph(int index)
	{
		view.frame.setContentPane(new DrawFunction(favorites.get(index).getPoints(), favorites.get(index).getEquation(), view, graphPanel.getGraphController()));
		view.frame.revalidate();
	}
}
