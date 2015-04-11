package world.viewport;

import gui.Viewport;
import gui.WorldPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public abstract class AbstractGridViewport implements Viewport {

    public static final int MIN_CELL_SIZE = 2;
    public static final Point NOT_A_CELL = new Point(-1, -1);

    protected Dimension portSize; // unit : px

    protected int cellSize;
    protected Point offset;
    
    protected WorldPane myPane;

    public AbstractGridViewport(WorldPane wp, Dimension portSize) {
        this.myPane = wp;
        this.portSize = portSize;
        this.offset = new Point(0, 0);
        this.cellSize = 10;
    }

    public AbstractGridViewport(WorldPane wp) {
        this(wp,null);
    }

    /**
     * Gets the dimension of the grid.
     *
     * @return the side length of the grid
     */
    public abstract int getGridDim();

    @Override
    public Point getOffset(){
        return offset;
    }
    
    @Override
    public int getCellSize(){
        return cellSize;
    }
    public void setCellSize(int cellSize){
        this.cellSize = cellSize;
    }
    @Override
    public void setPortSize(Dimension portSize) {
        this.portSize = portSize;
    }

    @Override
    public boolean portSizeInited() {
        return (portSize != null);
    }

    /**
     * returns the gridPosition number that the cursor on
     *
     * @param windowPosition
     * @return
     */
    public Point antiClick(Point windowPosition) {
        final Point gridPosition = toGridPosition(windowPosition);
        final Point cell = new Point(gridPosition.x / cellSize,
                                     gridPosition.y / cellSize);
        if (cell.x >= getGridDim()
            || cell.y >= getGridDim()
            || cell.x < 0
            || cell.y < 0) {
            return NOT_A_CELL;
        }
        return cell;
    }

    protected void paintBackground(Graphics g){
                //background
        g.setColor(Viewport.BACKGROUND_COLOR);
        g.fillRect(0, 0, portSize.width, portSize.height);
    }
    protected void paintGrid(Graphics g,boolean[][] data){
        paintGrid(g, data,Viewport.LIVE_COLOR,false);
    }
    
    protected void paintGrid(Graphics g, boolean[][] data, boolean paintDead) {
        paintGrid(g,data,Viewport.LIVE_COLOR,paintDead);
    }
    
    protected void paintGrid(Graphics g, boolean[][] data,Color liveColor,boolean paintDead) {
        if (!portSizeInited()) {
            return;
        }
        
        final int worldWidth = data.length;
        final int worldHeight = data[0].length;

        g.setColor(liveColor);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j]) {
                    g.setColor(liveColor);
                    g.fillRect(offset.x + i * cellSize, offset.y + j * cellSize, cellSize, cellSize);
                } else if(paintDead){
                    g.setColor(Viewport.DEAD_COLOR);
                    g.fillRect(offset.x + i * cellSize, offset.y + j * cellSize, cellSize, cellSize);
                }
            }
        }

        g.setColor(LINE_COLOR);
        for (int i = 0; i <= worldWidth; i++) {
            g.drawLine(offset.x + cellSize * i, offset.y + 0, offset.x + cellSize * i, offset.y + worldHeight * cellSize);
        }
        for (int i = 0; i <= worldHeight; i++) {
            g.drawLine(offset.x + 0, offset.y + cellSize * i, offset.x + worldWidth * cellSize, offset.y + cellSize * i);
        }
    }

    public Point toGridPosition(Point windowPosition) {
        // (windowPosition.x - offset.x) is transforming the click into coord system where 0 is the edge of grid
        return new Point(windowPosition.x - offset.x, windowPosition.y - offset.y);
    }

    @Override
    public Dimension getPortSize() {
        return portSize;
    }

    /**
     * if the grid is wheeled will zoom and maintain the cursor windowPosition
     * relative to grid.
     *
     * @param windowPosition
     * @param amount
     * @param direction
     */
    @Override
    public void wheeled(Point windowPosition, int amount, boolean direction) {
        if (!portSizeInited()) {
            return;
        }

        final Point cell = antiClick(windowPosition);
        if (cell == NOT_A_CELL) {
            return;
        }
        //
        // The following info will be used to maintain cursor location
        final Point gridPosition = toGridPosition(windowPosition);
        final double oldCursorCellPosRatio_X = (gridPosition.x % cellSize) / ((double) cellSize);
        final double oldCursorCellPosRatio_Y = (gridPosition.y % cellSize) / ((double) cellSize);
        //

        final double amountFactor = 3.0; // based on my wheel every "click" is amount 3
        final double zoomFactor = 0.1; // every zoom step is 10% bigger/smaller (cellSize = cellSize*1.1)

        final int oldCellSize = cellSize;
        cellSize *= (direction ? 1 : -1) * (zoomFactor * amount / amountFactor) + 1;
        if (oldCellSize == cellSize) {
            // this happens because cellSize is so small that 
            cellSize += 1; // add just a little bit to fix it. ( collective)
        }
        if (cellSize < MIN_CELL_SIZE) {
            cellSize = MIN_CELL_SIZE;
        }

        // Fix Cursor location on grid
        final Point gridCursorPointer = new Point(cell.x * cellSize + (int) (oldCursorCellPosRatio_X * cellSize),
                                                  cell.y * cellSize + (int) (oldCursorCellPosRatio_Y * cellSize));
        offset.x = windowPosition.x - gridCursorPointer.x;
        offset.y = windowPosition.y - gridCursorPointer.y;
    }

    @Override
    public void dragged(Point fromWindowPosition, Point toWindowPosition) {

        final Point cell = antiClick(fromWindowPosition);
        if (cell == NOT_A_CELL) {
            return;
        }

        offset.x += (toWindowPosition.x - fromWindowPosition.x);
        offset.y += (toWindowPosition.y - fromWindowPosition.y);
    }
    @Override
    public void keyPressed(int keyCode) {
    }

    @Override
    public void keyTyped(char c) {
    }

    @Override
    public void keyReleased(int keyCode) {
    }
}
