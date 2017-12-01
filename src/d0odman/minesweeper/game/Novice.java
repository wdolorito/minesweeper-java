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

public class Novice extends Game {
    static final int    MINES = 10,
                        TRC = 9,
                        ROWS = TRC,
                        BRC = TRC * ROWS,
                        BLC = BRC - TRC + 1;
    
    static final Dimension BOARD = new Dimension(TRC * 20 + 60, ROWS * 20 + 60);

    @Override
    public int getNumberOfMines() {
        return MINES;
    }

    @Override
    public Dimension getBoardSize() {
        return BOARD;
    }

    @Override
    protected int getTRC() {
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
    protected int getRows() {
        return ROWS;
    }
}
