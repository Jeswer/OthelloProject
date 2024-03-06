public class MoveScore {
    private int score;
    private Position bestMove;

    public MoveScore(int score, Position bestMove) {
        this.score = score;
        this.bestMove = bestMove;
    }

    public int getScore() {
        return score;
    }

    public Position getPosition() {
        return bestMove;
    }
}
