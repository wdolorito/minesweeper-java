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
        currentGame = new Expert();
        layout.setColumns(currentGame.getTRC());
        layout.setRows(currentGame.getRows());
        setLayout(layout);
        setMaximumSize(currentGame.getBoardSize());
        setMinimumSize(currentGame.getBoardSize());
        setPreferredSize(currentGame.getBoardSize());
        setBackground(BACKGROUND);
        setTileIcons();
        initBoard();
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
        two = new ImageIcon(path + "2.png");
        three = new ImageIcon(path + "3.png");
        four = new ImageIcon(path + "4.png");
        five = new ImageIcon(path + "5.png");
        six = new ImageIcon(path + "6.png");
        seven = new ImageIcon(path + "7.png");
        eigth = new ImageIcon(path + "8.png");
        flag = new ImageIcon(path + "flag.png");
        bomb = new ImageIcon(path + "bomb.png");
        exploded = new ImageIcon(path + "exploded.png");
    }

    private void initBoard() {
        System.out.println(currentGame.getTRC());
        System.out.println(currentGame.getRows());
        int counter = currentGame.getTRC() * currentGame.getRows();
        for(int i = 0; i < counter; i++) {
            JButton tempButton = new JButton(initial);
            tempButton.setBackground(BACKGROUND);
            add(tempButton);
        }
    }
}