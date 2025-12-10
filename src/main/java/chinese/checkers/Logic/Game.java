package chinese.checkers.Logic;

import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Piece;
import chinese.checkers.Models.PlayerColor.PlayerColor;

import java.util.*;

public class Game {
    private final SimpleBoard board;
    private Cell selectedCell = null;
    private final List<Cell> possibleCellMoves = new ArrayList<>();
    private PlayerColor currentTurn = PlayerColor.BLUE;
    private List<PlayerColor> turnOrder;
    int currentTurnIndex;
    private PlayerColor nextTurn;
    private Runnable onTurnChanged;


    public Game(SimpleBoard board) {
        this.board = board;

        turnOrder = new ArrayList<>(board.getHomeZones().keySet());
        turnOrder.sort(Comparator.comparing(Enum::ordinal));

        currentTurnIndex = 0;
        currentTurn = turnOrder.get(currentTurnIndex);
        nextTurn = turnOrder.get((currentTurnIndex + 1) % turnOrder.size());
    }

    public void selectedCell(Cell cell) {
        if (cell != null && cell.getPiece() != null) {
            selectedCell = cell;
            possibleCellMoves.clear();
            calculatePossibleMoves(cell);
        } else {
            selectedCell = null;
            possibleCellMoves.clear();
        }
    }

    public void moveSelectedCell(Cell target) {
        if (target != null && possibleCellMoves.contains(target)) {
            if (selectedCell.getPiece().getColor() != currentTurn) {
                return;
            }

            target.setPiece(selectedCell.getPiece());
            selectedCell.setPiece(null);

            PlayerColor movedColor = target.getPiece().getColor();
            System.out.println(movedColor);

            selectedCell = null;
            possibleCellMoves.clear();

            if (checkWin(movedColor)) {
                System.out.printf("Player %s win%n", movedColor);
            } else {
                nextTurn();
            }
        }
    }

    private void calculatePossibleMoves(Cell cell) {
        for (Cell neighbor : board.getNeighbors(cell)) {
            if (neighbor.getPiece() == null) {
                possibleCellMoves.add(neighbor);
            }
        }

        Set<Cell> visited = new HashSet<>();
        visited.add(cell);
        findJumps(cell, possibleCellMoves, visited);
    }

    private void findJumps(Cell from, List<Cell> jumps, Set<Cell> visited) {
        int[][] directions = {
                {1, 0}, {0, 1}, {-1, 1},
                {-1, 0}, {0, -1}, {1, -1}
        };

        for (int[] d : directions) {
            int nx = from.getX() + d[0];
            int ny = from.getY() + d[1];

            Cell neighbor = board.getCell(nx, ny);
            if (neighbor != null && neighbor.getPiece() != null) {
                int jx = from.getX() + 2*d[0];
                int jy = from.getY() + 2*d[1];
                Cell jumpCell = board.getCell(jx, jy);

                if (jumpCell != null && jumpCell.getPiece() == null && !visited.contains(jumpCell)) {
                    jumps.add(jumpCell);
                    visited.add(jumpCell);

                    findJumps(jumpCell, jumps, visited);

                    visited.remove(jumpCell);
                }
            }
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
