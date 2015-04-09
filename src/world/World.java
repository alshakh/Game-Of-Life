package world;

/**
 * DESIGN_PATTERN : observer : attach/detach
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface World {
	
	public void step();
	public void step(int n);
	
	public int getWidth();
	public int getHeight();
	
	public boolean isAliveCell(int x, int y);
	public void toggle(int x,int y);
    
    public void attachListener(WorldListener wl);
    public void detachListener(WorldListener wl);
}
