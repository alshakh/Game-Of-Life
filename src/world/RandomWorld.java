package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class RandomWorld extends AbstractWorld {

	public RandomWorld(int dim) {
		super(dim);
        this.step();
	}

	@Override
	protected void doStep() {
		boolean[][] cellData = getCellData();
		for (int i = 0; i < getDim(); i++) {
			for (int j = 0; j < getDim(); j++) {
				cellData[i][j] = Math.random() < 0.5;
			}
		}
	}

	/**
	 * No use here.
	 * @param grid 
	 */
	@Override
	protected void fillNeighborCountData(boolean[][] grid) {
	}

 
	
}
