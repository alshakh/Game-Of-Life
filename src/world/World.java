package world;

import io.WorldState;

/**
 * DESIGN_PATTERN : observer : attach/detach
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface World {
	
	public void step();
	public void step(int n);
	
	public int getDim();
	
    public boolean[][] getCellData();
	public boolean isAliveCell(int x, int y);
	public void toggle(int x,int y);
    
    public void kill(int x,int y);
    public void live(int x,int y);
    
    public void attachListener(WorldListener wl);
    public void detachListener(WorldListener wl);
    
    public WorldState toWorldState(boolean clone);

    public void zero();
}
