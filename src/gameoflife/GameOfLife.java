package gameoflife;

import gui.MainFrame;
import world.RandomWorld;
import world.WarpedWorld;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class GameOfLife {

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new MainFrame(new WarpedWorld(20, 20)).setVisible(true);

//        //control time : 4.2 +- 0.1 s
//        long time = System.currentTimeMillis();
//        world.World w = new WarpedWorld(1000,1000);
//        w.step(1000);
//        System.out.println((System.currentTimeMillis() - time)/1000.0);
    }
}
