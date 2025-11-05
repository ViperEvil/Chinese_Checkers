package chinese.checkers.Models;

import java.awt.*;

public enum PlayerColor {
    BLUE(Color.BLUE),
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    GRAY(Color.LIGHT_GRAY);

    private final Color color;

    PlayerColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
