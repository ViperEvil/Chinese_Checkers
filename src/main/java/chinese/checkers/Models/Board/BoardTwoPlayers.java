package chinese.checkers.Models.Board;

import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.Player;
import chinese.checkers.Models.PlayerColor.PlayerColor;
import chinese.checkers.Models.StarPoint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        Map<StarPoint, Set<Cell>> pointsCells = new HashMap<>();
        for (StarPoint sp : StarPoint.values()) {
            pointsCells.put(sp, new HashSet<>());
        }

        for (Cell cell : cells.values()) {
            StarPoint p = getStarPoint(cell);
            if (p != null) {
                pointsCells.get(p).add(cell);
            }

            if (p == StarPoint.TOP) {
                Piece piece = new Piece(new Player("Black"), PlayerColor.BLACK);
                cell.setPiece(piece);
            }
            if (p == StarPoint.BOTTOM) {
                Piece piece = new Piece(new Player("Blue"), PlayerColor.BLUE);
                cell.setPiece(piece);
            }
        }

        homeZones.put(PlayerColor.BLACK, pointsCells.get(StarPoint.TOP));
        homeZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.BOTTOM));
        goalZones.put(PlayerColor.BLACK, pointsCells.get(StarPoint.BOTTOM));
        goalZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.TOP));
    }
}
