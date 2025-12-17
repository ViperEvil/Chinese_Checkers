import chinese.checkers.Logic.Game;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import chinese.checkers.Models.LeaderBoard;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EdgeTests {
    @Test
    void testSelectCellOutsideBoard() {
        SimpleBoard board = new BoardSixPlayers();
        LeaderBoard leaderBoard = new LeaderBoard();
        Game game = new Game(board, leaderBoard);

        Cell outside = board.getCell(100, 100); // null
        Assertions.assertNull(outside);

        game.selectedCell(outside);

        Assertions.assertNull(game.getSelectedCell());
        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty());
    }

    @Test
    void testSelectSameCellTwice() {
        SimpleBoard board = new BoardSixPlayers();
        LeaderBoard leaderBoard = new LeaderBoard();
        Game game = new Game(board, leaderBoard);

        Cell cell = board.getCell(-4, -2);
        Assertions.assertNotNull(cell.getPiece());

        game.selectedCell(cell);
        List<Cell> movesFirst = new ArrayList<>(game.getPossibleCellMoves());

        game.selectedCell(cell);
        List<Cell> movesSecond = game.getPossibleCellMoves();

        // Состояние не дублируется
        Assertions.assertEquals(movesFirst, movesSecond);
        Assertions.assertEquals(cell, game.getSelectedCell());
    }
}
