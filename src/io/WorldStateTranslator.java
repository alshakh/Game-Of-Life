package io;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public interface WorldStateTranslator {

	/**
	 *
	 * @param ws
	 * @return
	 */
	public Rle toRle(WorldState ws);
}
