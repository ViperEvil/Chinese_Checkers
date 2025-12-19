package chinese.checkers.Logic;

import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Cell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoveValidator {
    private static final int[][] DIRECTIONS = {
            {1, 0}, {0, 1}, {-1, 1},
            {-1, 0}, {0, -1}, {1, -1}
    };

    public List<Cell> getPossibleMoves(SimpleBoard board, Cell from) {
        List<Cell> moves = new ArrayList<>();

        for (Cell neighbor : board.getNeighbors(from)) {
            if (neighbor.getPiece() == null) {
                moves.add(neighbor);
            }
        }

        Set<Cell> visited = new HashSet<>();
        visited.add(from);
        findJumps(board, from, moves, visited);

        return moves;
    }

    private void findJumps(SimpleBoard board, Cell from, List<Cell> moves, Set<Cell> visited) {
        for (int[] d : DIRECTIONS) {
            Cell mid = board.getCell(from.getX() + d[0], from.getY() + d[1]);
            Cell landing = board.getCell(from.getX() + 2*d[0], from.getY() + 2*d[1]);

            if (mid != null && mid.getPiece() != null &&
                    landing != null && landing.getPiece() == null &&
                    !visited.contains(landing)) {

                moves.add(landing);
                visited.add(landing);
                findJumps(board, landing, moves, visited);
                visited.remove(landing);
            }
        }
    }
}
