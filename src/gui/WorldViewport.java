package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import world.World;

/**
 * Paint and interact with world.
 * 
 * 
 * Two kinds of positions both measured in pixels
 * windowPosition : a position relative to JComponent
 * gridPosition : the windowPosition without translating ( offset ).
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WorldViewport implements Viewport {

	public static final int MIN_CELL_SIZE = 2;
	public static final Point NOT_A_CELL = new Point(-1, -1);

	private final World worldRef;
	private Dimension portSize;

	private int cellSize;
	private Point offset;

	public WorldViewport(World world, Dimension portSize) {
		this.worldRef = world;
		this.portSize = portSize;
		this.offset = new Point(0,0);
		this.cellSize = 10;
	}

	public WorldViewport(World world) {
		this(world, null);
	}

	
	@Override
	public void paintView(Graphics g) {
		if (!sizeInited()) {
			return;
		}
		// background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, portSize.width, portSize.height);
		
		
		final int worldWidth = worldRef.getWidth();
		final int worldHeight = worldRef.getHeight();

		for (int i = 0; i < worldWidth; i++) {
			for (int j = 0; j < worldHeight; j++) {
				Color cellColor;
				if (worldRef.isAliveCell(i, j)) {
					cellColor = Viewport.ALIVE_COLOR;
				} else {
					cellColor = Viewport.DEAD_COLOR;
				}
				g.setColor(cellColor);
				g.fillRect(offset.x + i * cellSize, offset.y + j * cellSize, cellSize, cellSize);
			}
		}
		g.setColor(LINE_COLOR);
		for (int i = 0; i <= worldWidth; i++) {
			g.drawLine(offset.x + cellSize * i, offset.y + 0, offset.x + cellSize * i, offset.y + worldHeight * cellSize);
		}
		for (int i = 0; i <= worldHeight; i++) {
			g.drawLine(offset.x + 0, offset.y + cellSize * i,offset.x + worldWidth * cellSize, offset.y + cellSize * i);
		}
	}

	@Override
	public void clicked(Point windowPosition) {
		if (!sizeInited()) {
			return;
		}
		final Point gridPosition= antiClick(windowPosition);

		worldRef.toggle(gridPosition.x,gridPosition.y);
	}

	/**
	 * if the grid is wheeled will zoom and maintain the cursor windowPosition relative to grid.
	 * @param windowPosition
	 * @param amount
	 * @param direction 
	 */
	@Override
	public void wheeled(Point windowPosition, int amount, boolean direction) {
		if (!sizeInited()) {
			return;
		}
		
		final Point cell = antiClick(windowPosition);
		if(cell == NOT_A_CELL) return;
		//
		// The following info will be used to maintain cursor location
		final Point gridPosition = toGridPosition(windowPosition);
		final double oldCursorCellPosRatio_X =(gridPosition.x % cellSize)/((double)cellSize);
		final double oldCursorCellPosRatio_Y =(gridPosition.y % cellSize)/((double)cellSize);
		//

		final double amountFactor = 3.0; // based on my wheel every "click" is amount 3
		final double zoomFactor = 0.1; // every zoom step is 10% bigger/smaller (cellSize = cellSize*1.1)

		final int oldCellSize = cellSize;
		cellSize *= (direction ? 1 : -1) * (zoomFactor * amount / amountFactor) + 1;
		if (oldCellSize == cellSize) {
			// this happens because cellSize is so small that 
			// 1.1*cellSize is rounded off to integer
			cellSize += 1; // add just a little bit to fix it. ( collective)
		}
		if (cellSize < MIN_CELL_SIZE) {
			cellSize = MIN_CELL_SIZE;
		}
		
		// Fix Cursor location on grid
		final Point gridCursorPointer = new Point(cell.x*cellSize+(int)(oldCursorCellPosRatio_X*cellSize),
					cell.y*cellSize+(int)(oldCursorCellPosRatio_Y*cellSize));
		offset.x = windowPosition.x - gridCursorPointer.x;
		offset.y = windowPosition.y - gridCursorPointer.y;
		
		System.out.println(cellSize);
	}

	@Override
	public void dragged(Point fromWindowPosition, Point toWindowPosition) {
		
		final Point cell = antiClick(fromWindowPosition);
		if(cell == NOT_A_CELL) return;
		
		offset.x += (toWindowPosition.x - fromWindowPosition.x);
		offset.y += (toWindowPosition.y - fromWindowPosition.y);
	}

	@Override
	public void setSize(Dimension portSize) {
		this.portSize = portSize;
	}

	@Override
	public boolean sizeInited() {
		return (portSize != null);
	}

	/**
	 * returns the gridPosition number that the cursor on
	 *
	 * @param pos
	 * @return
	 */
	private Point antiClick(Point windowPosition) {
		final Point gridPosition = toGridPosition(windowPosition);
		final Point cell = new Point(gridPosition.x / cellSize,
					     gridPosition.y / cellSize);
		if(cell.x >= worldRef.getHeight()) return NOT_A_CELL;
		if(cell.y >= worldRef.getWidth()) return NOT_A_CELL;
		if(cell.x < 0) return NOT_A_CELL;
		if(cell.y < 0) return NOT_A_CELL;
		// (windowPosition.x - x_offset) is transforming the click into coord system where 0 is the edge of grid
		return cell;
	}
	private Point toGridPosition(Point windowPosition) {
		return new Point(windowPosition.x - offset.x, windowPosition.y - offset.y);
	}
}
