package level1_ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirectoryListerReadFile {

    private static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            readFile(args[0]);
        } else {
            System.out.println("Please provide the file name as a parameter.");
        }


    }
}
