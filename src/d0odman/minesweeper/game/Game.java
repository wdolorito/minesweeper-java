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
    protected String[]    mines;
    protected ArrayList<Integer>    leftMines,
                                    rightMines;
    protected int   trc,
                    blc,
                    brc;

    protected boolean solved;

    public Game() {
        setSolved(false);
        setEdgeMines();
        generateMines();
    }

    public String[] getMines() {
        return mines;
    }

    protected void setSolved(boolean b) {
        solved = b;
    }

    protected void resetMines() {
        mines = new String[(trc + 1) * getRows()];
        System.out.println("New mines");
        System.out.println(mines.length);
    }

    // Method to set ArrayList variables associated with the mines on the edge
    //  of the game board
    private void setEdgeMines() {
        leftMines = new ArrayList<>();
        rightMines = new ArrayList<>();
        trc = getTRC() - 1;
        blc = getBLC() - 1;
        brc = getBRC() - 1;

        for(int i = trc + 1; i < blc; i += trc + 1) {
            leftMines.add(i);
        }

        for(int i = trc + trc + 1; i < brc - trc; i += trc + 1) {
            rightMines.add(i);
        }
    } // End setEdgeMines method

    private void generateMines() {
        int rando;
        Random temp = new Random();
        resetMines();
        for(int i = 0; i < getNumberOfMines(); i++) {
            rando = temp.nextInt(brc);
            if(!"m".equals(mines[rando])) {
                mines[rando] = "m";
            } else {
                i--;
            }
        }
        for(int i = 0; i < mines.length; i++) {
            checkTile(i);
        }
    }

    private void checkTile(int tile) {
        if(!"m".equals(mines[tile])) {
            int mineCounter = 0;
            boolean checked = false;

            // Top left corner:  check 3 surrounding tiles
            if(tile == 0) {
                if("m".equals(mines[tile + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 2])) mineCounter++;
                checked = true;
            }

            // Top edge mines:  check 5 surrounding tiles
            if(tile > 0 && tile < trc) {
                if("m".equals(mines[tile - 1])) mineCounter++;
                if("m".equals(mines[tile + 1])) mineCounter++;
                if("m".equals(mines[tile + trc])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 2])) mineCounter++;
                checked = true;
            }

            // Top right corner:  check 3 surrounding tiles
            if(tile == trc) {
                if("m".equals(mines[tile - 1])) mineCounter++;
                if("m".equals(mines[tile + trc])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                checked = true;
            }

            // Left edge mines:  check 5 surrounding tiles
            if(leftMines.contains(tile)) {
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - trc])) mineCounter++;
                if("m".equals(mines[tile + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 2])) mineCounter++;
                checked = true;
            }

            // Bottom left corner:  check 3 surrounding tiles
            if(tile == blc) {
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - trc])) mineCounter++;
                if("m".equals(mines[tile + 1])) mineCounter++;
                checked = true;
            }

            // Bottom edge mines:  check 5 surrounding tiles
            if(tile > blc && tile < brc) {
                if("m".equals(mines[tile - trc - 2])) mineCounter++;
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - trc])) mineCounter++;
                if("m".equals(mines[tile - 1])) mineCounter++;
                if("m".equals(mines[tile + 1])) mineCounter++;
                checked = true;
            }

            // Bottom right corner:  check 3 surrounding tiles
            if(tile == brc) {
                if("m".equals(mines[tile - trc - 2])) mineCounter++;
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - 1])) mineCounter++;
                checked = true;
            }

            // Right edge mines:  check 5 surrounding tiles
            if(rightMines.contains(tile)) {
                if("m".equals(mines[tile - trc - 2])) mineCounter++;
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - 1])) mineCounter++;
                if("m".equals(mines[tile + trc])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                checked = true;
            }

            //  Everywhere else:  check 8 surrounding tiles
            if(!checked) {
                if("m".equals(mines[tile - trc - 2])) mineCounter++;
                if("m".equals(mines[tile - trc - 1])) mineCounter++;
                if("m".equals(mines[tile - trc])) mineCounter++;
                if("m".equals(mines[tile - 1])) mineCounter++;
                if("m".equals(mines[tile + 1])) mineCounter++;
                if("m".equals(mines[tile + trc])) mineCounter++;
                if("m".equals(mines[tile + trc + 1])) mineCounter++;
                if("m".equals(mines[tile + trc + 2])) mineCounter++;
            }

            mines[tile] = Integer.toString(mineCounter);
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