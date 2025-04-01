package level1_ex5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializePerson {
    public static void main(String[] args) {

        String directoryPath = "resources";
        String filePath = directoryPath + File.separator + "person.ser";

        File parentDir = new File(directoryPath);
        if(!parentDir.exists() && !parentDir.isDirectory()){
            System.err.println("Failed to create directory: " + directoryPath);
            return;
        }

        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Person person = (Person) in.readObject();
            System.out.println("Person object deserialized: " + person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}