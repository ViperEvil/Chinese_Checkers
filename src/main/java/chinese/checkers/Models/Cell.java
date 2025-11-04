package chinese.checkers.Models;

public class Cell {
    private int x, y;
    private Piece piece;

    public Cell(int row, int col) {
        this.x = row;
        this.y = col;
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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
