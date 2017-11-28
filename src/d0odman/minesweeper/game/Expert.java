/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d0odman.minesweeper.game;

import java.awt.Dimension;

/**
 *
 * @author d0odman
 */

public class Expert extends Game {
    static final int    MINES = 99,
                        TRC = 30,
                        BLC = TRC * (TRC -1) + 1,
                        ROWS = 16,
                        BRC = TRC * ROWS;
    
    static final Dimension BOARD = new Dimension(TRC * 20,(ROWS * 20) + 60);

    @Override
    public int getNumberOfMines() {
        return MINES;
    }

    @Override
    public Dimension getBoardSize() {
        return BOARD;
    }

    @Override
    public int getTRC() {
        return TRC;
    }

    @Override
    public int getBLC() {
        return BLC;
    }

    @Override
    protected int getBRC() {
        return BRC;
    }

    @Override
    public int getRows() {
        return ROWS;
    }
}
