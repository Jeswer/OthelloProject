import java.util.ArrayList;

public class Utility {

    private static final int MOBILITY_WEIGHT = 10;  // Weight for mobility
    private static final int TOKENS_WEIGHT = 100;   // Weight for token count
    private static final int CORNER_WEIGHT = 500;
    private static final int EDGE_WEIGHT = 200;     // Weight for edge 


    public static int evaluateGameState(GameState s) {
        int currentplayer = s.getPlayerInTurn();
        int mobility = calculateMobility(s, currentplayer);
        int tokens = calculateTokenCount(s, currentplayer);
        int corners = calculateCorners(s, currentplayer);
        int edges = calculateEdges(s, currentplayer);


        // Combine the components using the predefined weights
        return MOBILITY_WEIGHT * mobility
                + TOKENS_WEIGHT * tokens
                + CORNER_WEIGHT * corners
                + EDGE_WEIGHT * edges;

    }

    private static int calculateCorners(GameState s, int currentPlayer){
        if (currentPlayer == 1){
            return calculateCornerOccupancy(s, 1) - calculateCornerOccupancy(s, 2);
        }
        else{
            return calculateCornerOccupancy(s, 2) - calculateCornerOccupancy(s, 1);
        }
    }

    private static int calculateCornerOccupancy(GameState s, int currentPlayer) {
        int[][] board = s.getBoard();
        int cornerScore = 0;
        int bounds = board.length-1;
        cornerScore += board[0][0] == currentPlayer ? 1 : 0; // Top-left corner
        cornerScore += board[0][bounds] == currentPlayer ? 1 : 0; // Top-right corner
        cornerScore += board[bounds][0] == currentPlayer ? 1 : 0; // Bottom-left corner
        cornerScore += board[bounds][bounds] == currentPlayer ? 1 : 0; // Bottom-right corner
        return cornerScore;
    }

    private static int calculateTokenCount(GameState s, int currentPlayer) {
        int[] tokens = s.countTokens();
        if (currentPlayer == 1){
            return tokens[0] - tokens[1];
        }
        else{
            return tokens[1] - tokens[0];
        }
    }

    private static int calculateMobility(GameState s, int currentPlayer) {
        ArrayList<Position> legalMoves = s.legalMoves();
        s.changePlayer();
        ArrayList<Position> legalMoves2 = s.legalMoves();
        s.changePlayer();
        if (currentPlayer == 1){
            return legalMoves.size() - legalMoves2.size();
        }
        else{
            return legalMoves2.size() - legalMoves.size();
        }
    }

    private static int calculateEdges(GameState s, int currentPlayer){
        int[][] preferablePos = s.getBoard();
        ArrayList<Position> preferablePosList = new ArrayList<>();
    
        for (int i = 0; i < preferablePos.length; i++) {
            for (int j = 0; j < preferablePos[i].length; j++) {
                if (i == 0 && j != 0 && j < preferablePos[i].length-1 || i == preferablePos.length-1 && j != 0 && j < preferablePos[i].length-1 
                || j == 0 && i != 0 && i < preferablePos.length-1 || j == preferablePos.length-1 && i != 0 && i < preferablePos.length-1) {
                    preferablePosList.add(new Position(i, j));
                }
            }
        }
        int val = 0;
        for (Position pos1 : preferablePosList) {
            for (Position pos2 : s.legalMoves()) {
                if (pos1.equals(pos2)) {
                    val = 1;
                }
            }
        }
        return val;
    }

}
