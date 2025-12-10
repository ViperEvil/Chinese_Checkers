package chinese.checkers.Models.Board;

import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.Player;
import chinese.checkers.Models.PlayerColor.PlayerColor;

import java.util.HashSet;
import java.util.Set;

public class BoardTwoPlayers extends SimpleBoard {
    public BoardTwoPlayers() {
        super();
        initBoard();
        initPieces();
        buildAdjacency();
    }

    @Override
    protected void initPieces() {
        Set<Cell> blackHome = new HashSet<>();
        Set<Cell> blueHome = new HashSet<>();

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
            if (y <= r && y > 0 && x > 0 && x <= r && z < -4) {
                Piece piece = new Piece(new Player("Black"), PlayerColor.BLACK);
                cell.setPiece(piece);
                blackHome.add(cell);
            }
            //Нижний луч
            if (y < 0 && y >= -r && x >= -r && x < 0 && z > 4) {
                Piece piece = new Piece(new Player("Blue"), PlayerColor.BLUE);
                cell.setPiece(piece);
                blueHome.add(cell);
            }

            if (Math.abs(x) <= r && Math.abs(y) <= r && Math.abs(z) <= r)
                cell.setPiece(null);
        }

        homeZones.put(PlayerColor.BLACK, blackHome);
        homeZones.put(PlayerColor.BLUE, blueHome);

        goalZones.put(PlayerColor.BLACK, blackHome);
        goalZones.put(PlayerColor.BLUE, blueHome);
    }
}
