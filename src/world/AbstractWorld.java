package world;

import io.WorldState;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public abstract class AbstractWorld implements World {

    private final int width;
    private final int height;

    private final boolean[][] firstGrid;
    private final boolean[][] secondGrid;

    private final int[][] neighborCountData;

    private final List<WorldListener> listeners;
    // useing to buffers to store data.
    private static final boolean FIRST_GRID = true;
    private static final boolean SECOND_GRID = false;
    private boolean currentBuffer = FIRST_GRID;

    public AbstractWorld(int width, int height) {
        this.width = width;
        this.height = height;
        this.firstGrid = new boolean[width][height];
        this.secondGrid = new boolean[width][height];

        this.neighborCountData = new int[width][height];

        this.listeners = new ArrayList<WorldListener>();
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
    protected abstract  void doStep();

    protected abstract void fillNeighborCountData(boolean[][] grid);

    /**
     * get the data that is currently showing.
     *
     * @return
     */
    protected final boolean[][] getCellData() {
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

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean isAliveCell(int x, int y) {
        if (x >= width) {
            return false;
        }
        if (y >= height) {
            return false;
        }
        return getCellData()[x][y];
    }

    @Override
    public void toggle(int x, int y) {
        notifyListeners(WorldEvent.WORLD_TOGGLED);
        if (x >= this.width) {
            return;
        }
        if (y >= this.height) {
            return;
        }
        boolean[][] cellData = getCellData();
        cellData[x][y] = !cellData[x][y];
    }

    public WorldState toWorldState(boolean clone) {
        // if()
        return new WorldState(width,height,"B2/S23",getCellData());
    }
}
