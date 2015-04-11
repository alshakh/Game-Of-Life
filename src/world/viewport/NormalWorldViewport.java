package world.viewport;

import gui.Viewport;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import world.World;

/**
 * Paint and interact with world.
 *
 *
 * Two kinds of positions both measured in pixels windowPosition : a position
 relative to JComponent cell : the windowPosition without translating
 ( offset ).
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class NormalWorldViewport extends AbstractGridViewport {

    World myWorld;
    public NormalWorldViewport(World world, Dimension portSize) {
        super(portSize);
        myWorld = world;
    }

    public NormalWorldViewport(World world) {
        this(world,null);
    }

    @Override
    public void paintView(Graphics g) {
        if (!portSizeInited()) {
            return;
        }
        super.paintBackground(g);

        paintGrid(g, myWorld.getCellData(),true);
    }

    @Override
    public void clicked(Point windowPosition, boolean button) {
        if (!portSizeInited()) {
            return;
        }

        if (button == LEFT_BUTTON) {
            myWorld.step();
        } else {
            final Point cell = antiClick(windowPosition);
            if (cell == NOT_A_CELL) {
                return;
            }
            myWorld.toggle(cell.x, cell.y);
        }
    }

    @Override
    public int getGridDim() {
        return myWorld.getDim();
    }

    

}
