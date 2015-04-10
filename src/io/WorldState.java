package io;

import java.util.ArrayList;

/**
 * DESIGN_PATTERN : Lazy implementation for toRle. using cached
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WorldState {

    public final boolean[][] data;
    private final int dim;
    private final String rule;
    private Rle myCachedRle = null;

    public WorldState(int dim, String rule, boolean[][] data) {
        this.data = data;
        this.dim = dim;
        this.rule = rule;
    }
    public Rle toRle() {
        if(myCachedRle != null) return myCachedRle;
        myCachedRle = Rle.createRle(dim, rule, data);
        return myCachedRle;
    }
}
