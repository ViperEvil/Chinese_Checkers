package chinese.checkers.Logic;

import chinese.checkers.Models.Board;
import chinese.checkers.Models.Cell;
import chinese.checkers.Models.Player;

import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private MoveValidator validator;
    private int currentPlayerIndex;

    public Game(ArrayList<Player> players) {
        this.players = players;
        this.board = new Board();
        this.validator = new MoveValidator(board);
        currentPlayerIndex = 0;
    }

    public void makeMove(Cell from, Cell to) {

    }

    public void nextTurn() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1)  % players.size();
    }
}
