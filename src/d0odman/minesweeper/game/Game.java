/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.game;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author d0odman
 */

public abstract class Game {
    // Minefield variables
    protected ArrayList<Integer>    mines = new ArrayList<>(),
                                    leftMines = new ArrayList<>(),
                                    rightMines = new ArrayList<>();
    
    public Game() {
        setEdgeMines();
        generateMineField();
    }
    
    public ArrayList<Integer> getMines() {
        return mines;
    }
    
    protected ArrayList<Integer> getLeftMines() {
        return leftMines;
    }
    
    protected ArrayList<Integer> getRightMines() {
        return rightMines;
    }
    
    protected void setMine(int mine) {
        if(!mines.contains(mine)) mines.add(mine);
    }
    
    protected void resetMines() {
        mines.clear();
    }

    // Method to set ArrayList variables associated with the mines on the edge
    //  of the game board
    private void setEdgeMines() {
        // Squares from 1 to blc non-inclusive and are multiples of trc belong
        //  to leftEdgeMines
        for(int i = getBLC() - getTRC(); i > 1; i -= getTRC()) {
            leftMines.add(i);
        }

        // Squares from trc to brc non-inclusive and are multiples of trc belong
        //  to rightEdgeMines
        for(int i = getTRC(); i < getBRC(); i += getTRC()) {
            rightMines.add(i);
        }
    } // End setEdgeMines method
    
    private void generateMineField() {
        int rando;
        Random temp = new Random();
        mines.clear();
        while(mines.size() < getNumberOfMines()) {
            rando = temp.nextInt(getBRC()) + 1;
            
            if(!mines.contains(rando)) {
                mines.add(rando);
            }
        }
    }

    // Get number of mines for this difficulty level
    abstract public int getNumberOfMines();

    // Get size of window for this difficulty level
    abstract public Dimension getBoardSize();

    // Get top right corner of mine field
    abstract public int getTRC();

    // Get bottom left corner of mine field
    abstract public int getBLC();

    // Get bottom right corner of mine field
    abstract protected int getBRC();

    // Get rows of mine field
    abstract public int getRows();
}