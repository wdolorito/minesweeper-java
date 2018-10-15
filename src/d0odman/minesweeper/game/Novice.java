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
package d0odman.minesweeper.game;

import java.awt.Dimension;

/**
 *
 * @author William Dolorito
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
