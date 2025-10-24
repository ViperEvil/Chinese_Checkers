package chinese.checkers.Models;

import java.util.HashMap;

public class Board {
    private HashMap<String, Cell> cells = new HashMap<>();

    public Board() {
        initCells();
        initPieces();
    }

    private void initCells() {
        int[][] rowLengths = {
                {1}, {2}, {3}, {4}, {13}, {12}, {11}, {10},
                {9},
                {10}, {11}, {12}, {13}, {4}, {3}, {2}, {1}
        };

        int rowIndex = 0;
        for (int[] row : rowLengths) {
            int length = row[0];
            for (int q = 0; q < length; q++) {
                Cell cell = new Cell(q - length / 2, rowIndex - rowLengths.length / 2);
                cells.put(getKey(cell.getQ(), cell.getR()), cell);
            }
            rowIndex++;
        }
    }

    private void initPieces() {
        int[][] rowLengths = {
                {1}, {2}, {3}, {4}
        };

        int rowIndex = 0;

        for (int[] row : rowLengths) {
            int length = row[0];
            for (int q = 0; q < length; q++) {
                Cell cell = getCell(q - length + 1, rowIndex);
                if (cell != null) {
                    cell.setPiece(new Piece(new Player("Carl", PlayerColor.RED)));
                }
            }
            rowIndex++;
        }
    }

    public String getKey(int q, int r) {
        return q + "," + r;
    }

    public Cell getCell(int q, int r) {
        return cells.get(getKey(q, r));
    }

    public HashMap<String, Cell> getCells() {
        return cells;
    }
}
