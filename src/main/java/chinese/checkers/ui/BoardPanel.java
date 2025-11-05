package chinese.checkers.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import chinese.checkers.Models.Board;
import chinese.checkers.Models.Cell;

public class BoardPanel extends JPanel {

    private final Board board;
    private final int cellRadius = 20;  // радиус кружочка
    private final int cellSpacing = 35; // расстояние между центрами
    private Cell hoveredCell = null;
    private Cell selectedCell = null;
    private List<Cell> possibleCellMoves = new ArrayList<>();

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(new Color(240, 240, 240));

        //Обработчик наведения мыши на ячейку с последующей подсветкой
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Cell cell = getCellAtPoint(e.getX(), e.getY());
                if (cell != hoveredCell) {
                    hoveredCell = cell;
                    repaint();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredCell = null;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Cell clickedCell = getCellAtPoint(e.getX(), e.getY());
                if (clickedCell != null && clickedCell.getPiece() != null) {
                    selectedCell = clickedCell;
                    possibleCellMoves.clear();

                    for (Cell neighbor : board.getNeighbors(clickedCell)) {
                        if (neighbor.getPiece() == null) {
                            possibleCellMoves.add(neighbor);
                        }
                    }
                } else if (possibleCellMoves.contains(clickedCell)) {
                    clickedCell.setPiece(selectedCell.getPiece());
                    selectedCell.setPiece(null);

                    selectedCell = null;
                    possibleCellMoves.clear();
                } else {
                    //Если выбрали где-то в другом месте - ячейки перестанут быть выделенными
                    selectedCell = null;
                    possibleCellMoves.clear();
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // включаем сглаживание для красивых кружков
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        for (Cell cell : board.getAllCells()) {
            int x = cell.getX();
            int y = cell.getY();
            int z = -x - y; // для симметрии можно не использовать, но пусть будет

            // Преобразуем логические координаты в экранные
            double screenX = cx + (x - y) * cellSpacing * Math.sqrt(3) / 2;
            double screenY = cy - (x + y) * cellSpacing * 1.2;

            //Области допустимых ходов при нажатии на фишку игрока
            if (selectedCell != null && possibleCellMoves.contains(cell)) {
                g2.setColor(new Color(150, 255, 150));
                g2.fillOval(
                        (int) (screenX - cellRadius - 4),
                        (int) (screenY - cellRadius - 4),
                        (int) ((cellRadius + 4) * 2),
                        (int) ((cellRadius + 4) * 2)
                );
            }

            //Обводка ячейки при наведении мыши на ячейку
            if (cell == hoveredCell) {
                g2.setColor(new Color(255, 255, 150)); // мягкая жёлтая подсветка
                g2.fillOval(
                        (int) (screenX - cellRadius - 4),
                        (int) (screenY - cellRadius - 4),
                        (int) ((cellRadius + 4) * 2),
                        (int) ((cellRadius + 4) * 2)
                );
            }

            // Цвет самой клетки (если есть шашка)
            if (cell.getPiece() != null) {
                g2.setColor(cell.getPiece().getColor());
            } else {
                g2.setColor(Color.LIGHT_GRAY);
            }

            g2.fillOval(
                    (int) (screenX - cellRadius),
                    (int) (screenY - cellRadius),
                    cellRadius * 2,
                    cellRadius * 2
            );

            // Контур
            g2.setColor(Color.BLACK);
            g2.drawOval(
                    (int) (screenX - cellRadius),
                    (int) (screenY - cellRadius),
                    cellRadius * 2,
                    cellRadius * 2
            );
        }
    }

    private Cell getCellAtPoint(int mx, int my) {
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        for (Cell cell : board.getAllCells()) {
            int x = cell.getX();
            int y = cell.getY();

            double screenX = cx + (x - y) * cellSpacing * Math.sqrt(3) / 2;
            double screenY = cy - (x + y) * cellSpacing * 1.2;

            double dist = Point.distance(mx, my, screenX, screenY);
            if (dist <= cellRadius) {
                return cell;
            }
        }
        return null;
    }
}
