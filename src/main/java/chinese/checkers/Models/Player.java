package chinese.checkers.Models;

public class Player {
    private String name;
    private PlayerColor color;

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public PlayerColor getColor() {
        return this.color;
    }
}
