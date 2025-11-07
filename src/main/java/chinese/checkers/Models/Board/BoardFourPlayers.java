package chinese.checkers.Models.Board;

import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.Player;
import chinese.checkers.Models.PlayerColor.PlayerColor;

public class BoardFourPlayers extends SimpleBoard {
    public BoardFourPlayers() {
        super();
        initBoard();
        initPieces();
        buildAdjacency();
    }

    @Override
    protected void initPieces() {
        for (Cell cell : cells.values()) {
            int x = cell.getX();
            int y = cell.getY();
            int z = -x - y;
            int r = size;

            /**
             * TODO: Сделать что-то с Player с их именами.
             * По-хорошему - всех назвать ботами и одному дать ник/имя игрока.
             * Захардкоженное "Test" лучше убрать, либо вообще не реализовывать Player.
             */
            if (y <= r && y > 0 && x > 0 && x <= r) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.BLACK));
            }
            //Нижний луч
            if (y < 0 && y >= -r && x >= -r && x < 0) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.BLUE));
            }

            // Право-верхний луч
            if (x > r && y < 0 && y >= -r && z >= -r) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.RED));
            }

            // Лево-верхний луч
            if (x >= -r && y >= r && z >= -r) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.WHITE));
            }

            if (Math.abs(x) <= r && Math.abs(y) <= r && Math.abs(z) <= r)
                cell.setPiece(null);
        }
    }
}
