package chinese.checkers.Models;

import chinese.checkers.Models.PlayerColor.PlayerColor;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class LeaderBoard {
    private final Map<PlayerColor, Integer> board = new EnumMap<>(PlayerColor.class);

    public LeaderBoard() {
        for (PlayerColor color : PlayerColor.values()) {
            board.put(color, 0);
        }
    }

    public Map<PlayerColor, Integer> getBoard() {
        return board;
    }

    public int getWinsCount(PlayerColor color) {
        return board.get(color);
    }

    public void addOneWin(PlayerColor color) {
        board.put(color, board.get(color) + 1);
    }

    public void reset() {
        board.replaceAll((c, v) -> 0);
    }
}
