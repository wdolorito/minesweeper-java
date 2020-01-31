/*
 * The MIT License
 *
 * Copyright 2018 William Dolorito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package d0odman.minesweeper.panels;

import d0odman.minesweeper.Minesweeper;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author William Dolorito
 */

public class MenuPanel extends JPanel {
    final private String[] gameDiff = {"Novice",
                                       "Intermediate",
                                       "Expert" };
    final private String[] tileSets = {"set1",
                                       "set2" };
    private MinePanel minePanel;
    final private BorderLayout layout;
    final private JComboBox<String> diff;
    final private JComboBox<String> sets;
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
        sets = new JComboBox<>(tileSets);
        sets.addActionListener((ActionEvent e) -> {
            JComboBox box = (JComboBox) e.getSource();
            minePanel.setTileIcons((String) box.getSelectedItem() + "/", false);
        });
        doSetup();
    }

    private void doSetup() {
        setBackground(Minesweeper.BACKGROUND);
        setBorder(new EmptyBorder(30, 30, 0, 30));
        add(timer, BorderLayout.WEST);
        add(diff, BorderLayout.EAST);
        add(sets, BorderLayout.SOUTH);
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
    
    public String getTimer() {
        return timer.getText();
    }
}