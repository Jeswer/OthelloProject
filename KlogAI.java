import java.util.ArrayList;

public class KlogAI implements IOthelloAI {

    /**
     * Takes the calculaled max score and calls the associated position of the move
     * @param s The current game state
     * @return The position of the movescore
     */
    public Position decideMove(GameState s) {
        MoveScore fMoveScore = maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, 6);
        return fMoveScore.getPosition();
    }

    /**
     * Calculate the best possible move for the player based on:
     * @param s The current game state
     * @param alpha The lowest possible min value (used for prunning)
     * @param beta The highest possible max value (used for prunning)
     * @param depth The depth of recurrision used for search
     * @return the most valuable move
     */
    private MoveScore maxValue(GameState s, int alpha, int beta, int depth) {
        ArrayList<Position> legalMoves = s.legalMoves();
        Position pos = null;
        if (depth == 0 || legalMoves.isEmpty()) {
            int util = Utility.evaluateGameState(s);
            return new MoveScore(util, null);
        } else {
            int v = Integer.MIN_VALUE;
            for (Position p : legalMoves) {
                GameState nGameState = new GameState(s.getBoard(), s.getPlayerInTurn()); 
                nGameState.insertToken(p, null);
                int v2 = minValue(nGameState, alpha, beta, depth - 1).getScore();
                
                if (v2 > v) {
                    v = v2;
                    pos = p;
                    alpha = Math.max(alpha, v);
                } 

                if (v >= beta) {
                    return new MoveScore(v, pos);
                } 
            }
            return new MoveScore(v, pos);
        }

    }
    /**
     * Calculate the best possible move for the opponent based on: 
     * @param s The current game state
     * @param alpha The lowest possible min value (used for prunning)
     * @param beta The highest possible max value (used for prunning)
     * @param depth The depth of recursive steps used for search
     * @return The least valuable move
     */
    private MoveScore minValue(GameState s, int alpha, int beta, int depth) {
        ArrayList<Position> legalMoves = s.legalMoves();
        Position pos = null;
        if(depth == 0 || legalMoves.isEmpty()) {
            int util = Utility.evaluateGameState(s);
            return new MoveScore(util, null);
        } else {
            int v = Integer.MAX_VALUE;
            for (Position p : legalMoves) {
                GameState nGameState = new GameState(s.getBoard(), s.getPlayerInTurn()); 
                nGameState.insertToken(p, null);
                int v2 = maxValue(nGameState, alpha, beta, depth - 1).getScore();
                if(v2 < v) {
                    v = v2;
                    pos = p;
                    beta = Math.min(beta,v);
                }
                if(v <= alpha) {
                    return new MoveScore(v, pos);
                }
            }
            return new MoveScore(v, pos);
        }
    }

}