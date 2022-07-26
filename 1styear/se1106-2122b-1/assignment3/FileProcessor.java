import java.io.*;
import java.util.*;

public class FileProcessor {
    private ArrayList<String> getAllWords(String inputFileName) throws FileProcessorException {
        ArrayList<String> words = new ArrayList<>();
        File f = new File(inputFileName);
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    for (String word : line.split(" ")) {
                        if (word.length() > 0) {
                            words.add(word.toLowerCase().replaceAll("\\p{P}", ""));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileProcessorException(e.getMessage());
        }

        return words;
    }

    void countWordsByLetter(String inputFileName, String outputFIleName) throws FileProcessorException {
        TreeMap<Character, Integer> occurrences = new TreeMap<>();
        for (String word : getAllWords(inputFileName)) {
            Character key = word.charAt(0);
            if (!occurrences.containsKey(key)) {
                occurrences.put(key, 1);
            } else {
                occurrences.put(key, occurrences.get(key) + 1);
            }
        }

        try {
            FileWriter fWriter = new FileWriter(outputFIleName);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
                bWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            bWriter.close();
        } catch (IOException e) {
            throw new FileProcessorException(e.getMessage());
        }
    }

    int countWords(String inputFileName) throws FileProcessorException {
        return getAllWords(inputFileName).size();
    }

    int countWord(String inputFileName, String key) throws FileProcessorException {
        int count = 0;
        ArrayList<String> words = getAllWords(inputFileName);
        for (String word : words) {
            if (word.equals(key.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    void filterOut(String inputFileName, String outputFileName, String key) throws FileProcessorException {
        ArrayList<String> words = getAllWords(inputFileName);
        try {
            FileWriter fWriter = new FileWriter(outputFileName);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for (String word : words) {
                if (!word.equals(key.toLowerCase()))
                    bWriter.write(word + "\n");
            }

            bWriter.close();
        } catch (IOException e) {
            throw new FileProcessorException(e.getMessage());
        }
    }

    void filter(String inputFileName, String outputFileName, int minWordLength) throws FileProcessorException {
        ArrayList<String> words = getAllWords(inputFileName);
        try {
            FileWriter fWriter = new FileWriter(outputFileName);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for (String word : words) {
                if (word.length() >= minWordLength)
                    bWriter.write(word + "\n");
            }

            bWriter.close();
        } catch (IOException e) {
            throw new FileProcessorException(e.getMessage());
        }
    }
}
