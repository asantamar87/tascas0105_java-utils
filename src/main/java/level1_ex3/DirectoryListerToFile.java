package level1_ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;


public class DirectoryListerToFile {

    private static void listDirectoryContents(String path, int depth, StringBuilder output) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
                for (File file : files) {
                    String type = file.isDirectory() ? "[DIR]" : "[FILE]";
                    String indent = "  ".repeat(depth);
                    output.append(String.format("%s%s %s%n", indent, type, file.getName()));

                    if (file.isDirectory()) {
                        listDirectoryContents(file.getAbsolutePath(), depth + 1, output);

                    }
                }
            } else {
                output.append("The " + directory.getName() + " directory is empty.\n");
            }
        } else {
            output.append("The directory does not exist or is not a valid directory.\n");
        }

    }

    private static void saveToFile(String filename, StringBuilder output) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append(output);
            System.out.println("Directory contents saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Error: You do not have sufficient permissions to access the directory.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void listAndSaveDirectoryContents(String path, String filename) {
        StringBuilder output = new StringBuilder();
        listDirectoryContents(path, 0, output);
        saveToFile(filename, output);
    }


    public static void main(String[] args) {
        if (args.length == 2) {
            String path = args[0];
            String filename = args[1];
            listAndSaveDirectoryContents(path, filename);
        } else {
            System.out.println("Usage: java DirectoryHandler <directory_path> <output_filename>");
        }
    }
}
