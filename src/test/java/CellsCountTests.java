import chinese.checkers.Models.Board.BoardFourPlayers;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.BoardTwoPlayers;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.PlayerColor.PlayerColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CellsCountTests {
    @Test
    void boardHasExpectedCellsCount() {
        SimpleBoard board = new BoardSixPlayers();

        Assertions.assertEquals(121, board.getAllCells().size());
    }

    @Test
    void sixPlayersHasExpectedCellsCount() {
        SimpleBoard board = new BoardSixPlayers();

        Map<PlayerColor, Integer> countMap = new EnumMap<>(PlayerColor.class);

        for (PlayerColor color : PlayerColor.values()) {
            countMap.put(color, 0);
        }

        for (Cell cell : board.getAllCells()) {
            Piece piece = cell.getPiece();
            if (piece == null) {
                continue;
            }

            PlayerColor playerColor = piece.getColor();

            countMap.put(playerColor, countMap.get(playerColor) + 1);
        }

        int excepted = 10;

        for (PlayerColor color : PlayerColor.values()) {
            Assertions.assertEquals(excepted, countMap.get(color),
                    "Игрок " + color + " должен иметь " + excepted + " фигур");
        }
    }

    @Test
    void fourPlayersHasExpectedCellsCount() {
        SimpleBoard board = new BoardFourPlayers();

        List<PlayerColor> activeColors = List.of(
                PlayerColor.BLACK,
                PlayerColor.BLUE,
                PlayerColor.RED,
                PlayerColor.WHITE
        );

        Map<PlayerColor, Integer> countMap = new EnumMap<>(PlayerColor.class);

        for (PlayerColor color : activeColors) {
            countMap.put(color, 0);
        }

        for (Cell cell : board.getAllCells()) {
            Piece piece = cell.getPiece();
            if (piece == null) {
                continue;
            }

            PlayerColor playerColor = piece.getColor();

            countMap.put(playerColor, countMap.get(playerColor) + 1);
        }

        int excepted = 10;

        for (PlayerColor color : activeColors) {
            Assertions.assertEquals(excepted, countMap.get(color),
                    "Игрок " + color + " должен иметь " + excepted + " фигур");
        }
    }

    @Test
    void twoPlayersHasExpectedCellsCount() {
        SimpleBoard board = new BoardTwoPlayers();

        List<PlayerColor> activeColors = List.of(
                PlayerColor.BLACK,
                PlayerColor.BLUE
        );

        Map<PlayerColor, Integer> countMap = new EnumMap<>(PlayerColor.class);

        for (PlayerColor color : activeColors) {
            countMap.put(color, 0);
        }

        for (Cell cell : board.getAllCells()) {
            Piece piece = cell.getPiece();
            if (piece == null) {
                continue;
            }

            PlayerColor playerColor = piece.getColor();

            countMap.put(playerColor, countMap.get(playerColor) + 1);
        }

        int excepted = 10;

        for (PlayerColor color : activeColors) {
            Assertions.assertEquals(excepted, countMap.get(color),
                    "Игрок " + color + " должен иметь " + excepted + " фигур");
        }
    }
}
