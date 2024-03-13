import java.util.ArrayList;

public class Utility {
    public static int evaluateGameState(GameState s) {
        // number of legal moves left:
        ArrayList<Position> legalmoveList = s.legalMoves();
        s.changePlayer();
        ArrayList<Position> legalmoveList1 = s.legalMoves();
        s.changePlayer();
        // number of tokens
        int[] tokens = s.countTokens(); 

        

        return (int) (0.5 * (legalmoveList.size() - legalmoveList1.size())) + (int) (0.5 * (tokens[1] - tokens[0]));

    }
}
