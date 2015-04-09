package io;

import java.util.ArrayList;

/**
 * DESIGN_PATTERN : Lazy implementation for toRle. using cached
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WorldState {

    public final boolean[][] data;
    private final int X;
    private final int Y;
    private final String rule;
    private Rle myCachedRle = null;

    public WorldState(int xSize, int ySize, String rule, boolean[][] data) {
        this.data = data;
        this.X = xSize;
        this.Y = ySize;
        this.rule = rule;
    }
    public Rle toRle() {
        if(myCachedRle != null) return myCachedRle;
        myCachedRle = Rle.createRle(X, Y, rule, data);
        return myCachedRle;
    }
}
