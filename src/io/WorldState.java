package io;

import world.Rule;
import world.World;
import world.WorldListener;

/**
 * DESIGN_PATTERN : Lazy implementation for toRle. using cached
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WorldState implements World {

    public final boolean[][] data;
    private final int dim;
    private final Rule rule;
    private Rle myCachedRle = null;

    public WorldState(int dim, Rule rule, boolean[][] data) {
        this.data = data;
        this.dim = dim;
        this.rule = rule;
    }

    public Rle toRle() {
        if (myCachedRle != null) {
            return myCachedRle;
        }
        myCachedRle = Rle.createRle(dim, rule, data);
        return myCachedRle;
    }

    @Override
    public void step() {
        throw new UnsupportedOperationException("No stepping with WorldState");
    }

    @Override
    public void step(int n) {
        step();
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public boolean[][] getCellData() {
        return data;
    }

    @Override
    public boolean isAliveCell(int x, int y) {
        return data[x][y];
    }

    @Override
    public void toggle(int x, int y) {
        throw new UnsupportedOperationException("WorldState objects are immutable");
    }

    @Override
    public void kill(int x, int y) {
        throw new UnsupportedOperationException("WorldState objects are immutable");
    }

    @Override
    public void live(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attachListener(WorldListener wl) {
        throw new UnsupportedOperationException("WorldState objects are immutable");
    }

    @Override
    public void detachListener(WorldListener wl) {
        throw new UnsupportedOperationException("WorldState objects are immutable");
    }

    @Override
    public WorldState toWorldState(boolean clone) {
        return this;
    }
    
    public Rule getRule(){
        return rule;
    }
    
    public void zero() {
         
    }
}
