package chinese.checkers.Models;

import chinese.checkers.Models.PlayerColor.PlayerColor;
import java.awt.*;

public class Piece {
    private Player owner;
    private PlayerColor color;

    public Piece(Player owner) {
        this.owner = owner;
    }

    public Piece(Player owner, PlayerColor color) {
        this.owner = owner;
        this.color = color;
    }

    public Player getOwner() {
        return this.owner;
    }

    public PlayerColor getColor() {
        return color;
    }
}
