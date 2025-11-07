package chinese.checkers.ui;

import chinese.checkers.Logic.Game;
import chinese.checkers.Models.Board.SimpleBoard;
import chinese.checkers.Models.Board.BoardSixPlayers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

public class Game_Window extends JFrame {
    private JPanel panelMain;
    private BoardPanel boardPanel;
    private SimpleBoard board;
    private Game game;
    private JButton reset;
    private JPanel boardContainer;
    private JButton newGameMode;

    public Game_Window(Supplier<SimpleBoard> typeBoard) {
        this.setTitle("Китайские шашки");
        board = typeBoard.get();
        game = new Game(board);
        boardPanel = new BoardPanel(board, game);

        boardContainer.setLayout(new BorderLayout());
        boardContainer.add(boardPanel, BorderLayout.CENTER);

        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.setLocationRelativeTo(null);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board = typeBoard.get();;
                game = new Game(board);

                boardContainer.removeAll();

                boardPanel = new BoardPanel(board, game);
                boardContainer.add(boardPanel, BorderLayout.CENTER);

                boardContainer.revalidate();
                boardContainer.repaint();
            }
        });

        newGameMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ChooseGame();
            }
        });
    }
}
