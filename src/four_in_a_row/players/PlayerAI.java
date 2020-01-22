/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.players;

import four_in_a_row.palyers.ai.MinMaxTree;

/**
 *
 * @author Armin Mokri
 */
public class PlayerAI extends Player {

    private int level;
    private boolean debug;

    public PlayerAI(int col) {
        super(col);
        this.level = 4;
    }

    public PlayerAI(int col, int level, boolean debug) {
        super(col);
        this.level = level;
        this.debug = debug;
    }

    @Override
    public int getMove(four_in_a_row.Board board) {
        try {
            MinMaxTree minMaxTree = new MinMaxTree(board, getCol(), level, debug);
            minMaxTree.Start();
            return minMaxTree.getMove();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1;

    }
}
