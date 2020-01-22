/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.players;

import four_in_a_row.Board;

/**
 *
 * @author Armin Mokri
 */
public abstract class Player {

    private final int col;

    abstract public int getMove(Board board);

    public Player(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }
}
