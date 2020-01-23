/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row;

import four_in_a_row.players.Player;
import four_in_a_row.gui.JFrameMain;

/**
 *
 * @author Armin Mokri
 */
public class Game extends Thread {

    private int timeLimit;
    private Player[] players;
    private Board board;
    private int move;
    private int res;
    private int turn;
    private JFrameMain jFrameMain;
    private boolean debug;

    public Game(Player player1, Player player2, int border_size, int time_limit, JFrameMain jFrameMain, boolean debug) {
        players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
        this.timeLimit = time_limit * 1000;
        this.board = new Board(border_size);
        this.jFrameMain = jFrameMain;
        this.debug = debug;
    }

    public Game(Player p1, Player p2, int border_size, int time_limit, boolean debug) {
        players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
        this.timeLimit = time_limit * 1000;
        this.debug = debug;
        this.board = new Board(border_size);
    }

    @Override
    public void run() {
        this.turn = 0;
        while (board.win() == 0) {

            res = -2;

            // sleep 100
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread t = new Thread() {
                public void run() {
                    try {
                        move = players[turn].getMove(new Board(board));
                        res = 0;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();

            try {
                t.join(timeLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (res == -2) {
                break;
            }

            res = board.move(move, turn + 1);
            if (res == -1) {
                break;
            }

            turn = 1 - turn;

            // update UI
            if (jFrameMain != null) {
                jFrameMain.printGame();
            }
        }

        int win = -1;
        String string = "";

        switch (res) {
            case 0:
                win = board.win();
                break;
            case -1:
                win = players[1 - turn].getCol();
                string = string + "Player " + players[turn].getCol() + " has made an invalid move.\n";
                break;
            case -2:
                win = players[1 - turn].getCol();
                string = string + "Player " + players[turn].getCol() + " has exceeded the time limit.\n";
                break;
        }
        string = string + "Player " + win + " has won.";

        if (debug) {
            System.out.println(string);
        }
        if (jFrameMain != null) {
            jFrameMain.showResult(string);
        }

    }

    public Board getBoard() {
        return board;
    }
}
