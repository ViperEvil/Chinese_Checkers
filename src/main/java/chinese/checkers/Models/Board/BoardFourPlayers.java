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

public class BoardFourPlayers extends SimpleBoard {
    public BoardFourPlayers() {
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

            if (p == StarPoint.BOTTOM) {
                Piece piece = new Piece(new Player("Blue"), PlayerColor.BLUE);
                cell.setPiece(piece);
            }
            if (p == StarPoint.BOTTOM_LEFT) {
                Piece piece = new Piece(new Player("Red"), PlayerColor.RED);
                cell.setPiece(piece);
            }
            if (p == StarPoint.BOTTOM_RIGHT) {
                Piece piece = new Piece(new Player("Green"), PlayerColor.GREEN);
                cell.setPiece(piece);
            }
            if (p == StarPoint.TOP_RIGHT) {
                Piece piece = new Piece(new Player("Yellow"), PlayerColor.YELLOW);
                cell.setPiece(piece);
            }
        }

        homeZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.BOTTOM));
        goalZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.TOP_RIGHT));

        homeZones.put(PlayerColor.RED, pointsCells.get(StarPoint.BOTTOM_LEFT));
        goalZones.put(PlayerColor.RED, pointsCells.get(StarPoint.BOTTOM_RIGHT));

        homeZones.put(PlayerColor.GREEN, pointsCells.get(StarPoint.BOTTOM_RIGHT));
        goalZones.put(PlayerColor.GREEN, pointsCells.get(StarPoint.BOTTOM_LEFT));

        homeZones.put(PlayerColor.YELLOW, pointsCells.get(StarPoint.TOP_RIGHT));
        goalZones.put(PlayerColor.YELLOW, pointsCells.get(StarPoint.BOTTOM));
    }
}
