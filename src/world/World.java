package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface World {
	
	public void step();
	public void step(int n);
	
	public int getWidth();
	public int getHeight();
	
	public boolean isAliveCell(int x, int y);
	public void toggle(int x,int y);
}
