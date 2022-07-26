import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
//        ArrayList<String> words = new ArrayList<>();
//        words.add("land");
//        words.add("mice");
//        words.add("melon");
//        words.add("one");
//        words.add("can");
//
//        WordPuzzle wordPuzzle = new WordPuzzle(new char[][]{
//                "akflandmers".toCharArray(),
//                "ngthgmecimt".toCharArray(),
//                "aoryexqklpp".toCharArray(),
//                "cslelirtnrt".toCharArray(),
//                "wedeskdflye".toCharArray(),
//                "opylmuencnz".toCharArray(),
//                "canitnrtode".toCharArray()
//        }, words);
//
//        System.out.println(wordPuzzle);
//        wordPuzzle.printSolution();
//        PuzzleSolution puzzleSolution = wordPuzzle.findWords();
//        System.out.print(puzzleSolution);
//
//        // testing intersects function
//        ArrayList<WordPosition> wordPositions = puzzleSolution.getWordPositions();
//        WordPosition a = wordPositions.get(1);
//        WordPosition b = wordPositions.get(2);
//        if(a.intersects(b)) {
//            System.out.println(a + " intersects with " + b);
//        } else {
//            System.out.println(a + "does not intersect with " + b);
//        }

        ArrayList<String> words = new ArrayList<>();
        words.add("lets");
        words.add("see");
        words.add("if");
        words.add("this");
        words.add("homework");
        words.add("is");
        words.add("done");

        WordPuzzle wordPuzzle = new WordPuzzle(new char[][]{
                "ohmnnnwiqxcfwolpeihl".toCharArray(),
                "yfnfdmlsyomscemyylzg".toCharArray(),
                "ewviiwjzdktftvztlqhv".toCharArray(),
                "dqmuiqfdltzsaonfygps".toCharArray(),
                "owferpdugqvgeduzmrev".toCharArray(),
                "meblfrurythyoneskpzb".toCharArray(),
                "vzsfpxrymdzpykocppvs".toCharArray(),
                "lmagajeykjlxwdwduyhj".toCharArray(),
                "vtvgpmnehmvkjhfjywta".toCharArray(),
                "azomythiukrowemoheco".toCharArray(),
                "ykiybelotpmizqcfpmsh".toCharArray(),
                "aoniqvksgplydhekzkmu".toCharArray(),
                "wgrzdswhdwdqfaptoluc".toCharArray(),
                "bwenwxggclgrogndskyk".toCharArray(),
                "ofnhheicgxwrjgkyxkzr".toCharArray(),
                "uvatfuiyeasgizhwejin".toCharArray(),
                "xhhpfkabkefxwlexvgpw".toCharArray(),
                "lifjsgibfqscjiaqskvy".toCharArray(),
                "sqszfnwwgorzuxowyntt".toCharArray(),
                "vqsvtdzqouqjlapftbhl".toCharArray(),
        }, words);
        System.out.println(wordPuzzle);
        wordPuzzle.printSolution();
        PuzzleSolution puzzleSolution = wordPuzzle.findWords();
        System.out.print(puzzleSolution);

        WordPuzzle copyW = new WordPuzzle(wordPuzzle);
        WordPosition copyWP = new WordPosition(puzzleSolution.getWordPositions().get(0));
        PuzzleSolution copyPS = new PuzzleSolution(puzzleSolution);
    }
}
