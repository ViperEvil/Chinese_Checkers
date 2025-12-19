package chinese.checkers.Logic;

import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import chinese.checkers.Models.LeaderBoard;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.PlayerColor.PlayerColor;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class Game {
    private final SimpleBoard board;
    private Cell selectedCell = null;
    private final List<Cell> possibleCellMoves = new ArrayList<>();
    private PlayerColor currentTurn = PlayerColor.BLUE;
    private final List<PlayerColor> turnOrder;
    int currentTurnIndex;
    private PlayerColor nextTurn;
    private Runnable onTurnChanged;
    private final LeaderBoard leaderBoard;
    private Consumer<PlayerColor> onGameWon;
    private final MoveValidator moveValidator = new MoveValidator();

    public Game(SimpleBoard board, LeaderBoard leaderBoard) {
        this.board = board;

        turnOrder = new ArrayList<>(board.getHomeZones().keySet());
        turnOrder.sort(Comparator.comparing(Enum::ordinal));

        this.leaderBoard = leaderBoard;

        currentTurnIndex = 0;
        currentTurn = turnOrder.get(currentTurnIndex);
        nextTurn = turnOrder.get((currentTurnIndex + 1) % turnOrder.size());
    }

    private void clearSelection() {
        selectedCell = null;
        possibleCellMoves.clear();
    }

    public void selectedCell(Cell cell) {
        if (cell == null || cell.getPiece() == null) {
            clearSelection();
            return;
        }

        if (cell.getPiece().getColor() != currentTurn) {
            clearSelection();
            return;
        }

        selectedCell = cell;
        possibleCellMoves.clear();
        possibleCellMoves.addAll(moveValidator.getPossibleMoves(board, cell));
    }


    public void moveSelectedCell(Cell target) {
        if (selectedCell == null || !possibleCellMoves.contains(target)) {
            return;
        }

        target.setPiece(selectedCell.getPiece());
        selectedCell.setPiece(null);

        PlayerColor movedColor = target.getPiece().getColor();

        clearSelection();

        if (checkWin(movedColor)) {
            leaderBoard.addOneWin(movedColor);
            if (onGameWon != null) {
                onGameWon.accept(movedColor);
            }
        } else {
            nextTurn();
        }
    }

    private boolean checkWin(PlayerColor color) {
        Set<Cell> goalZone = board.getGoalZone(color);

        for (Cell cell : goalZone) {
            Piece piece = cell.getPiece();
            if (piece == null || piece.getColor() != color) {
                return false;
            }
        }
        return true;
    }

    public void setOnTurnChanged(Runnable callback) {
        this.onTurnChanged = callback;
    }

    private void nextTurn() {
        currentTurnIndex = (currentTurnIndex + 1) % turnOrder.size();
        currentTurn = turnOrder.get(currentTurnIndex);
        nextTurn = turnOrder.get((currentTurnIndex + 1) % turnOrder.size());
        System.out.println("Next turn: " + currentTurn);

        if (onTurnChanged != null) {
            onTurnChanged.run();
        }
    }

    public void setOnGameWon(Consumer<PlayerColor> callback) {
        this.onGameWon = callback;
    }

    public void forceWin(PlayerColor color) {
        leaderBoard.addOneWin(color);
        if (onGameWon != null) {
            onGameWon.accept(color);
        }
    }

    public String getCurrentTurn() {
        return currentTurn.toString();
    }

    public String getNextTurn() {
        return nextTurn.toString();
    }

    public SimpleBoard getBoard() {
        return board;
    }

    public List<Cell> getPossibleCellMoves() {
        return possibleCellMoves;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }
}
