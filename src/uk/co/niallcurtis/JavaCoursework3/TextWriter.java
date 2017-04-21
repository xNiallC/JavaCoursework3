/*
 * Name: Niall Curtis
 * Student number: C1623580
 */

package uk.co.niallcurtis.JavaCoursework3;

import javax.xml.soap.Text;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

class TextWriter {
    // This class uses features from the recent JDK 7 implementation, nio.File
    // Allows very streamlined file manipulation
    static void txtWriter(String input, String filename) throws IOException {
        try {
            // Get file
            Path text = Paths.get(filename);
            // Need to get actual string bytes to write to txt, thanks Java API, took me a while to find this ...
            // Write to file. From javadocs, need to make the open option append as to not overwrite.
            // Append user input
            Files.write(text, input.getBytes(), StandardOpenOption.APPEND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void binaryWriter(String input, String filename) throws IOException {
        // Get bytes of input string
        byte[] binaryInput = input.getBytes();

        // Create new output stream to write
        FileOutputStream writeBinary = new FileOutputStream(filename);
        // Write the bytes
        writeBinary.write(binaryInput, 0, binaryInput.length);
        // Cleanup before closing
        writeBinary.flush();
        writeBinary.close();
    }
    static void txtDelete(String filename, ArrayList<String> lines) throws IOException {
        try {
            String oldFile;
            Path tempFile;
            if(TextReader.getExtension(filename) == "txt") {
                // Make a new temporary file
                oldFile = "temp.txt";
            }
            else {
                oldFile = "temp.dat";
            }
            tempFile = Files.createFile(Paths.get(oldFile));
            // Set path and old file
            Path textToReplace = Paths.get(filename);

            // Delete old file
            Files.delete(textToReplace);

            // Write lines to new file
            for(String item: lines) {
                txtWriter(item, oldFile);
                txtWriter("\n", oldFile);
            }

            // Move temporary file to original file position
            Files.move(tempFile, textToReplace);
        }
        catch (Exception e) {
            // TODO: Better exceptions
            System.out.println(e);
        }
    }
    static void fileWriter(String input, String filename) throws IOException {
        // To be able to read the files correctly dependent on format, we need to get the file extension
        String fileExtension = TextReader.getExtension(filename);
        // Check if its txt, so we deal with the file in a text way
        if(fileExtension.equals("txt")) {
            txtWriter(input, filename);
        }
        else {
            binaryWriter(input, filename);
        }
    }
}
