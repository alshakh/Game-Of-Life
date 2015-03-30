package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WarpedWorld extends AbstractWorld {

	public WarpedWorld(int width, int height) {
		super(width, height);
	}

	@Override
	public void step() {
		swapBuffer(); // now cellData & preData is swapped
		boolean[][] cellData = super.getCellData();
		boolean[][] preData = super.getPreData();
		
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				int neighbors = countNeighbors(i,j, preData);
				boolean thisCell = preData[i][j];
				// if cell alive 2||3 ==> alive else ==> dead
				// if cell dead     3 ==> alive else ==> dead
				if(thisCell && (neighbors ==2 || neighbors ==3)) {
					cellData[i][j] = true;
				} else cellData[i][j] = !thisCell && neighbors == 3;
			}
		}
	}
}
