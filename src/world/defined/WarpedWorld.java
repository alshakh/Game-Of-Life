package world.defined;

import java.util.Arrays;
import world.AbstractWorld;
import world.Rule;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class WarpedWorld extends AbstractWorld {

    public WarpedWorld(int dim, Rule rule) {
        super(dim,rule);
    }
    public WarpedWorld(int dim) {
        super(dim,ConwayRule.INSTANCE);
    }

    @Override
    protected void doStep() {
        swapBuffer(); // now cellData & preData is swapped
        boolean[][] cellData = super.getCellData();
        boolean[][] preData = super.getPreData();
        int[][] neighborCount = super.getNeighborCountData();

        fillNeighborCountData(preData);
        // from here on, can be parallelized

        for (int i = 0; i < getDim(); i++) {
            for (int j = 0; j < getDim(); j++) {

                int neighbors = neighborCount[i][j];

                cellData[i][j] = super.getRule().newValue(preData[i][j], neighborCount[i][j]);
            }
        }
    }

    @Override
    protected void fillNeighborCountData(boolean[][] grid) {
        // can be parallelized

        final int[][] neighborData = getNeighborCountData();

        // empty neighborCount
        for (int i = 0; i < getDim(); i++) {
            Arrays.fill(neighborData[i], 0);
        }

        // fill neighborCount
        int im1, ip1, jm1, jp1;
        for (int i = 0; i < getDim(); i++) {
            for (int j = 0; j < getDim(); j++) {
                im1 = ( i == 0 ? getDim()-1 : i - 1);
                jm1 = ( j == 0 ? getDim()-1 : j - 1);
                ip1 = ( i == getDim()-1 ? 0 : i + 1 );
                jp1 = ( j == getDim()-1 ? 0 : j + 1 );
                       
                if (grid[i][j]) {
                    neighborData[im1][jm1] += 1;
                    neighborData[im1][j] += 1;
                    neighborData[im1][jp1] += 1;

                    neighborData[i][jm1] += 1;
                    neighborData[i][jp1] += 1;

                    neighborData[ip1][jm1] += 1;
                    neighborData[ip1][j] += 1;
                    neighborData[ip1][jp1] += 1;
                }
            }
        }

    }
    

}
