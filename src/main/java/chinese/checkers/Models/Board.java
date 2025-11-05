package chinese.checkers.Models;

import java.util.*;

public class Board {
    private final int size = 4;
    private Map<Coordinate, Cell> cells;
    private Map<Cell, List<Cell>> adjacency;


    public Board() {
        cells = new HashMap<>();
        adjacency = new HashMap<>();
        initBoard();
        initPieces();
        buildAdjacency();
    }

    public Cell getCell(int x, int y) {
        return cells.get(new Coordinate(x, y));
    }

    public Collection<Cell> getAllCells() {
        return cells.values();
    }

    public List<Cell> getNeighbors(Cell cell) {
        return adjacency.getOrDefault(cell, Collections.emptyList());
    }

    public int getSize() {
        return size;
    }

    private void initBoard() {
        for (int x = -size * 2; x <= size * 2; x++) {
            for (int y = -size * 2; y <= size * 2; y++) {
                if (isInStar(x, y)) {
                    Cell cell = new Cell(x, y);
                    cells.put(new Coordinate(x, y), cell);
                }
            }
        }
    }

    //Шестиугольник представляет собой 3д, с тремя осями (с наклоном в сторону).
    //Так проще понять, каким образом получаются x y и z для представления
    //звезды давида (или базовый шестиугольник)
    // https://www.google.com/url?sa=i&url=https%3A%2F%2Fhabr.com%2Fru%2Farticles%2F319644%2F&psig=AOvVaw37RwwMgH7U_uEjp-mI0JfY&ust=1762338670171000&source=images&cd=vfe&opi=89978449&ved=0CBUQjRxqGAoTCIC64aSl2JADFQAAAAAdAAAAABC4AQ
    private boolean isInStar(int x, int y) {
        // Упрощённое условие: можно заменить на реальную маску звезды
        int z = -x - y;
        int r = size;
        // Центральный шестиугольник
        if (Math.abs(x) <= r && Math.abs(y) <= r && Math.abs(z) <= r)
            return true;

        // Верхний луч
        if (y <= r && y > 0 && x > 0 && x <= r) {
            return true;
        }

        //Нижний луч
        if (y < 0 && y >= -r && x >= -r && x < 0) {
            return true;
        }

        // Право-верхний луч
        if (x > r && y < 0 && y >= -r && z >= -r) {
            return true;
        }

        // Право-нижний луч
        if (z <= r && x > 0 && x <= r && y < 0) {
            return true;
        }

        // Лево-нижний луч
        if (x < -r && y > 0 && y <= r && z <= r) {
            return true;
        }

        // Лево-верхний луч
        if (x >= -r && y >= r && z >= -r) {
            return true;
        }

        return false;
    }

    private void buildAdjacency() {
        int[][] directions = {
                {1, 0}, {0, 1}, {-1, 1},
                {-1, 0}, {0, -1}, {1, -1}
        };

        for (Cell cell : cells.values()) {
            List<Cell> neighbors = new ArrayList<>();
            for (int[] d : directions) {
                int nx = cell.getX() + d[0];
                int ny = cell.getY() + d[1];
                Cell neighbor = cells.get(new Coordinate(nx, ny));
                if (neighbor != null) {
                    neighbors.add(neighbor);
                }
            }
            adjacency.put(cell, neighbors);
        }
    }

    private void initPieces() {
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

            // Право-нижний луч
            if (z <= r && x > 0 && x <= r && y < 0) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.YELLOW));
            }

            // Лево-нижний луч
            if (x < -r && y > 0 && y <= r && z <= r) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.GREEN));
            }

            // Лево-верхний луч
            if (x >= -r && y >= r && z >= -r) {
                cell.setPiece(new Piece(new Player("Test"), PlayerColor.WHITE));
            }

            if (Math.abs(x) <= r && Math.abs(y) <= r && Math.abs(z) <= r)
                cell.setPiece(null);
        }
    }

    @Override
    public String toString() {
        return "Board with " + cells.size() + " cells";
    }
}
