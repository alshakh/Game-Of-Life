package world;

import java.util.Arrays;

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
		int[][] neighborCount = super.getNeighborCountData();
		
		fillNeighborCountData(preData);
		// from here on, can be parallelized
		
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				
				int neighbors = neighborCount[i][j];
				
				boolean thisCell = preData[i][j];
				// if cell alive 2||3 ==> alive else ==> dead
				// if cell dead     3 ==> alive else ==> dead
				if (thisCell && (neighbors == 2 || neighbors == 3)) {
					cellData[i][j] = true;
				} else {
					cellData[i][j] = !thisCell && neighbors == 3;
				}
			}
		}
	}

	@Override
	protected void fillNeighborCountData(boolean[][] grid) {
		// can be parallelized

		final int[][] neighborData = getNeighborCountData();
		final int width = super.getWidth();
		final int height = super.getHeight();

		// empty neighborCount
		for (int i = 0; i < getWidth(); i++) {
			Arrays.fill(neighborData[i], 0);
		}

		// fill neighborCount
		// -- Not edge cases
		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				if (super.isAliveCell(i, j)) {
					neighborData[i - 1][j - 1] += 1;
					neighborData[i - 1][j] += 1;
					neighborData[i - 1][j + 1] += 1;

					neighborData[i][j - 1] += 1;
					neighborData[i][j + 1] += 1;

					neighborData[i + 1][j - 1] += 1;
					neighborData[i + 1][j] += 1;
					neighborData[i + 1][j + 1] += 1;
				}
			}
		}

		// edge cases ( warped around the grid )
		int im1, jm1, ip1, jp1;
		int i = 0;
		int j = 0;
		while (i != width - 1 || j != height - 1) {
			im1 = (i - 1 < 0 ? (i - 1) + width : i - 1);
			jm1 = (i - 1 < 0 ? (j - 1) + height : j - 1);
			ip1 = (i + 1 >= width ? (i + 1) - width : i + 1);
			jp1 = (i + 1 >= height ? (j + 1) - height : j + 1);

			if (super.isAliveCell(i, j)) {
				neighborData[im1][jm1] += 1;
				neighborData[im1][j] += 1;
				neighborData[im1][jp1] += 1;

				neighborData[i][jm1] += 1;
				neighborData[i][jp1] += 1;

				neighborData[ip1][jm1] += 1;
				neighborData[ip1][j] += 1;
				neighborData[ip1][jp1] += 1;
			}

			if (i != width - 1) {
				i = width - 1;
			} else if (j != height - 1) {
				j = height - 1;
			}
		}
	}
}
