package chinese.checkers.Models.Board;

import chinese.checkers.Models.*;
import chinese.checkers.Models.PlayerColor.PlayerColor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BoardSixPlayers extends SimpleBoard {
    public BoardSixPlayers() {
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
            if (p == StarPoint.TOP_LEFT) {
                Piece piece = new Piece(new Player("Yellow"), PlayerColor.WHITE);
                cell.setPiece(piece);
            }
            if (p == StarPoint.TOP) {
                Piece piece = new Piece(new Player("Black"), PlayerColor.BLACK);
                cell.setPiece(piece);
            }
        }

        homeZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.BOTTOM));
        goalZones.put(PlayerColor.BLUE, pointsCells.get(StarPoint.TOP));

        homeZones.put(PlayerColor.BLACK, pointsCells.get(StarPoint.TOP));
        goalZones.put(PlayerColor.BLACK, pointsCells.get(StarPoint.BOTTOM));

        homeZones.put(PlayerColor.RED, pointsCells.get(StarPoint.BOTTOM_LEFT));
        goalZones.put(PlayerColor.RED, pointsCells.get(StarPoint.TOP_RIGHT));

        homeZones.put(PlayerColor.GREEN, pointsCells.get(StarPoint.BOTTOM_RIGHT));
        goalZones.put(PlayerColor.GREEN, pointsCells.get(StarPoint.TOP_LEFT));

        homeZones.put(PlayerColor.YELLOW, pointsCells.get(StarPoint.TOP_RIGHT));
        goalZones.put(PlayerColor.YELLOW, pointsCells.get(StarPoint.BOTTOM_LEFT));

        homeZones.put(PlayerColor.WHITE, pointsCells.get(StarPoint.TOP_LEFT));
        goalZones.put(PlayerColor.WHITE, pointsCells.get(StarPoint.BOTTOM_RIGHT));
    }
}
