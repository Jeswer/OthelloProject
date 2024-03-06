import java.util.ArrayList;

public class KlogAI implements IOthelloAI {

    public Position decideMove(GameState s) {
        return maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE).getPosition();
    }

    public MoveScore maxValue(GameState s, int alpha, int beta) {
        ArrayList<Position> moves = s.legalMoves();
        Position bestMove = new Position(0, 0);
        if (s.isFinished()) {
            return null;
        } else {
            int v = Integer.MAX_VALUE;
            GameState newState = new GameState(s.getBoard(), s.getPlayerInTurn());
            for (Position move : moves) {
                s.insertToken(move, null);
                MoveScore scoreMove = minValue(newState, alpha, beta);
                int v2 = scoreMove.getScore();
                if (v2 > v) {
                    v = v2;
                    bestMove = move;
                }
                alpha = Math.max(alpha, v);
                if (alpha >= beta) {
                    break;
                }
            }
            return new MoveScore(v, bestMove);

        }
    }

    public MoveScore minValue(GameState s, int alpha, int beta) {
        ArrayList<Position> moves = s.legalMoves();
        Position bestMove = new Position(0, 0);
        if (s.isFinished()) {
            return null;
        } else {
            int v = Integer.MAX_VALUE;
            GameState newState = new GameState(s.getBoard(), s.getPlayerInTurn());
            for (Position move : moves) {
                s.insertToken(move, null);
                MoveScore scoreMove = maxValue(newState, alpha, beta);
                int v2 = scoreMove.getScore();
                if (v2 > v) {
                    v = v2;
                    bestMove = move;
                }

                alpha = Math.max(alpha, v);
                if (v >= beta) {
                    break;
                }
            }
            return new MoveScore(v, bestMove);

        }
    }
}
