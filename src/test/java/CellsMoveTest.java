import chinese.checkers.Logic.Game;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellsMoveTest {
    @Test
    void testMoveSelectedCellToValidTarget() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell selected = board.getCell(-4, -2);
        game.selectedCell(selected);

        Cell target = game.getPossibleCellMoves().get(0);

        game.moveSelectedCell(target);

        //фигура переместилась
        Assertions.assertEquals(selected.getPiece(), null);
        Assertions.assertNotNull(target.getPiece());

        //selectedCell и возможные ходы должны сброситься
        Assertions.assertNull(game.getSelectedCell());
        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty());
    }

    @Test
    void testMoveSelectedCellToInvalidTarget() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell selected = board.getCell(-4, -2);
        game.selectedCell(selected);

        //Берём клетку, которая не находится в possibleCellMoves
        Cell invalidTarget = board.getCell(0, 0);
        if (game.getPossibleCellMoves().contains(invalidTarget)) {
            Assertions.fail("Выбранная клетка НЕ должна быть в possibleCellMoves");
        }

        game.moveSelectedCell(invalidTarget);

        //фигура не должна перемещаться
        Assertions.assertNotNull(selected.getPiece());
        Assertions.assertNull(invalidTarget.getPiece());

        //selectedCell и possibleCellMoves остаются прежними
        Assertions.assertEquals(selected, game.getSelectedCell());
        Assertions.assertFalse(game.getPossibleCellMoves().isEmpty());
    }

    @Test
    void testMoveSelectedCellWithoutSelection() {
        SimpleBoard board = new BoardSixPlayers();
        Game game = new Game(board);

        Cell target = board.getCell(-4, -2);

        //selectedCell ещё не выбран
        game.moveSelectedCell(target);

        Assertions.assertTrue(game.getPossibleCellMoves().isEmpty());
        Assertions.assertNull(game.getSelectedCell());
        Assertions.assertNotNull(target.getPiece()); //фигура на месте
    }
}
