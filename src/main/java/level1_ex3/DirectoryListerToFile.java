package level1_ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;


public class DirectoryListerToFile {

    private static void listAndSaveDirectoryContents(String path, String filename, int level, StringBuilder output) {
        try (FileWriter writer = new FileWriter(filename)) {

            File directory = new File(path);

            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null && files.length > 0) {
                    Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));

                    for (File file : files) {
                        String type = file.isDirectory() ? "[DIR]" : "[FILE]";
                        String indent = "  ".repeat(level);
                        output.append(String.format("%s%s %s%n", indent, type, file.getName()));

                        if (file.isDirectory()) {
                            listAndSaveDirectoryContents(file.getAbsolutePath(), filename, level + 1, output);
                        }
                    }
                } else {
                    output.append("The " + directory.getName() + " directory is empty.\n");
                }
            } else {
                output.append("The directory does not exist or is not a valid directory.\n");
            }
            writer.write(output.toString());
            System.out.println("Directory contents saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Error: You do not have sufficient permissions to access the directory.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }





    public static void main(String[] args) {
        if (args.length > 0) {
            StringBuilder output = new StringBuilder();
            listAndSaveDirectoryContents(args[0], "directory_list.txt", 0, output);
        } else {
            System.out.println("Please provide the directory path as a parameter.");
        }
    }
}
