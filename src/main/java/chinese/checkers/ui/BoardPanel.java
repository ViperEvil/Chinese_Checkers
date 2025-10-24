package chinese.checkers.ui;

import chinese.checkers.Models.Board;
import chinese.checkers.Models.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BoardPanel extends JPanel {
    private static final int CELL_RADIUS = 14;
    private static final int SPACING = 33;

    private final ArrayList<Ellipse2D> cells = new ArrayList<>();
    private Cell selectedCell = null;
    private Ellipse2D hoverecCell = null;

    private final Board board;

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(600, 600));
        setBackground(new Color(240, 240, 240));

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handleMouseMove(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        drawPieces(g2, centerX, centerY);
        drawStar(g2, centerX, centerY);
    }

    private void drawStar(Graphics2D g2, int cx, int cy) {
        g2.setColor(Color.GRAY);

        int[][] rowLengths = {
                {1}, {2}, {3}, {4}, {13}, {12}, {11}, {10},
                {9},
                {10}, {11}, {12}, {13}, {4}, {3}, {2}, {1}
        };

        int startY = cy - (rowLengths.length / 2) * SPACING;

        for (int r = 0; r < rowLengths.length; r++) {
            int length = rowLengths[r][0];
            int y = startY + r * SPACING;
            int offsetX = cx - (length * SPACING) / 2;

            for (int c = 0; c < length; c++) {
                int x = offsetX + c * SPACING;
                Ellipse2D circle = new Ellipse2D.Double(x - CELL_RADIUS, y - CELL_RADIUS,
                        CELL_RADIUS * 2, CELL_RADIUS * 2);
                cells.add(circle);

                if (circle.equals(hoverecCell)) {
                    g2.setColor(new Color(100, 180, 255, 188));
                    g2.draw(circle);
                } else {
                    g2.setColor(Color.DARK_GRAY);
                    g2.draw(circle);
                }
            }
        }
    }

    private void drawPieces(Graphics2D g2, int cx, int cy) {
        for (Cell cell : board.getCells().values()) {
            if (cell.getPiece() == null) {
                continue;
            }

            int r = cell.getR();
            int q = cell.getQ();

            int x = pixelX(q, r);
            int y = pixelY(q, r);

            int offsetY = -8;

            switch (cell.getPiece().getColor()) {
                case RED:
                    g2.setColor(Color.RED);
                    break;
                default:
                    g2.setColor(Color.GRAY);
                    break;
            }

            g2.fill(new Ellipse2D.Double(
                    x - CELL_RADIUS - SPACING / 2,
                    y - CELL_RADIUS + offsetY * SPACING,
                    CELL_RADIUS * 2,
                    CELL_RADIUS * 2
            ));

            if (cell == selectedCell) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.draw(new Ellipse2D.Double(
                        x - CELL_RADIUS - SPACING / 2,
                        y - CELL_RADIUS + offsetY * SPACING,
                        CELL_RADIUS * 2,
                        CELL_RADIUS * 2
                ));
            }
        }
    }

    private void handleMouseMove(MouseEvent e) {
        Point p = e.getPoint();
        Ellipse2D found = null;

        for (Ellipse2D circle : cells) {
            if (circle.contains(p)) {
                found = circle;
                break;
            }
        }

        if (found != hoverecCell) {
            hoverecCell = found;
            repaint();
        }
    }

    private void handleMousePress(MouseEvent e) {
        Cell clicked = findCellByClick(e.getX(), e.getY());
        if (clicked != null && clicked.getPiece() != null) {
            selectedCell = clicked;
            repaint();
        }
    }

    private void handleMouseRelease(MouseEvent e) {
        if (selectedCell == null) return;

        Cell target = findCellByClick(e.getX(), e.getY());
        if (target != null && target.getPiece() == null) {
            target.setPiece(selectedCell.getPiece());
            selectedCell.setPiece(null);
        }

        selectedCell = null;
        repaint();
    }

    private Cell findCellByClick(int x, int y) {
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        for (Cell cell : board.getCells().values()) {
            int cellX = pixelX(cell.getQ(), cell.getR());
            int cellY = pixelY(cell.getQ(), cell.getR());
            double dx = x - cellX;
            double dy = y - cellY;
            if (Math.sqrt(dx * dx + dy * dy) < CELL_RADIUS + 5) {
                return cell;
            }
        }

        return null;
    }

    private int pixelX(int q, int r) {
        int centerX = getWidth() / 2;
        return centerX + q * SPACING + r * SPACING / 2;
    }

    private int pixelY(int q, int r) {
        int centerY = getHeight() / 2;
        return centerY + r * SPACING;
    }
}
