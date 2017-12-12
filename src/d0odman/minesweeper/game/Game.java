/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.game;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author d0odman
 */

public abstract class Game {
    // Minefield variables
    protected String[]    mines;
    protected ArrayList<Integer>    topMines,
                                    leftMines,
                                    rightMines,
                                    bottomMines,
                                    solution;
    protected int   trc,
                    blc,
                    brc;

    protected boolean solved;

    public Game() {
        solved = false;
        setEdgeMines();
        generateMines();
    }

    public String[] getMines() {
        return mines;
    }

    protected void resetMines() {
        mines = new String[(trc + 1) * getRows()];
    }

    // Method to set ArrayList variables associated with the mines on the edge
    //  of the game board
    private void setEdgeMines() {
        topMines = new ArrayList<>();
        leftMines = new ArrayList<>();
        rightMines = new ArrayList<>();
        bottomMines = new ArrayList<>();
        trc = getTRC() - 1;
        blc = getBLC() - 1;
        brc = getBRC() - 1;

        for(int i = 1; i < trc; i++) {
            topMines.add(i);
        }

        for(int i = trc + 1; i < blc; i += trc + 1) {
            leftMines.add(i);
        }

        for(int i = trc + trc + 1; i < brc - trc; i += trc + 1) {
            rightMines.add(i);
        }

        for(int i = blc + 1; i < brc; i++) {
            bottomMines.add(i);
        }
    } // End setEdgeMines method

    private void generateMines() {
        solution = new ArrayList<>();
        int rando;
        Random temp = new Random();
        resetMines();
        for(int i = 0; i < getNumberOfMines(); i++) {
            rando = temp.nextInt(brc);
            if(!"m".equals(mines[rando])) {
                mines[rando] = "m";
                solution.add(rando);
            } else {
                i--;
            }
            Collections.sort(solution);
        }
        for(int i = 0; i < mines.length; i++) {
            checkTile(i);
        }
    }

    public List<Integer> returnSolution() {
        return Collections.unmodifiableList(solution);
    }

    public List<Integer> returnCheckMines(int tile) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        boolean checked = false;

        // Top left corner:  return 3 surrounding tiles
        if(tile == 0) {
            toReturn.add(tile + 1);
            toReturn.add(tile + trc + 1);
            toReturn.add(tile + trc + 2);
            checked = true;
        }

        // Top edge mines:  return 5 surrounding tiles
        if(topMines.contains(tile)) {
            toReturn.add(tile - 1);
            toReturn.add(tile + 1);
            toReturn.add(tile + trc);
            toReturn.add(tile + trc + 1);
            toReturn.add(tile + trc + 2);
            checked = true;
        }

        // Top right corner:  return 3 surrounding tiles
        if(tile == trc) {
            toReturn.add(tile - 1);
            toReturn.add(tile + trc);
            toReturn.add(tile + trc + 1);
            checked = true;
        }

        // Left edge mines:  return 5 surrounding tiles
        if(leftMines.contains(tile)) {
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - trc);
            toReturn.add(tile + 1);
            toReturn.add(tile + trc + 1);
            toReturn.add(tile + trc + 2);
            checked = true;
        }

        // Bottom left corner:  return 3 surrounding tiles
        if(tile == blc) {
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - trc);
            toReturn.add(tile + 1);
            checked = true;
        }

        // Bottom edge mines:  return 5 surrounding tiles
        if(bottomMines.contains(tile)) {
            toReturn.add(tile - trc - 2);
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - trc);
            toReturn.add(tile - 1);
            toReturn.add(tile + 1);
            checked = true;
        }

        // Bottom right corner:  return 3 surrounding tiles
        if(tile == brc) {
            toReturn.add(tile - trc - 2);
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - 1);
            checked = true;
        }

        // Right edge mines:  return 5 surrounding tiles
        if(rightMines.contains(tile)) {
            toReturn.add(tile - trc - 2);
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - 1);
            toReturn.add(tile + trc);
            toReturn.add(tile + trc + 1);
            checked = true;
        }

        //  Everywhere else:  return 8 surrounding tiles
        if(!checked) {
            toReturn.add(tile - trc - 2);
            toReturn.add(tile - trc - 1);
            toReturn.add(tile - trc);
            toReturn.add(tile - 1);
            toReturn.add(tile + 1);
            toReturn.add(tile + trc);
            toReturn.add(tile + trc + 1);
            toReturn.add(tile + trc + 2);
        }

        return Collections.unmodifiableList(toReturn);
    }

    private void checkTile(int tile) {
        if(!"m".equals(mines[tile])) {
            int mineCounter = 0;
            List<Integer> toCheck = returnCheckMines(tile);
            for(int i: toCheck) {
                if("m".equals(mines[i])) mineCounter++;
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