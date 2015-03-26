package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface World {
	public void step();
	
	public int getWidth();
	public int getHeight();
	
	public boolean getCell(int x, int y);
	public void toggle(int x,int y);
}
