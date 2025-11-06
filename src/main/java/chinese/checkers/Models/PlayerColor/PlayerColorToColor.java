package chinese.checkers.Models.PlayerColor;

import java.awt.*;

public class PlayerColorToColor {
    public static Color getColorFromPlayer(PlayerColor color) {
        switch (color) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case BLACK:
                return Color.BLACK;
            case GREEN:
                return Color.GREEN;
            case WHITE:
                return Color.WHITE;
            case YELLOW:
                return Color.YELLOW;
            default:
                return Color.LIGHT_GRAY;
        }
    }
}
