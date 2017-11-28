/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper;

import d0odman.minesweeper.panels.MinePanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author d0odman
 */

public class Minesweeper extends JFrame {
    private static final MinePanel minePanel = new MinePanel();
    private BorderLayout layout;
    
    public static void main(String[] args) {
        Minesweeper game = new Minesweeper();
        game.setTitle("Minesweeper!!!!!!");
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setBackground(minePanel.getBackground());
        game.setVisible(true);
    }
    
    public Minesweeper() {
        layout = new BorderLayout(0, 0);
        setLayout(layout);
        setBackground(MinePanel.BACKGROUND);
        getContentPane().add(minePanel, BorderLayout.CENTER);
        setResizable(false);
        pack();
    }
}
