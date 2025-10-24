package chinese.checkers.Models;

public class Cell {
    private int r, q;
    private Piece piece;

    public Cell(int row, int col) {
        this.r = row;
        this.q = col;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }
}
