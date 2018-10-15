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
package d0odman.minesweeper;

import d0odman.minesweeper.panels.MenuPanel;
import d0odman.minesweeper.panels.MinePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author William Dolorito
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
