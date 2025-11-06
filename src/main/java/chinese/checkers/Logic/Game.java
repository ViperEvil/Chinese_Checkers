package chinese.checkers.Logic;

import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final SimpleBoard board;
    private Cell selectedCell = null;
    private final List<Cell> possibleCellMoves = new ArrayList<>();

    public Game(SimpleBoard board) {
        this.board = board;
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
            target.setPiece(selectedCell.getPiece());
            selectedCell.setPiece(null);
            selectedCell = null;
            possibleCellMoves.clear();
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

                    //visited.remove(jumpCell);
                }
            }
        }
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
