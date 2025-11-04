package chinese.checkers;

import chinese.checkers.Models.Board;
import chinese.checkers.ui.Game_Window;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(() -> new Game_Window().setVisible(true));

        Board board = new Board();
        System.out.println(board);
    }
}