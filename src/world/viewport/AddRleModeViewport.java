package world.viewport;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import world.World;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
/*
public class AddRleModeViewport extends AbstractGridViewport {
    
    private World otherWorld = null;
    
    private Point otherWorldOffset = new Point(0,0); // unit : cells
    
    public AddRleModeViewport(World world, Dimension portSize) {
        super(world, portSize);
    }
    public AddRleModeViewport(World world) {
        this(world, null);
    }
    public void setOtherWorld(World w) {
        if(w.getWidth() > getWorld().getWidth()) return;
        if(w.getHeight()> getWorld().getHeight()) return;
 
        otherWorld = w;
    }
    
    @Override
    public void paintView(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, portSize.width, portSize.height);
        // paint mainWorld
        super.paintGrid(g, getWorld().getCellData());
        //
        if(otherWorld == null) return;
        offset.x += otherWorldOffset.x*cellSize;
        offset.y += otherWorldOffset.y*cellSize;
        super.paintGrid(g, otherWorld.getCellData());
        offset.x -= otherWorldOffset.x*cellSize;
        offset.y -= otherWorldOffset.y*cellSize;
    }

    @Override
    public void clicked(Point windowPosition, boolean button) {
        getWorld().step();
    }
    public World getOtherWorld() {
        return otherWorld;
    }
}
*/