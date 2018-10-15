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
import d0odman.minesweeper.game.Game;
import d0odman.minesweeper.game.Novice;
import d0odman.minesweeper.game.Intermediate;
import d0odman.minesweeper.game.Expert;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author William Dolorito
 */

public class MinePanel extends JPanel {
    final private JFrame mainWindow;
    final private GridLayout layout;
    private Game currentGame;
    private List<Integer> solution;
    private ArrayList<Integer> runningSolution;
    private MenuPanel menuPanel;
    private JButton[] mineField;
    private String[] mines;
    private int unflaggedMines;
    private boolean gameRunning;

    private ImageIcon   initial,
                        empty,
                        one,
                        two,
                        three,
                        four,
                        five,
                        six,
                        seven,
                        eigth,
                        flag,
                        bomb,
                        exploded;

    public MinePanel(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        layout = new GridLayout();
        setTileIcons();
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        runningSolution = new ArrayList<>();
        newGame();
        initPanel();
    }

    private void initPanel() {
        layout.setColumns(currentGame.getTRC());
        layout.setRows(currentGame.getRows());
        setLayout(layout);
        setPreferredSize(currentGame.getBoardSize());
        setBackground(Minesweeper.BACKGROUND);
        setBorder(new EmptyBorder(30, 30, 30, 30));
    }

    private void setTileIcons() {
        setTileIcons("set1/");
    }

