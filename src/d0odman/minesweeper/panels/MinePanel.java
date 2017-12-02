/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.panels;

import d0odman.minesweeper.game.Game;
import d0odman.minesweeper.game.Novice;
import d0odman.minesweeper.game.Intermediate;
import d0odman.minesweeper.game.Expert;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author d0odman
 */

public class MinePanel extends JPanel {
    // Static variable declarations
    public static final Color BACKGROUND = new Color(0xE5, 0xE5, 0xE5);
    
    private Game currentGame;
    private GridLayout layout = new GridLayout();
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
        layout.setColumns(9);
        layout.setRows(9);
        initPanel();
        setTileIcons();
        initBoard();
    }
    
    private void initPanel() {
        setLayout(layout);
        setPreferredSize(currentGame.getBoardSize());
        setBackground(BACKGROUND);
        setBorder(new EmptyBorder(30, 30, 30, 30)); 
    }
    
    private void setTileIcons() {
        setTileIcons("set1/");
    }

    private void setTileIcons(String setName) {
        String path = "images/" + setName;
        
        initial = new ImageIcon(path + "default.png");
        initial = new ImageIcon(initial.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        empty = new ImageIcon(path + "empty.png");
        empty = new ImageIcon(empty.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        one = new ImageIcon(path + "1.png");
        one = new ImageIcon(one.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        two = new ImageIcon(path + "2.png");
        two = new ImageIcon(two.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        three = new ImageIcon(path + "3.png");
        three = new ImageIcon(three.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        four = new ImageIcon(path + "4.png");
        four = new ImageIcon(four.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        five = new ImageIcon(path + "5.png");
        five = new ImageIcon(five.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        six = new ImageIcon(path + "6.png");
        six = new ImageIcon(six.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        seven = new ImageIcon(path + "7.png");
        seven = new ImageIcon(seven.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        eigth = new ImageIcon(path + "8.png");
        eigth = new ImageIcon(eigth.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        flag = new ImageIcon(path + "flag.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        bomb = new ImageIcon(path + "bomb.png");
        bomb = new ImageIcon(bomb.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        exploded = new ImageIcon(path + "exploded.png");
        exploded = new ImageIcon(exploded.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    private void initBoard() {
        mines = currentGame.getMines();
        for(int i = 0; i < mines.length; i++) {
            JButton tempButton = initButton(mines[i]);
            tempButton.setBackground(BACKGROUND);
            add(tempButton);
        }
    }
    
    private JButton initButton(String val) {
        JButton tempButton = new JButton(initial);
        switch(val) {
            case "m": tempButton = new JButton(bomb);
                      break;
            case "0": tempButton = new JButton(empty);
                      break;
            case "1": tempButton = new JButton(one);
                      break;
            case "2": tempButton = new JButton(two);
                      break;
            case "3": tempButton = new JButton(three);
                      break;
            case "4": tempButton = new JButton(four);
                      break;
            case "5": tempButton = new JButton(five);
                      break;
            case "6": tempButton = new JButton(six);
                      break;
            case "7": tempButton = new JButton(seven);
                      break;
            case "8": tempButton = new JButton(eigth);
                      break;
        }
        return tempButton;
    }
}