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
        // background
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, portSize.width, portSize.height);


        for (int i = 0; i < myWorld.getDim(); i++) {
            for (int j = 0; j < myWorld.getDim(); j++) {
                Color cellColor;
                if (myWorld.isAliveCell(i, j)) {
                    cellColor = Viewport.LIVE_COLOR;
                } else {
                    cellColor = Viewport.DEAD_COLOR;
                }
                g.setColor(cellColor);
                g.fillRect(offset.x + i * cellSize, offset.y + j * cellSize, cellSize, cellSize);
            }
        }
        g.setColor(LINE_COLOR);
        for (int i = 0; i <= myWorld.getDim(); i++) {
            g.drawLine(offset.x + cellSize * i, offset.y + 0, offset.x + cellSize * i, offset.y + myWorld.getDim() * cellSize);
        }
        for (int i = 0; i <= myWorld.getDim(); i++) {
            g.drawLine(offset.x + 0, offset.y + cellSize * i, offset.x + myWorld.getDim() * cellSize, offset.y + cellSize * i);
        }
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
