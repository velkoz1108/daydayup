package com.example.operatelogspringbootstarter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

public class TestFile {

    // Path of a file
    static String FILEPATH = "/Users/dasouche/logs/";


    // Method 1
    // To write the bytes into a file
    static void writeByte(byte[] bytes)
    {

        // Try block to check for exceptions
        try {
            String filename = System.currentTimeMillis() + String.valueOf(new Random().nextInt(1000));
            System.out.println("filename:"+filename);
            File file = new File(FILEPATH+filename);

            // Initialize a pointer in file
            // using OutputStream
            OutputStream os = new FileOutputStream(file);

            // Starting writing the bytes in it
            os.write(bytes);

            // Display message onconsole for successful
            // execution
            System.out.println("Successfully"
                    + " byte inserted");

            // Close the file connections
            os.close();
        }

        // Catch block to handle the exceptions
        catch (Exception e) {

            // Display exception on console
            System.out.println("Exception: " + e);
        }
    }

    // Method 2
    // Main driver method
    public static void main(String args[]) throws IOException {

//        // Input string
//        String string = "GeeksForGeeks"
//                + " - A Computer Science"
//                + " Portal for geeks";
//
//        // Getting byte array from string
//        // using getBytes() method
//        byte[] bytes = string.getBytes();

        String filePath = "/Users/dasouche/Documents/照片/dian.png";

        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());

        // Calling Method 1 to
        // convert byte array to file
        writeByte(bytes);
    }

}
