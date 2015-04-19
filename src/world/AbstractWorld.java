package world;

import world.defined.ConwayRule;
import io.WorldState;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public abstract class AbstractWorld implements World {

    private final boolean[][] firstGrid;
    private final boolean[][] secondGrid;

    private final int[][] neighborCountData;
    private final Rule rule;

    private final List<WorldListener> listeners;
    // useing to buffers to store data.
    private static final boolean FIRST_GRID = true;
    private static final boolean SECOND_GRID = false;
    private boolean currentBuffer = FIRST_GRID;
    private final int dim; // unit : cell

    public AbstractWorld(int dim, Rule rule) {
        this.dim = dim;
        this.firstGrid = new boolean[dim][dim];
        this.secondGrid = new boolean[dim][dim];

        this.neighborCountData = new int[dim][dim];

        this.listeners = new ArrayList<>();
        
        this.rule = rule;
    }
    public AbstractWorld(int dim){
        this(dim,ConwayRule.INSTANCE);
    }

    @Override
    public void attachListener(WorldListener wl) {
        listeners.add(wl);
    }

    @Override
    public void detachListener(WorldListener wl) {
        listeners.remove(wl);
    }

    public void notifyListeners(WorldEvent we) {
        for (WorldListener wl : listeners) {
            switch (we) {
                case WORLD_STEPPED:
                    wl.worldStepped();
                    break;
                case WORLD_TOGGLED:
                    wl.worldToggled();
                    break;
            }
        }
    }

    @Override
    public void step() {
        notifyListeners(WorldEvent.WORLD_STEPPED);
        doStep();
    }
    
    protected abstract  void doStep(); // DESING_PATTERN : template

    protected abstract void fillNeighborCountData(boolean[][] fromGrid);

    /**
     * get the data that is currently showing.
     *
     * @return
     */
    @Override
    public final boolean[][] getCellData() {
        if (currentBuffer == FIRST_GRID) {
            return firstGrid;
        } else {
            return secondGrid;
        }
    }

    /**
     * get the previous data.
     *
     * @return
     */
    protected final boolean[][] getPreData() {
        if (currentBuffer == SECOND_GRID) {
            return firstGrid;
        } else {
            return secondGrid;
        }
    }
    
    protected final int[][] getNeighborCountData() {
        return neighborCountData;
    }

    protected final void swapBuffer() {
        currentBuffer = !currentBuffer;
    }

    @Override
    public void step(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }
    }

    public Rule getRule(){
        return rule;
    }
    @Override
    public int getDim() {
        return this.dim;
    }

    @Override
    public boolean isAliveCell(int x, int y) {
        if (x >= dim) {
            return false;
        }
        if (y >= dim) {
            return false;
        }
        return getCellData()[x][y];
    }

    @Override
    public void toggle(int x, int y) {
        notifyListeners(WorldEvent.WORLD_TOGGLED);
        if (x >= dim) {
            return;
        }
        if (y >= dim) {
            return;
        }
        boolean[][] cellData = getCellData();
        cellData[x][y] = !cellData[x][y];
    }

    @Override
    public WorldState toWorldState(boolean clone) {
        // if()
        return new WorldState(dim,rule,getCellData());
    }
    
    @Override
    public void kill(int x, int y) {
        notifyListeners(WorldEvent.WORLD_TOGGLED);
        if (x >= dim) {
            return;
        }
        if (y >= dim) {
            return;
        }
        getCellData()[x][y] = false;
    }

    @Override
    public void live(int x, int y) {
        notifyListeners(WorldEvent.WORLD_TOGGLED);
        if (x >= dim) {
            return;
        }
        if (y >= dim) {
            return;
        }
        getCellData()[x][y] = true;
    }
}
