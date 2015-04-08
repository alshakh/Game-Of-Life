package io;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface RleTranslator {
	
	/**
	 *
	 * @param rle
	 * @return
	 */
	public WorldState toWorldState(Rle rle);
}
