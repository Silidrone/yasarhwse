public class Main {
    public static void main(String[] args) {
        String inputFile = "sample.txt";
        String wbl_file = "wbl.txt";
        String fo_file = "fo.txt";
        String f_file = "f.txt";
        FileProcessor fileProcessor = new FileProcessor();

        try {
            fileProcessor.countWordsByLetter(inputFile, wbl_file);
            System.out.println("Number of words in " + inputFile + ": " + fileProcessor.countWords(inputFile));
            System.out.println("Number of `lorem` words in " + inputFile + ": " + fileProcessor.countWord(inputFile, "lorem"));
            fileProcessor.filterOut(inputFile, fo_file, "lorem");
            fileProcessor.filter(inputFile, f_file, 4);
        } catch (FileProcessorException e) {
            System.out.println(e.getMessage());
        }
    }
}
