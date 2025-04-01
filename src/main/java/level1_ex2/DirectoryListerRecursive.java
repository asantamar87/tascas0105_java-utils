package level1_ex2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryListerRecursive {


    private static void recursiveListDirectoryContents(String path) {

        try {
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String type = file.isDirectory() ? "📁 DIR -> " : "📄 FILE -> ";
                        System.out.println(type + file.getName());
                        if (file.isDirectory()) {
                            recursiveListDirectoryContents(file.getAbsolutePath());
                        }
                    }
                }
            } else {
                System.out.println("Directory does not exist or is not a directory.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            recursiveListDirectoryContents(args[0]);
        }else{
            System.out.println("Please pass the directory path as a parameter.");
        }

    }
}
