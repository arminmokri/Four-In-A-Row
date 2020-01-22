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
public class Cell {

    private int row, column;
    private int color;

    public Cell(int row, int column, int color) {
        this(column, row);
        this.color = color;
    }

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell(int color) {
        this.color = color;
    }

    public Cell() {
    }

    public Cell(Cell cell) {
        this.row = cell.getRow();
        this.column = cell.getColumn();
        this.color = cell.getColor();
    }

    public int getRow() {
        return row;
    }

    protected void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
