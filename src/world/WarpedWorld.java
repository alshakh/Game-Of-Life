package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WarpedWorld implements World {

	private final int width;
	private final int height;
	private final boolean[][] firstGrid;
	private final boolean[][] secondGrid;

	private boolean[][] cellData;
	private boolean[][] swapData;

	public WarpedWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.firstGrid = new boolean[width][height];
		this.secondGrid = new boolean[width][height];

		this.cellData = firstGrid;
		this.swapData = secondGrid;
	}

	@Override
	public void step() {
		boolean[][] tmpData = cellData;
		cellData = swapData;
		swapData = tmpData;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int neighbors = countNeighbors(i,j, swapData);
				boolean thisCell = swapData[i][j];
				if(thisCell && (neighbors ==2 || neighbors ==3)) {
					cellData[i][j] = true;
				} else if (!thisCell && neighbors == 3 ) {
					cellData[i][j] = true;
				} else {
					cellData[i][j] = false;
				}
			}
		}
	}

	private int countNeighbors(int x, int y, boolean[][] data) {
		final int xm1 = (x-1 < 0 ? (x-1)+width : x-1);
		final int ym1 = (y-1 < 0 ? (y-1)+height : y-1);
		final int xp1 = (x+1 >= width ? (x+1) - width : x+1);
		final int yp1 = (y+1 >= height ? (y+1) - height : y+1);
		return   (data[xm1][ym1]?1:0) +( data[x][ym1]?1:0) + (data[xp1][ym1]?1:0)
		       + (data[xm1][y]  ?1:0) +                      (data[xp1][y]  ?1:0)
		       + (data[xm1][yp1]?1:0) + (data[x][yp1]?1:0) + (data[xp1][yp1]?1:0);
	}

	private int countNeighborsEdge(int x, int y, boolean[][] data) {
		throw new UnsupportedOperationException("Not supported yet.");
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
