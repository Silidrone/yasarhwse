/*
Student Number: 21070006208
Name Surname: Muhamed Cicak
 */

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    // I used this code to generate the output csv file for the tests
    public static void main(String[] args) {
        File file = new File("t4output.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            GrayCode grayCode = new GrayCode();
            String[] s = grayCode.grayCodeSequence(10);
            writer.writeNext(s);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}