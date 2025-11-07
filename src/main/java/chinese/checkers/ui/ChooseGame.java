package chinese.checkers.ui;

import chinese.checkers.Models.Board.BoardFourPlayers;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.BoardTwoPlayers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseGame extends JFrame {
    private JPanel panelMain;
    private JButton a6PlayersButton;
    private JButton a4PlayersButton;
    private JButton a2PlayersButton;
    private JLabel Title;

    public ChooseGame() {
        this.setTitle("Choose game type");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        this.setLocationRelativeTo(null);

        a6PlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new Game_Window(BoardSixPlayers::new);
            }
        });
        a4PlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new Game_Window(BoardFourPlayers::new);
            }
        });
        a2PlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new Game_Window(BoardTwoPlayers::new);
            }
        });
    }
}
