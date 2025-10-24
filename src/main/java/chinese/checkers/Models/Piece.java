package chinese.checkers.Models;

public class Piece {
    private Player owner;
    private PlayerColor color;

    public Piece(Player owner) {
        this.owner = owner;
        this.color = owner.getColor();
    }

    public Player getOwner() {
        return this.owner;
    }

    public PlayerColor getColor() {
        return color;
    }
}
