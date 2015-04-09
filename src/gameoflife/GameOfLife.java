package gameoflife;

import gui.MainFrame;
import io.Rle;
import io.Utils;
import io.WorldState;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import world.WarpedWorld;
import world.World;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class GameOfLife {
    static World w;
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

        w = new WarpedWorld(100, 100);
        JFrame jf =  new MainFrame(w);
        jf.setVisible(true);
        //System.out.println(Utils.readFile(new File("vc.rle")));
        WorldState ws = new Rle(Utils.readFile(new File("res/vc.rle"))).toWorldState();
        for (int i = 0; i < ws.data.length; i++) {
            for (int j = 0; j < ws.data[i].length; j++) {
                if (ws.data[i][j]) {
                    w.toggle(i, j);
                }
            }
        }
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                w.step();
            }
        }, 0, 10);
    }
}
