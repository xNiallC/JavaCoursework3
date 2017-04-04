package uk.co.niallcurtis.JavaCoursework3;

import java.io.IOException;
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
    static void txtDelete(String filename, ArrayList<String> lines) throws IOException {
        try {
            // Make a new temporary file
            Path tempFile = Files.createFile(Paths.get("temp.txt"));

            // Set path and old file
            Path textToReplace = Paths.get(filename);

            // Delete old file
            Files.delete(textToReplace);

            // Write lines to new file
            for(String item: lines) {
                txtWriter(item, "temp.txt");
                txtWriter("\n", "temp.txt");
            }

            // Move temporary file to original file position
            Files.move(tempFile, textToReplace);
        }
        catch (Exception e) {
            // TODO: Better exceptions
            System.out.println(e);
        }
    }
}
