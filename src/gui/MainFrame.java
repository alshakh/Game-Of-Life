package gui;

import io.Rle;
import io.UnsupportedRuleException;
import io.Utils;
import io.WorldState;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import world.World;
import world.viewport.AddWorldViewport;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class MainFrame extends javax.swing.JFrame {

    private final World world;
    private final Timer timer = new Timer();
    private final int WATCH_INTERVAL = 300;

    private final JComponent[] compo_arr;

    public MainFrame(World world) {
        this.world = world;
        initComponents();
        worldPane1.setFocusable(true);
        compo_arr = new JComponent[]{
            stepBtn, autoFastBtn, autoStepBtn, exportRleBtn, addRleFileBtn, newGameBtn, clearBtn
        };
    }

    void startByAdding(WorldState addThis) throws AddWorldViewport.TooBigWorld {
        worldPane1.addWorld(addThis);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        worldPane1 = new WorldPane(world);
        stepBtn = new javax.swing.JButton();
        autoFastBtn = new javax.swing.JButton();
        addRleFileBtn = new javax.swing.JButton();
        newGameBtn = new javax.swing.JButton();
        exportRleBtn = new javax.swing.JButton();
        autoStepBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        pupulateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 10, 0, 0));

        javax.swing.GroupLayout worldPane1Layout = new javax.swing.GroupLayout(worldPane1);
        worldPane1.setLayout(worldPane1Layout);
        worldPane1Layout.setHorizontalGroup(
            worldPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        worldPane1Layout.setVerticalGroup(
            worldPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        stepBtn.setText("Step");
        stepBtn.setFocusable(false);
        stepBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepBtnActionPerformed(evt);
            }
        });

        autoFastBtn.setText("Auto Fast");
        autoFastBtn.setFocusable(false);
        autoFastBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoFastBtnActionPerformed(evt);
            }
        });

        addRleFileBtn.setText("Add RLE file..");
        addRleFileBtn.setFocusable(false);
        addRleFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRleFileBtnActionPerformed(evt);
            }
        });

        newGameBtn.setText("New Game..");
        newGameBtn.setFocusable(false);
        newGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBtnActionPerformed(evt);
            }
        });

        exportRleBtn.setText("Export RLE file..");
        exportRleBtn.setFocusable(false);
        exportRleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportRleBtnActionPerformed(evt);
            }
        });

        autoStepBtn.setText("Auto Step");
        autoStepBtn.setFocusable(false);
        autoStepBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoStepBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.setFocusable(false);
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        pupulateBtn.setText("Populate Randomly");
        pupulateBtn.setFocusable(false);
        pupulateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pupulateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(worldPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stepBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addRleFileBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newGameBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportRleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(autoFastBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(autoStepBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pupulateBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(worldPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newGameBtn)
                        .addGap(35, 35, 35)
                        .addComponent(addRleFileBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportRleBtn)
                        .addGap(69, 69, 69)
                        .addComponent(pupulateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn)
                        .addGap(45, 45, 45)
                        .addComponent(stepBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoFastBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(autoStepBtn)
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //// use by the auto btns
    private void disableAllExcept(JComponent obj) {
        for (JComponent jc : compo_arr) {
            if (jc.equals(obj)) {
                continue;
            }
            jc.setEnabled(false);
        }
    }

    private void enableAllBtns() {
        for (JComponent jc : compo_arr) {
            jc.setEnabled(true);
        }
    }
    private boolean currentlyWorking = false;
    private TimerTask currentTT = null;
    ///

    private void stepBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepBtnActionPerformed
        world.step();
    }//GEN-LAST:event_stepBtnActionPerformed

    private void autoFastBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoFastBtnActionPerformed
        if (!currentlyWorking) {
            disableAllExcept(autoFastBtn);
            currentTT = new TimerTask() {
                @Override
                public void run() {
                    world.step();
                }
            };
            timer.scheduleAtFixedRate(currentTT, 0, 10);
            autoFastBtn.setText("Stop");
            currentlyWorking = true;
        } else {
            autoFastBtn.setText("Auto Fast");
            enableAllBtns();
            currentTT.cancel();
            timer.purge();
            currentlyWorking = false;
        }
    }//GEN-LAST:event_autoFastBtnActionPerformed

    private void addRleFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRleFileBtnActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Run Length Encodeing", "rle"));
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File selectedFile = fileChooser.getSelectedFile();
            World newWorld = null;
            try {
                newWorld = new Rle(Utils.readFile(selectedFile)).toWorldState();
            } catch (UnsupportedRuleException ex) {
                JOptionPane.showMessageDialog(this, "The rule in " + selectedFile.getParent() + " is not supported", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newWorld == null) {
                return;
            }
            worldPane1.addWorld(newWorld);
            // worldPane1.requestFocus()
        } catch (AddWorldViewport.TooBigWorld ex) {
            worldPane1.toNormal();
            JOptionPane.showMessageDialog(this, "The world you're trying to add is too large for this game. Please start new game from your Rle file");
        }
    }//GEN-LAST:event_addRleFileBtnActionPerformed

    private void newGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBtnActionPerformed
        JFrame ngf = new NewGameFrame();
        ngf.setLocationRelativeTo(this);
        ngf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_newGameBtnActionPerformed

    private void exportRleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportRleBtnActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Run Length Encodeing", "rle"));
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = fileChooser.getSelectedFile();
        try {
            Utils.saveFile(selectedFile,world.toWorldState(false).toRle().getContents());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot save file : " + ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_exportRleBtnActionPerformed


    private void autoStepBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoStepBtnActionPerformed
        if (!currentlyWorking) {
            disableAllExcept(autoStepBtn);
            currentTT = new TimerTask() {
                @Override
                public void run() {
                    world.step();
                }
            };
            timer.scheduleAtFixedRate(currentTT, 0, WATCH_INTERVAL);
            autoStepBtn.setText("Stop");
            currentlyWorking = true;
        } else {
            autoStepBtn.setText("Auto Step");
            enableAllBtns();
            currentTT.cancel();
            timer.purge();
            currentlyWorking = false;
        }
    }//GEN-LAST:event_autoStepBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        world.zero();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void pupulateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pupulateBtnActionPerformed
        for(int i = 0 ; i < world.getDim() ; i++) {
            for ( int j = 0 ; j < world.getDim() ; j++ ) {
                if(Math.random()<0.5) world.toggle(i, j);
            }
        }
    }//GEN-LAST:event_pupulateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRleFileBtn;
    private javax.swing.JButton autoFastBtn;
    private javax.swing.JButton autoStepBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton exportRleBtn;
    private javax.swing.JButton newGameBtn;
    private javax.swing.JButton pupulateBtn;
    private javax.swing.JButton stepBtn;
    private gui.WorldPane worldPane1;
    // End of variables declaration//GEN-END:variables

}
