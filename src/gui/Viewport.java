package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import world.World;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface Viewport {
	
	public static final boolean WHEEL_UP = true;
	public static final boolean WHEEL_DOWN = false;
	
	public static final Color ALIVE_COLOR = Color.WHITE;
	public static final Color DEAD_COLOR = Color.BLACK;
	public static final Color LINE_COLOR = Color.GRAY;
	
	public void paintView(Graphics g);
	public void clicked(Point windowPosition);
	public void wheeled(Point windowPosition,int amount, boolean direction);
	public void dragged(Point fromWindowPosition,Point toWindowPosition);
	public void setSize(Dimension portSize);
	public boolean sizeInited();
}
