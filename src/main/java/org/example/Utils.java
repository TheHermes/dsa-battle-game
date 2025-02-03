package org.example;

import java.io.*;
import java.util.Random;

public class Utils {
    // Returns a random integer between min and max, used for determining damage range
    public static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(min, max);
    }

    public static Object loadGame(String fileName) {
        Object returnObject = null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            returnObject = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return  returnObject;
    }

    public static void saveGame(Object object, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);

            objectStream.writeObject(object);
            objectStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
