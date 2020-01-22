/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row.palyers.ai;

import java.util.ArrayList;

/**
 *
 * @author Armin Mokri
 */
public class MinMaxNode {

    public final static int ROOT = 0;
    public final static int MAX = 1;
    public final static int MIN = 2;
    //
    private four_in_a_row.Board board;
    private int move;
    //
    private int[] stuffed;
    private Direction[] directions;
    //
    private int kind;
    private int value;
    public int alpha;
    public int beta;
    private int level;
    private MinMaxNode parent;
    private ArrayList<MinMaxNode> childs;

    public MinMaxNode(four_in_a_row.Board board, int kind, int level, MinMaxNode parent) {
        this(board, kind, level, parent, -1);
    }

    public MinMaxNode(four_in_a_row.Board board, int kind, int level, MinMaxNode parent, int move) {
        this.board = board;
        this.kind = kind;
        this.level = level;
        this.parent = parent;
        this.move = move;
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
        if (this.kind == ROOT) {
            this.value = Integer.MAX_VALUE;
        } else if (this.kind == MAX) {
            this.value = Integer.MIN_VALUE;
        } else {
            this.value = Integer.MAX_VALUE;
        }
        this.childs = new ArrayList<>();
    }

    public void MakeChilds(int player) {

        int kind_child;
        if (this.kind == ROOT) {
            kind_child = MAX;
        } else if (this.kind == MAX) {
            kind_child = MIN;
        } else {
            kind_child = MAX;
        }
        /*
        for (int j = 0; j < 2; j++) {
            MinMaxNode minMaxNode_child1 = new MinMaxNode(board, kind_child, this.getLevel() + 1, this, j);
            childs.add(minMaxNode_child1);
        }
         */
        for (int j = 0; j < four_in_a_row.Board.size; j++) {
            if (board.getCell(four_in_a_row.Board.size - 1, j).getColor() == 0) {
                four_in_a_row.Board board_child = new four_in_a_row.Board(board);
                board_child.move(j, player);

                MinMaxNode minMaxNode_child = new MinMaxNode(board_child, kind_child, this.getLevel() + 1, this, j);
                childs.add(minMaxNode_child);
                //System.out.println("j=" + j);
                //minMaxNode_child.PrintParents();
                //minMaxNode_child.PrintBoard();

            }
        }
    }

    public void EvalutionFunction(int player_max, int player_min, int win) throws Exception {

        if (this.kind == MAX) {
            if (player_max == win) {
                value = -1000;///level;
                return;
            } else if (player_min == win) {
                throw new Exception("");
            }
            Evalution(player_max);
            value = (-1) * value;
        } else {
            if (player_min == win) {
                value = 1000;///level;
                return;
            } else if (player_max == win) {
                throw new Exception("");
            }
            Evalution(player_min);
        }
    }

    private void Stuffed() {
        stuffed = new int[four_in_a_row.Board.size];
        for (int j = 0; j < four_in_a_row.Board.size; j++) {
            for (int i = 0; i < four_in_a_row.Board.size; i++) {
                if (board.getCell(i, j).getColor() != 0) {
                    stuffed[j]++;
                } else {
                    break;
                }
            }
        }
    }

    private void Evalution(int player) {

        Stuffed();
        directions = new Direction[four_in_a_row.Board.size];
        value = 0;
        for (int j = 0; j < four_in_a_row.Board.size; j++) {
            directions[j] = EvalutionColumn(j, player);
            if (directions[j] != null) {
                directions[j].Calculate();
                value = value + directions[j].Evalution;
                //System.out.println("j=" + j + " player" + player);
                //directions[j].Print();
            }
        }
    }

    private Direction EvalutionColumn(int column, int player) {

        int row = stuffed[column] - 1;

        if ((row < 0 || column < 0) || board.getCell(row, column).getColor() != player) {
            return null;
        }

        Direction direction = new Direction();

        for (int j = column + 1; j < four_in_a_row.Board.size; j++) {
            if (board.getCell(row, j).getColor() == player) {
                direction.Right++;
            } else {
                break;
            }
        }

        for (int j = column - 1; j >= 0; j--) {
            if (board.getCell(row, j).getColor() == player) {
                direction.Left++;
            } else {
                break;
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            if (board.getCell(i, column).getColor() == player) {
                direction.Down++;
            } else {
                break;
            }
        }

        for (int i = row + 1, j = column + 1; i < four_in_a_row.Board.size && j < four_in_a_row.Board.size; i++, j++) {
            if (board.getCell(i, j).getColor() == player) {
                direction.Right_Up++;
            } else {
                break;
            }
        }

        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.getCell(i, j).getColor() == player) {
                direction.Left_Down++;
            } else {
                break;
            }
        }

        for (int i = row + 1, j = column - 1; i < four_in_a_row.Board.size && j >= 0; i++, j--) {
            if (board.getCell(i, j).getColor() == player) {
                direction.Left_Up++;
            } else {
                break;
            }
        }

        for (int i = row - 1, j = column + 1; i >= 0 && j < four_in_a_row.Board.size; i--, j++) {
            if (board.getCell(i, j).getColor() == player) {
                direction.Right_Down++;
            } else {
                break;
            }
        }

        return direction;
    }

    public void PrintParents() {
        MinMaxNode temp = this;
        String s = "";
        while (temp != null) {
            if (temp.getMove() != -1) {
                s = temp.getMove() + "*" + s;
            }
            temp = temp.getParent();
        }
        System.out.println(s);
    }

    public void PrintBoard() {
        for (int ii = 0; ii < four_in_a_row.Board.size; ii++) {
            for (int jj = 0; jj < four_in_a_row.Board.size; jj++) {
                System.out.print(board.getCell(ii, jj).getColor() + " ");
            }
            System.out.println();
        }
    }

    /*public void PrintArray(String name, int[] array) {
        System.out.println("Name=" + name);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }*/

    public four_in_a_row.Board getBoard() {
        return board;
    }

    public int getKind() {
        return kind;
    }

    public int getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }

    public int getMove() {
        return move;
    }

    public MinMaxNode getParent() {
        return parent;
    }

    public ArrayList<MinMaxNode> getChilds() {
        return childs;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
