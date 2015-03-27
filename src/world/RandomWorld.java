package world;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class RandomWorld extends AbstractWorld {

	public RandomWorld(int width, int height) {
		super(width, height);
	}

	@Override
	public void step() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				cellData[i][j] = (Math.random() < 0.5 ? true : false);
			}
		}
	}
	
}
