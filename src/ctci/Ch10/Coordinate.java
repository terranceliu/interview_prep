package ctci.Ch10;

public class Coordinate {
    public int row, col;

    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    public boolean isInbounds(int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public int getElement(int[][] matrix) {
        return matrix[row][col];
    }

    public boolean equals(Coordinate coor) {
        return this.row == coor.row && this.col == coor.col;
    }

    public boolean isBefore(Coordinate coor) {
        return row <= coor.row && col <= coor.col;
    }

    public Coordinate clone() {
        return new Coordinate(row, col);
    }

    public void setToAvg(Coordinate c1, Coordinate c2) {
        row = (c1.row + c2.row) / 2;
        col = (c1.col + c2.col) / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(')
                .append(Integer.toString(row))
                .append(',')
                .append(Integer.toString(col))
                .append(')');
        return sb.toString();
    }
}
