/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.panels;

import d0odman.minesweeper.Minesweeper;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
    private MinePanel minePanel;
    final private BorderLayout layout;
    final private JComboBox<String> diff;
    private JLabel minesRem;
    final private JLabel timer;
    private int timerDisp;
    final private Timer gameTimer;

    public MenuPanel() {
        layout = new BorderLayout();
        timer = new JLabel("0");
        gameTimer = new Timer(1000, (ActionEvent e) -> {
            timerDisp++;
            timer.setText(Integer.toString(timerDisp));
        });
        diff = new JComboBox<>(gameDiff);
        diff.addActionListener((ActionEvent e) -> {
            JComboBox box = (JComboBox) e.getSource();
            restartGame((String) box.getSelectedItem());
        });
        doSetup();
    }

    private void doSetup() {
        setBackground(Minesweeper.BACKGROUND);
        add(timer, BorderLayout.WEST);
        add(diff, BorderLayout.EAST);
    }

    private void restartGame(String difficulty) {
        minePanel.newGame(difficulty);
    }

    public void setMinePanel(MinePanel minePanel) {
        this.minePanel = minePanel;
        minesRem = new JLabel();
        setMinesRem(this.minePanel.getUnflaggedMines());
        add(minesRem, BorderLayout.CENTER);
    }

    public void setMinesRem(int i) {
       minesRem.setText(Integer.toString(i));
    }

    public void resetTimer() {
        timerDisp = 0;
        timer.setText("0");
    }

    public void startTimer() {
        gameTimer.start();
    }

    public void stopTimer() {
        gameTimer.stop();
    }
}