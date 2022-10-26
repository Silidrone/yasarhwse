import java.util.ArrayList;

public class PuzzleSolution {
    private ArrayList<WordPosition> wordPositions;

    PuzzleSolution(ArrayList<WordPosition> wordPositions) {
        this.wordPositions = new ArrayList<>(wordPositions);
    }
    PuzzleSolution(PuzzleSolution other) {
        wordPositions = new ArrayList<>(other.wordPositions);
    }

    ArrayList<WordPosition> getWordPositions() {
        return wordPositions;
    }

    public String toString() {
        return "PuzzleSolution{" +
                "wordPositions=" + wordPositions +
                "}\n";
    }
}
