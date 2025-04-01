package level1_ex1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryLister {

    private static void listDirectoryContents(String path) {

        try{
            File directory = new File(path);
            if(directory.exists() && directory.isDirectory()){
                File[] files = directory.listFiles();
                if(files != null) {
                    Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
                    for (File file : files) {
                        String type = file.isDirectory() ? "📁 DIR " : "📄 FILE";
                        System.out.println(type+" " + file.getName());
                    }
                }
            } else{
                System.out.println("Directory does not exist or is not a directory.");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        if (args.length > 0) {
            listDirectoryContents(args[0]);
        }else{
            System.out.println("Please pass the directory path as a parameter.");
        }

    }
}
