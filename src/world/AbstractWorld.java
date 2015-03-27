package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public abstract class AbstractWorld implements World {

	private final int width;
	private final int height;
	
	private final boolean[][] firstGrid;
	private final boolean[][] secondGrid;

	// references to be used in all methods
	protected boolean[][] cellData; 
	protected boolean[][] swapData;

	public AbstractWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.firstGrid = new boolean[width][height];
		this.secondGrid = new boolean[width][height];

		this.cellData = firstGrid;
		this.swapData = secondGrid;
	}
	
	@Override
	public abstract void step();
	
	protected void swapData(){
			boolean[][] tmpData = cellData;
		cellData = swapData;
		swapData = tmpData;
	}
	protected int countNeighbors(int x, int y, boolean[][] data) {
		final int xm1 = (x-1 < 0 ? (x-1)+width : x-1);
		final int ym1 = (y-1 < 0 ? (y-1)+height : y-1);
		final int xp1 = (x+1 >= width ? (x+1) - width : x+1);
		final int yp1 = (y+1 >= height ? (y+1) - height : y+1);
		return   (data[xm1][ym1]?1:0) +( data[x][ym1]?1:0) + (data[xp1][ym1]?1:0)
		       + (data[xm1][y]  ?1:0) +                      (data[xp1][y]  ?1:0)
		       + (data[xm1][yp1]?1:0) + (data[x][yp1]?1:0) + (data[xp1][yp1]?1:0);
	}

	@Override
	public void setp(int n) {
		for (int i = 0; i < n; i++) {
			step();
		}
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public boolean isAliveCell(int x, int y) {
		if (x >= width) {
			return false;
		}
		if (y >= height) {
			return false;
		}
		return cellData[x][y];
	}

	@Override
	public void toggle(int x, int y) {
		if (x >= this.width) {
			return;
		}
		if (y >= this.height) {
			return;
		}

		cellData[x][y] = !cellData[x][y];
	}
	
}
