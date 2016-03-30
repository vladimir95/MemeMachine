import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.*;

public class TestButtonPress
{
	private CalcFavorites favorites;
	private CalcView view;
	private CalcController controller;
	private GraphController graphController;
	private DrawFunction drawFunction;
	private BufferedImage bi;
	private Graphics2D g;
	
	@Before
	public void setUp() throws Exception	
	{
		controller = new CalcController();
		view = new CalcView(controller);
		favorites = new CalcFavorites(view);
		graphController = new GraphController(controller, view);
		favorites = new CalcFavorites(view);
		bi = null;
		g = null;
	}
	
	@Test
	public void testButtonEnter()
	{
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "0");
		assertEquals(controller.getHistoryText(), "0");
	}
	
	@Test
	public void testButton0()
	{
		view.b0.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "0");
		assertEquals(controller.getHistoryText(), "0");
	}
	
	@Test
	public void testButton1()
	{
		view.b1.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "1");
		assertEquals(controller.getHistoryText(), "1");
	}
	
	@Test
	public void testButton2()
	{
		view.b2.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "2");
		assertEquals(controller.getHistoryText(), "2");
	}
	
	@Test
	public void testButton3()
	{
		view.b3.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "3");
		assertEquals(controller.getHistoryText(), "3");
	}
	
	@Test
	public void testButton4()
	{
		view.b4.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "4");
		assertEquals(controller.getHistoryText(), "4");
	}
	
	@Test
	public void testButton5()
	{
		view.b5.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "5");
		assertEquals(controller.getHistoryText(), "5");
	}
	
	@Test
	public void testButton6()
	{
		view.b6.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "6");
		assertEquals(controller.getHistoryText(), "6");
	}
	
	@Test
	public void testButton7()
	{
		view.b7.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "7");
		assertEquals(controller.getHistoryText(), "7");
	}
	
	@Test
	public void testButton8()
	{
		view.b8.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "8");
		assertEquals(controller.getHistoryText(), "8");
	}
	
	@Test
	public void testButton9()
	{
		view.b9.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "9");
		assertEquals(controller.getHistoryText(), "9");
	}
	
	@Test
	public void testButtonDot()
	{
		view.bdot.doClick();
		view.b9.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "0.9");
		assertEquals(controller.getHistoryText(), "0.9");
	}
	
	@Test
	public void testButtonClear()
	{
		view.b6.doClick();
		view.benter.doClick();
		view.bclear.doClick();
		assertEquals(controller.getDisplayText(), "0");
		assertEquals(controller.getHistoryText(), "Start New Calculation");
	}
	
	@Test
	public void testButtonAdd()
	{
		view.b5.doClick();
		view.benter.doClick();
		view.b9.doClick();
		view.badd.doClick();
		assertEquals(controller.getDisplayText(), "14");
		assertEquals(controller.getHistoryText(), "5 + 9 =");
	}

	@Test
	public void testButtonSubtract()
	{
		view.b9.doClick();
		view.benter.doClick();
		view.b3.doClick();
		view.bsubtract.doClick();
		assertEquals(controller.getDisplayText(), "6");
		assertEquals(controller.getHistoryText(), "9 - 3 =");
	}
	
	@Test
	public void testButtonMultiply()
	{
		view.b2.doClick();
		view.benter.doClick();
		view.b5.doClick();
		view.bmultiply.doClick();
		assertEquals(controller.getDisplayText(), "10");
		assertEquals(controller.getHistoryText(), "2 × 5 =");
	}
	
	@Test
	public void testButtonDivide()
	{
		view.b8.doClick();
		view.benter.doClick();
		view.b2.doClick();
		view.bdivide.doClick();
		assertEquals(controller.getDisplayText(), "4");
		assertEquals(controller.getHistoryText(), "8 ÷ 2 =");
	}
	
	@Test
	public void testButtonUndo()
	{
		view.b4.doClick();
		view.b3.doClick();
		view.bundo.doClick();
		assertEquals(controller.getDisplayText(), "4");
		assertEquals(controller.getHistoryText(), "");
	}

	@Test
	public void testButtonPi()
	{
		view.bpi.doClick();
		assertEquals(controller.getDisplayText(), "3.1415926536");
		assertEquals(controller.getHistoryText(), "π");
	}

	@Test
	public void testButtonSine()
	{
		view.bpi.doClick();
		view.b2.doClick();
		view.bdivide.doClick();
		view.bsin.doClick();
		assertEquals(controller.getDisplayText(), "1");
		assertEquals(controller.getHistoryText(), "sin(π ÷ 2) =");
	}
	
	@Test
	public void testButtonCosine()
	{
		view.bpi.doClick();
		view.bcos.doClick();
		assertEquals(controller.getDisplayText(), "-1");
		assertEquals(controller.getHistoryText(), "cos(π) =");
	}
	
	@Test
	public void testButtonFactorial()
	{
		view.b0.doClick();
		view.benter.doClick();
		view.bfact.doClick();
		assertEquals(controller.getDisplayText(), "1");
		assertEquals(controller.getHistoryText(), "0! =");
	}
	
	@Test
	public void testButtonSign()
	{
		view.b2.doClick();
		view.bsign.doClick();
		view.benter.doClick();
		assertEquals(controller.getDisplayText(), "-2");
		assertEquals(controller.getHistoryText(), "-2");
	}
	
	@Test
	public void testButtonx()
	{
		view.bx.doClick();
		assertEquals(controller.getDisplayText(), "0");
		assertEquals(controller.getHistoryText(), "x");
	}
	
	@Test
	public void testButtonSampleTest()
	{
		view.btest.doClick();
		assertEquals(controller.getDisplayText(), "0");
		assertEquals(controller.getHistoryText(), "cos(x) × x =");
	}
	
	@Test
	public void testButtonGraph()
	{
		drawFunction = new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, graphController);
		Container a = view.frame.getContentPane();
		view.bgraph.doClick();
		Container b = view.frame.getContentPane();
		assertEquals(a, b);
		
	}
	
	@Test
	public void testButtonFavouritesFromDrawFunction()
	{
		drawFunction = new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, graphController);
		drawFunction.openFavourites();
		Container a = view.frame.getContentPane();
		drawFunction = new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, graphController);
		drawFunction.bfavourites.doClick();
		Container b = view.frame.getContentPane();
		assertEquals(a, b);
	}
	
	@Test
	public void testButtonBackFromDrawFunction()
	{
		view.frame.setContentPane(view.mainpanel);
		Container a = view.frame.getContentPane();
		drawFunction = new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, graphController);
		drawFunction.bback.doClick();
		Container b = view.frame.getContentPane();
		assertEquals(a, b);
	}
	
	@Test
	public void testButtonAddToFavoritesFromDrawFunction()
	{
		controller.x();
		drawFunction = new DrawFunction(controller.getModel().getFunction(), controller.getModel().getEquation(), view, graphController);
		drawFunction.baddToFavourites.doClick();
		drawFunction.openFavourites();
		bi = new BufferedImage(view.frame.getContentPane().getWidth(), view.frame.getContentPane().getHeight(), BufferedImage.TYPE_INT_RGB);
		g = bi.createGraphics();
		view.frame.getContentPane().paint(g);
		Color c = new Color(bi.getRGB(bi.getWidth()-10, 70));
		assertEquals(c, view.functionColor);
	}
}
