/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.panels;

import d0odman.minesweeper.Minesweeper;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

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
    final private JComboBox diff;
    
    public MenuPanel(MinePanel minePanel) {
        this.minePanel = minePanel;
        layout = new BorderLayout();
        setBackground(Minesweeper.BACKGROUND);
        diff = new JComboBox(gameDiff);
        add(diff, BorderLayout.EAST);
    }
}