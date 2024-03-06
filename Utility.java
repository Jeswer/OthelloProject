public class Utility {

    public static int evaluateGameState(GameState s) {
        // Basic utility based on token difference
        int[] tokens = s.countTokens(); 
        return tokens[1] - tokens[0]; 
    }
}
