import chinese.checkers.Logic.Game;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CellsSelectTests {
    @Test
    void testNullCellSelection() {
        Game game = new Game(new BoardSixPlayers());

        Cell cell = new Cell(0, 0);
        cell.setPiece(null);

        game.selectedCell(cell);

        Assertions.assertNull(game.getSelectedCell(),
                "При выборе ячейки без фишки должен быть selectedCell = null");
        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty(),
                "При selectedCell = null список possibleCellMoves должен быть пустым");
    }

    @Test
    void testWithSelectedCell() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        //Должна быть чёрная фишка
        Cell cellWithPiece = board.getCell(-4, -2);
        assertNotNull(cellWithPiece.getPiece(),
                "Ячейка должна иметь фишку");

        game.selectedCell(cellWithPiece);

        Assertions.assertEquals(cellWithPiece, game.getSelectedCell(),
                "Выбранная ячейка должна быть той, что задали в условие");
        Assertions.assertFalse(game.getPossibleCellMoves().isEmpty(),
                "Если выбрана ячейка с фишкой, то список ходов не должен быть пустым");
    }

    @Test
    void testReselectAnotherCell() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell first = board.getCell(-4, -2);
        Cell second = board.getCell(-4, -3);

        Assertions.assertNotNull(first.getPiece());
        Assertions.assertNotNull(second.getPiece());

        game.selectedCell(first);

        List<Cell> firstMoves = new ArrayList<>(game.getPossibleCellMoves());

        game.selectedCell(second);

        Assertions.assertEquals(second, game.getSelectedCell());
        Assertions.assertNotEquals(firstMoves, game.getPossibleCellMoves());
    }

    @Test
    void testSelectEmptyCellClearsSelection() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell filled = board.getCell(-4, -2);
        game.selectedCell(filled);
        Assertions.assertNotNull(game.getSelectedCell());

        Cell empty = board.getCell(0, 0);
        Assertions.assertTrue(empty.isEmpty());

        game.selectedCell(empty);

        Assertions.assertNull(game.getSelectedCell());
        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty());
    }

    @Test
    void testSelectNullClearsSelection() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell withPiece = board.getCell(-4, -2);
        game.selectedCell(withPiece);
        Assertions.assertNotNull(game.getSelectedCell());

        game.selectedCell(null);

        Assertions.assertNull(game.getSelectedCell());
        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty());
    }

    @Test
    void testSwitchSelectedCells() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell a = board.getCell(-4, -2);
        Cell b = board.getCell(-4, -3);

        game.selectedCell(a);
        Assertions.assertEquals(a, game.getSelectedCell());

        game.selectedCell(b);
        Assertions.assertEquals(b, game.getSelectedCell());
    }

}
