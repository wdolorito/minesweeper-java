/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author d0odman
 */

public class MinePanel extends JPanel {
    private Game currentGame;
    private GridLayout layout;
    private JButton[] mineField;
    private String[] mines;

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

    public MinePanel() {
        currentGame = new Novice();
        layout = new GridLayout();
        layout.setColumns(currentGame.getTRC());
        layout.setRows(currentGame.getRows());
        initPanel();
        setTileIcons();
        initBoard();
    }

    private void initPanel() {
        setLayout(layout);
        setPreferredSize(currentGame.getBoardSize());
        setBackground(Minesweeper.BACKGROUND);
        setBorder(new EmptyBorder(30, 30, 30, 30));
    }

    private void setTileIcons() {
        setTileIcons("set1/");
    }

    private void setTileIcons(String setName) {
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
        mineField = new JButton[mines.length + 1];
        for(int i = 0; i < mines.length; i++) {
            JButton tempButton = new JButton(initial);
            tempButton.putClientProperty("id", i);
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
            add(tempButton);
        }
    }
    
    private void doLeftClick(int buttonIndex) {
        System.out.println("left click");
        System.out.println(buttonIndex);
    }
    
    private void doRightClick(int buttonIndex) {
        System.out.println("right click");
        System.out.println(buttonIndex);
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
}