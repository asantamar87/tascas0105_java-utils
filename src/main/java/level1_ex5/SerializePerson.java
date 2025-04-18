package level1_ex5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializePerson {
    public static void main(String[] args) {
        Person person = new Person("Aldo", 35);
        String directoryPath = "resources";
        String filePath = directoryPath + File.separator + "person.ser";

        File parentDir = new File(directoryPath);
        if (!parentDir.exists()) parentDir.mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(person);
            System.out.println("Person object serialized successfully to person.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}