public class Utility {
    private int value;

    public Utility(GameState s, int MAX) {
        value = getTokens(s);
    }

    public int getValue() {
        return value;
    }

    public int getTokens(GameState s) {
        if(s.getPlayerInTurn() == 1) {
            return s.countTokens()[1]-s.countTokens()[2];
        } else {
            return s.countTokens()[2]-s.countTokens()[1];
        }
    }
}
