/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.players;

import java.util.Scanner;

/**
 *
 * @author Armin Mokri
 */
public class PlayerHuman extends Player {

    public PlayerHuman(int col) {
        super(col);
    }

    @Override
    public int getMove(four_in_a_row.Board board) {
        System.out.print("Enter Column: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
