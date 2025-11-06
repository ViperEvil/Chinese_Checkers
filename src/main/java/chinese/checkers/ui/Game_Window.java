package chinese.checkers.ui;

import chinese.checkers.Logic.Game;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Board.BoardSixPlayers;

import javax.swing.*;
import java.awt.*;

public class Game_Window extends JFrame {
    private JPanel panelMain;
    private BoardPanel boardPanel;
    private SimpleBoard board;
    private Game game;

    public Game_Window() {
        this.setTitle("Китайские шашки");

        panelMain = new JPanel();
        board = new BoardSixPlayers();
        game = new Game(board);
        boardPanel = new BoardPanel(board, game);

        panelMain.add(boardPanel, BorderLayout.CENTER);

        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }
}