    protected void setTileIcons(String setName) {
        String path = "images/" + setName;

        initial = new ImageIcon(path + "default.png");
        initial = new ImageIcon(initial
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        empty = new ImageIcon(path + "empty.png");
        empty = new ImageIcon(empty
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        one = new ImageIcon(path + "1.png");
        one = new ImageIcon(one
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        two = new ImageIcon(path + "2.png");
        two = new ImageIcon(two
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        three = new ImageIcon(path + "3.png");
        three = new ImageIcon(three
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        four = new ImageIcon(path + "4.png");
        four = new ImageIcon(four
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        five = new ImageIcon(path + "5.png");
        five = new ImageIcon(five
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        six = new ImageIcon(path + "6.png");
        six = new ImageIcon(six
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        seven = new ImageIcon(path + "7.png");
        seven = new ImageIcon(seven
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        eigth = new ImageIcon(path + "8.png");
        eigth = new ImageIcon(eigth
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        flag = new ImageIcon(path + "flag.png");
        flag = new ImageIcon(flag
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        bomb = new ImageIcon(path + "bomb.png");
        bomb = new ImageIcon(bomb
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        exploded = new ImageIcon(path + "exploded.png");
        exploded = new ImageIcon(exploded
                .getImage()
                .getScaledInstance(20, 20, Image.SCALE_SMOOTH)); 
    }

    private void initBoard() {
        mines = currentGame.getMines();
        mineField = new JButton[mines.length];
        unflaggedMines = currentGame.getNumberOfMines();
        for(int i = 0; i < mines.length; i++) {
            JButton tempButton = new JButton(initial);
            tempButton.putClientProperty("id", i);
            tempButton.putClientProperty("state", "initial");
            tempButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int buttonIndex = (int) ((JButton) e.getSource())
                                        .getClientProperty("id");
                    switch(e.getButton()) {
                        case 1: doLeftClick(buttonIndex);
                                break;
                        case 3: doRightClick(buttonIndex);
                                break;
                        default: System.out.println("we don't use this button");
                                 System.out.println(e.getButton());
                                 break;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            mineField[i] = tempButton;
            add(tempButton);
        }
    }

    private void doLeftClick(int buttonIndex) {
        if(!gameRunning) {
            gameRunning = true;
            menuPanel.resetTimer();
            menuPanel.startTimer();
        }
        String state = (String) mineField[buttonIndex].getClientProperty("state");
        if(!"flag".equals(state) && !"checked".equals(state)) {
            String val = mines[buttonIndex];
            if("m".equals(val)) {
                endGame(buttonIndex);
            } else {
                mineField[buttonIndex].setDisabledIcon(getImageIcon(val));
                mineField[buttonIndex].putClientProperty("state", "checked");
                mineField[buttonIndex].setEnabled(false);
                if("0".equals(val)) {
                    List<Integer> toCheck = currentGame.returnCheckMines(buttonIndex);
                    toCheck.forEach((i) -> {
                        doLeftClick(i);
                    });
                }
            }
        }
        validateGame();
    }

    private void doRightClick(int buttonIndex) {
        if(!gameRunning) {
            gameRunning = true;
            menuPanel.resetTimer();
            menuPanel.startTimer();
        }
        String state = (String) mineField[buttonIndex].getClientProperty("state");
        if ("initial".equals(state)) {
            if(unflaggedMines != 0) {
                mineField[buttonIndex].putClientProperty("state", "flag");
                mineField[buttonIndex].setIcon(flag);

                System.out.println("adding " + Integer.toString(buttonIndex));
                if(!runningSolution.contains(buttonIndex)) {
                    runningSolution.add(buttonIndex);
                    Collections.sort(runningSolution);
                    System.out.println(runningSolution);
                }
            }
            unflaggedMines--;
            if(unflaggedMines < 0) unflaggedMines = 0;
        } else {
            if(!"checked".equals(state)) {
                int numMines = currentGame.getNumberOfMines();

                System.out.println("removing " + Integer.toString(buttonIndex));
                runningSolution.remove(new Integer(buttonIndex));
                System.out.println(runningSolution);

                mineField[buttonIndex].putClientProperty("state", "initial");
                mineField[buttonIndex].setIcon(initial);
                unflaggedMines++;
                if(unflaggedMines > numMines) unflaggedMines = numMines;
            }
        }

        menuPanel.setMinesRem(unflaggedMines);
        validateGame();
    }

    private void validateGame() {
        System.out.println("validating game");
        System.out.println(runningSolution.size());
        System.out.println(runningSolution);
        System.out.println(solution.size());
        System.out.println(solution);
        if(runningSolution.size() == solution.size()) {
            System.out.println("solutions right size");
            if(runningSolution.equals(solution)) {
                System.out.println("solutions same");
                int len = mineField.length,
                    numMines = currentGame.getNumberOfMines();
                ArrayList<String> tester = new ArrayList<>();
                for(JButton button: mineField) {
                    String state = (String) button.getClientProperty("state");
                    if("checked".equals(state)) {
                        tester.add(state);
                    }
                }
                if(tester.size() == (len - numMines)) winGame();
            }
        }
    }

    private void endGame(int buttonIndex) {
        removeAll();
        for(int i = 0; i < mines.length; i++) {
            JButton tempButton = new JButton(getImageIcon(mines[i]));
            if(i == buttonIndex) tempButton.setIcon(exploded);
            add(tempButton);
        }
        revalidate();
        gameRunning = false;
        menuPanel.stopTimer();
    }

    private void winGame() {
        removeAll();
        for(String mine : mines) {
            JButton tempButton = new JButton(getImageIcon(mine));
            add(tempButton);
        }
        revalidate();
        gameRunning = false;
        menuPanel.stopTimer();
    }

    private void newGame() {
        newGame("Novice");
    }

    public void newGame(String difficulty) {
        mainWindow.setResizable(true);
        menuPanel.stopTimer();
        menuPanel.resetTimer();
        removeAll();
        switch(difficulty) {
            case "Intermediate": currentGame = new Intermediate();
                                 break;
            case "Expert": currentGame = new Expert();
                           break;
            default: currentGame = new Novice();
                     break;
        }
        gameRunning = false;
        initPanel();
        solution = currentGame.returnSolution();
        initBoard();
        revalidate();
        mainWindow.setResizable(false);
        mainWindow.pack();
    }

    private ImageIcon getImageIcon(String val) {
        ImageIcon tempIcon = initial;
        switch(val) {
            case "m": tempIcon = bomb;
                      break;
            case "0": tempIcon = empty;
                      break;
            case "1": tempIcon = one;
                      break;
            case "2": tempIcon = two;
                      break;
            case "3": tempIcon = three;
                      break;
            case "4": tempIcon = four;
                      break;
            case "5": tempIcon = five;
                      break;
            case "6": tempIcon = six;
                      break;
            case "7": tempIcon = seven;
                      break;
            case "8": tempIcon = eigth;
                      break;
        }
        return tempIcon;
    }

    public int getNumMines() {
        return currentGame.getNumberOfMines();
    }

    public int getUnflaggedMines() {
        return unflaggedMines;
    }
}