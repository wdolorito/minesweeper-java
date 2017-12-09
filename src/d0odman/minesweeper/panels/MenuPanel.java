/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.panels;

import d0odman.minesweeper.Minesweeper;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author d0odman
 */

public class MenuPanel extends JPanel {
    final private String[] gameDiff = {"Novice",
                                       "Intermediate",
                                       "Expert" };
    final private MinePanel minePanel;
    final private BorderLayout layout;
    final private JComboBox<String> diff;
    final private JLabel minesRem;
    final private JLabel timer;
    private int timerDisp;
    final private Timer gameTimer;

    public MenuPanel(MinePanel minePanel) {
        this.minePanel = minePanel;
        layout = new BorderLayout();
        setBackground(Minesweeper.BACKGROUND);
        timer = new JLabel("0");
        gameTimer = new Timer(1000, (ActionEvent e) -> {
            timerDisp++;
            timer.setText(Integer.toString(timerDisp));
        });
        add(timer, BorderLayout.WEST);
        minesRem = new JLabel();
        setMinesRem(this.minePanel.getUnflaggedMines());
        add(minesRem, BorderLayout.CENTER);
        diff = new JComboBox<>(gameDiff);
        add(diff, BorderLayout.EAST);
        resetTimer();
    }

    private void setMinesRem(int i) {
       minesRem.setText(Integer.toString(i));
    }

    private void resetTimer() {
        timerDisp = 0;
        gameTimer.start();
    }
    
    private void stopTimer() {
        gameTimer.stop();
    }
}