package chinese.checkers.Models;

import chinese.checkers.Models.Board.BoardFourPlayers;
import chinese.checkers.Models.Board.BoardSixPlayers;
import chinese.checkers.Models.Board.BoardTwoPlayers;
import chinese.checkers.Models.Board.SimpleBoard;

import java.util.function.Supplier;

public enum GameMode {
    TWO(BoardTwoPlayers::new),
    FOUR(BoardFourPlayers::new),
    SIX(BoardSixPlayers::new);

    private final Supplier<SimpleBoard> boardFactory;

    GameMode(Supplier<SimpleBoard> boardFactory) {
        this.boardFactory = boardFactory;
    }

    public Supplier<SimpleBoard> getBoardFactory() {
        return boardFactory;
    }

    public LeaderBoard createLeaderBoard() {
        SimpleBoard board = boardFactory.get();
        return new LeaderBoard(board.getHomeZones().keySet());
    }
}
