/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four_in_a_row;

/**
 *
 * @author Armin Mokri
 */
public class Board {

    private int size;
    private Cell[][] cells;
    private int numberOfMoves = 0;

    public Board(Cell[][] cells, int numberOfMoves) {
        this.size = cells.length;
        this.cells = cells;
        this.numberOfMoves = numberOfMoves;
    }

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(i, j, 0);
            }
        }
        numberOfMoves = 0;
    }

    public Board(Board board) {
        this.size = board.cells.length;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell(board.cells[i][j]);
            }
        }
        this.numberOfMoves = board.numberOfMoves;
    }

    public int win() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 3; j++) {
                if (checkColor(cells[i][j], cells[i][j + 1]) && checkColor(cells[i][j], cells[i][j + 2]) && checkColor(cells[i][j], cells[i][j + 3])) {
                    return cells[i][j].getColor();
                }
            }
        }
        for (int i = 0; i < size - 3; i++) {
            for (int j = 0; j < size; j++) {
                if (checkColor(cells[i][j], cells[i + 1][j]) && checkColor(cells[i][j], cells[i + 2][j]) && checkColor(cells[i][j], cells[i + 3][j])) {
                    return cells[i][j].getColor();
                }
            }
        }
        for (int i = 0; i < size - 3; i++) {
            for (int j = 0; j < size - 3; j++) {
                if (checkColor(cells[i][j], cells[i + 1][j + 1]) && checkColor(cells[i][j], cells[i + 2][j + 2]) && checkColor(cells[i][j], cells[i + 3][j + 3])) {
                    return cells[i][j].getColor();
                }
            }
        }
        for (int i = 0; i < size - 3; i++) {
            for (int j = 3; j < size; j++) {
                if (checkColor(cells[i][j], cells[i + 1][j - 1]) && checkColor(cells[i][j], cells[i + 2][j - 2]) && checkColor(cells[i][j], cells[i + 3][j - 3])) {
                    return cells[i][j].getColor();
                }
            }
        }
        return 0;
    }

    public int move(int column, int playerColor) {
        numberOfMoves++;
        if (column < 0 || column > size - 1 || cells[size - 1][column].getColor() != 0) {
            return -1;
        } else {
            for (int i = 0; i < size; i++) {
                if (cells[i][column].getColor() == 0) {
                    cells[i][column].setColor(playerColor);
                    break;
                }
            }
        }
        return 0;
    }

    private boolean checkColor(Cell cell1, Cell cell2) {
        if (cell1.getColor() == 0) {
            return false;
        }
        return cell1.getColor() == cell2.getColor();
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
}
