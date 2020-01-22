/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.palyers.ai;

/**
 *
 * @author Armin Mokri
 */
public class Move {

    private int column;
    private int player;

    public Move(int column, int player) {
        this.column = column;
        this.player = player;
    }

    public int getColumn() {
        return column;
    }

    public int getPlayer() {
        return player;
    }
}
