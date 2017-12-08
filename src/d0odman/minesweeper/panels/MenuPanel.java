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
    private JLabel minesRem;
    private JLabel timer;
    private Timer gameTimer;

    public MenuPanel(MinePanel minePanel) {
        this.minePanel = minePanel;
        layout = new BorderLayout();
        setBackground(Minesweeper.BACKGROUND);
        timer = new JLabel();
        resetTimer();
        add(timer, BorderLayout.WEST);
        minesRem = new JLabel();
        setMinesRem(this.minePanel.getNumMines());
        add(minesRem, BorderLayout.CENTER);
        diff = new JComboBox<>(gameDiff);
        add(diff, BorderLayout.EAST);
    }

    private void setMinesRem(int i) {
       minesRem.setText(Integer.toString(i));
    }

    private void resetTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setText(Long.toString(System.currentTimeMillis()));
            }
        });
        gameTimer.start();
    }
}