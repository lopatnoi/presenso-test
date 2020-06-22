package com.presenso.domain;

public class Site {

    private final int rowPosition, columnPosition;
    private final int[] rows;
    private final int[] cols;

    public Site(int rowPosition, int columnPosition, int[] rows, int[] cols) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public String toString() {
        return "(" + rowPosition + ", " + columnPosition + ")";
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int[] getRows() {
        return rows;
    }

    public int[] getCols() {
        return cols;
    }
}
