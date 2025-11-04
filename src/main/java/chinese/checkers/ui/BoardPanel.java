package chinese.checkers.ui;

import javax.swing.*;
import java.awt.*;
import chinese.checkers.Models.Board;
import chinese.checkers.Models.Cell;

public class BoardPanel extends JPanel {

    private final Board board;
    private final int cellRadius = 20;  // радиус кружочка
    private final int cellSpacing = 35; // расстояние между центрами

    public BoardPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(1000, 1000));
        setBackground(new Color(240, 240, 240));
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

            // Нарисуем кружочек
            g2.setColor(Color.LIGHT_GRAY);
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
}
