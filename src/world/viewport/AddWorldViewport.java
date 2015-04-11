package world.viewport;

import gui.Viewport;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.nio.channels.Channels;
import world.World;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class AddWorldViewport extends AbstractGridViewport {

    private World mainWorld;
    private World toAddWorld = null;

    private final Point toAddOffset = new Point(0, 0); // unit : cells

    public AddWorldViewport(World world, Dimension portSize) {
        super(portSize);
        mainWorld = world;
    }

    public AddWorldViewport(World world) {
        this(world, null);
    }

    @Override
    public void paintView(Graphics g) {
        super.paintBackground(g);
        // paint mainWorld
        super.paintGrid(g, mainWorld.getCellData(),true);
        //
        if (toAddWorld == null) {
            return;
        }
        final int stashOffsetX = offset.x;
        final int stashOffsetY = offset.y;
        offset.x += toAddOffset.x * cellSize;
        offset.y += toAddOffset.y * cellSize;
        super.paintGrid(g, toAddWorld.getCellData(), Viewport.SELECTED_CELL_COLOR, false);
        offset.x = stashOffsetX;
        offset.y = stashOffsetY;
    }

    @Override
    public void clicked(Point windowPosition, boolean button) {
        mainWorld.step();
    }

    public void AddWorld(World w) {
        toAddWorld = w;
        toAddOffset.x = 0;
        toAddOffset.y = 0;
    }

    @Override
    public int getGridDim() {
        return mainWorld.getDim();
    }

    public void translate(int x, int y) {
        final int toAddWorldDim = (toAddWorld == null ? 0 : toAddWorld.getDim());
        if (toAddOffset.x + x + toAddWorldDim <= getGridDim()) {
            toAddOffset.x += x;
        }
        if (toAddOffset.y + y + toAddWorldDim <= getGridDim()) {
            toAddOffset.y += y;
        }

        if (toAddOffset.x < 0) {
            toAddOffset.x = 0;
        }
        if (toAddOffset.y < 0) {
            toAddOffset.y = 0;
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.translate(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                this.translate(1, 0);
                break;
            case KeyEvent.VK_UP:
                this.translate(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                this.translate(0, 1);
                break;
            case KeyEvent.VK_ENTER:
                mergeNewWorld();
                toAddWorld = null;
                
                break;
        }
    }

    public void mergeNewWorld() {
        for (int i = 0; i < toAddWorld.getDim(); i++) {
            for (int j = 0; j < toAddWorld.getDim(); j++) {
                if(toAddWorld.isAliveCell(i, j)) {
                    mainWorld.live(i+toAddOffset.x, j+toAddOffset.y);
                }
            }
        }
    }
}
