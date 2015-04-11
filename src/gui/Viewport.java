package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface Viewport {

	public static final boolean WHEEL_UP = true;
	public static final boolean WHEEL_DOWN = false;

	public static final boolean RIGHT_BUTTON = true;
	public static final boolean LEFT_BUTTON = false;

	public static final Color LIVE_COLOR = Color.WHITE;
	public static final Color DEAD_COLOR = Color.BLACK;
	public static final Color LINE_COLOR = Color.GRAY;
    public static final Color SELECTED_CELL_COLOR = Color.RED;
    public static final Color BACKGROUND_COLOR = Color.GRAY;
    
    
	public void paintView(Graphics g);
	
	public void clicked(Point windowPosition, boolean button);
	public void wheeled(Point windowPosition, int amount, boolean direction);
	public void dragged(Point fromWindowPosition, Point toWindowPosition);
    public void keyPressed(int keyCode);
    public void keyTyped(char c);
    public void keyReleased(int keyCode);
	
	public void setPortSize(Dimension portSize);
    public Dimension getPortSize();
	public boolean portSizeInited();
    
    public Point getOffset();
}
