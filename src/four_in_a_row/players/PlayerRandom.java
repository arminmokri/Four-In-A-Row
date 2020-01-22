/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.players;

import four_in_a_row.Board;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Armin Mokri
 */
public class PlayerRandom extends Player {

    public PlayerRandom(int col) {
        super(col);
    }

    @Override
    public int getMove(Board board) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 500));//5200
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextInt(0, 5);
    }
}
