package chinese.checkers.ui;

import chinese.checkers.Models.Board;

import javax.swing.*;
import java.awt.*;

public class Game_Window extends JFrame {
    private JPanel panelMain;
    private BoardPanel boardPanel;
    private Board board;

    public Game_Window() {
        this.setTitle("Китайские шашки");

        panelMain = new JPanel();
        board = new Board();
        boardPanel = new BoardPanel(board);

        panelMain.add(boardPanel, BorderLayout.CENTER);

        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }
}
