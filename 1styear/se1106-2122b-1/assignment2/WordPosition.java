enum WordOrientation {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    TOP_LEFT(-1, -1),
    TOP_RIGHT(-1, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM_RIGHT(1, 1);

    private final int rowDirection;
    private final int colDirection;

    WordOrientation(int rowDirection, int colDirection) {
        this.rowDirection = rowDirection;
        this.colDirection = colDirection;
    }

    public int getRow() {
        return rowDirection;
    }
    public int getCol() {
        return colDirection;
    }
}

public class WordPosition {
    private String word;
    private int row;
    private int column;
    private WordOrientation wordOrientation;

    WordPosition(int row, int column, String word, WordOrientation wordOrientation) {
        this.row = row;
        this.column = column;
        this.word = word;
        this.wordOrientation = wordOrientation;
    }

    WordPosition(WordPosition other) {
        word = other.word;
        row = other.row;
        column = other.column;
        wordOrientation = other.wordOrientation;
    }

    public String toString() {
        return "WordPosition{" +
                "word='" + word + '\'' +
                ", row=" + row +
                ", column=" + column +
                ", wordOrientation=" + wordOrientation +
                '}';
    }

    public boolean intersects(WordPosition other) { //I don't fully understand why this function takes place in the program, so I implement it but never use it.
        for (int i = row, j = column; i >= 0 && j >= 0 && i < word.length() && j < word.length(); i += wordOrientation.getRow(), j += wordOrientation.getCol()) {
            for (int k = other.row, l = other.column; k >= 0 && l >= 0 && k < other.word.length() && l < other.word.length();
                 k += other.wordOrientation.getRow(), l += other.wordOrientation.getCol()) {
                if (i == k && j == l) return true;
            }
        }

        return false;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public WordOrientation getWordOrientation() {
        return wordOrientation;
    }

    public String getWord() {
        return word;
    }
}
