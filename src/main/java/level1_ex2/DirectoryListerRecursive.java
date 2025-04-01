package level1_ex2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class DirectoryListerRecursive {


    private static void recursiveListDirectoryContents(String path) {

        try {
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
                    for (File file : files) {
                        String type = file.isDirectory() ? "📁 DIR -> " : "📄 FILE -> ";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String lastModified = dateFormat.format(new Date(file.lastModified()));
                        System.out.println(type + file.getName() + " (Last modified: " + lastModified + ")");
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
