/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper;

import d0odman.minesweeper.panels.MenuPanel;
import d0odman.minesweeper.panels.MinePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author d0odman
 */

public class Minesweeper extends JFrame {
    // Static variable declarations
    final public static Color BACKGROUND = new Color(0xE5, 0xE5, 0xE5);
    final private MinePanel minePanel = new MinePanel(this);
    final private MenuPanel menuPanel = new MenuPanel();
    final private BorderLayout layout;

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper();
        game.setTitle("Minesweeper!!!!!!");
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setBackground(BACKGROUND);
        game.setVisible(true);
    }

    public Minesweeper() {
        layout = new BorderLayout();
        minePanel.setMenuPanel(menuPanel);
        menuPanel.setMinePanel(minePanel);
        doSetup();
    }

    private void doSetup() {
        setLayout(layout);
        setBackground(BACKGROUND);
        getContentPane().add(menuPanel, BorderLayout.NORTH);
        getContentPane().add(minePanel, BorderLayout.CENTER);
        setResizable(false);
        pack();
    }
}
