package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class BoundedWorld implements World{
	private final int width;
	private final int height;
	private final boolean[][] cellData;
	public BoundedWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.cellData = new boolean[width][height];
		step(); // TMP : init cellData
	}
	
	@Override
	public void step() {
		for( int i = 0 ; i < width ; i++) {
			for ( int j = 0 ; j < height ; j++ ) {
				cellData[i][j] = (Math.random() < 0.5);
			}
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
	public boolean getCell(int x, int y) {
		if(x >= width) return false;
		if(y >= height) return false;
		return cellData[x][y];
	}

	@Override
	public void toggle(int x, int y) {
		if(x >= this.width) return ;
		if(y >= this.height) return;
		
		cellData[x][y] = !cellData[x][y];
	}
	
}
