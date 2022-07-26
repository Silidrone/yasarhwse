import java.util.ArrayList;

public class WordPuzzle {
    private char[][] grid;
    private ArrayList<String> words;

    WordPuzzle(char[][] grid, ArrayList<String> words) {
        this.grid = new char[grid.length][grid.length];

        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = grid[i];
        }

        this.words = new ArrayList<>(words);
    }

    WordPuzzle(WordPuzzle other) {
        grid = new char[other.grid.length][other.grid.length];

        for (int i = 0; i < other.grid.length; i++) {
            grid[i] = other.grid[i];
        }

        words = new ArrayList<>(other.words);
    }

    public String toString() {
        String result = "Puzzle:\n";

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                result += grid[i][j];
            }
            result += '\n';
        }

        return result + "Words:\n" + words;
    }

    ArrayList<String> getWords(char c) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            int indexOfC = words.get(i).indexOf(c);
            if (indexOfC == 0) {
                result.add(words.get(i));
            }
        }

        return result;
    }

    ArrayList<WordPosition> findWordOccurrences(int i, int j, String word) {
        ArrayList<WordPosition> result = new ArrayList<>();
        for (WordOrientation wordOrientation : WordOrientation.values()) {
            int row = i;
            int col = j;
            boolean word_found = true;
            for (char c : word.toCharArray()) {
                if (row < 0 || col < 0 || row == grid.length || col == grid[row].length || grid[row][col] != c) {
                    word_found = false;
                    break;
                }
                row += wordOrientation.getRow();
                col += wordOrientation.getCol();
            }
            if (word_found) {
                result.add(new WordPosition(i, j, word, wordOrientation));
            }
        }

        return result;
    }

    PuzzleSolution findWords() {
        ArrayList<WordPosition> wordPositions = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ArrayList<String> words_to_find = getWords(grid[i][j]);
                for (String word_to_find : words_to_find) {
                    ArrayList<WordPosition> wordOccurrences = findWordOccurrences(i, j, word_to_find);
                    if (wordOccurrences.size() > 0) {
                        wordPositions.addAll(wordOccurrences);
                    }
                }
            }
        }

        return new PuzzleSolution(wordPositions);
    }

    void printSolution() {
        PuzzleSolution puzzleSolution = findWords();
        char[][] gridCopy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            gridCopy[i] = new char[grid[i].length];
            for (int j = 0; j < grid[i].length; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }

        for (WordPosition wordPosition : puzzleSolution.getWordPositions()) {
            for (int i = 0, row = wordPosition.getRow(), col = wordPosition.getColumn();
                 i < wordPosition.getWord().length(); i++) {
                gridCopy[row][col] = Character.toUpperCase(gridCopy[row][col]);
                WordOrientation wordOrientation = wordPosition.getWordOrientation();
                row += wordOrientation.getRow();
                col += wordOrientation.getCol();
            }
        }
        System.out.println("Solution:");
        for (int i = 0; i < gridCopy.length; i++) {
            for (int j = 0; j < gridCopy[i].length; j++) {
                System.out.print(gridCopy[i][j]);
            }
            System.out.println();
        }
    }
}
