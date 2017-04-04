package uk.co.niallcurtis.JavaCoursework3;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

class TextReader {

    // Read txt files
    private ArrayList txtReader(String filename) throws IOException{
        // Read file name
        FileReader readFile = new FileReader(filename);
        // Use buffered reader for lines
        BufferedReader inFile = new BufferedReader(readFile);
        // Create empty array for lines
        ArrayList<String> lines = new ArrayList<>();

        String currentLine;
        // Line reader tool used in Reading-Writing lectures
        while((currentLine = inFile.readLine()) != null) {
            lines.add(currentLine);
        }

        // Close buffered reader
        inFile.close();

        return lines;
    }
    private String binaryReader(String filename) throws IOException {
        return filename;
    }
    String getExtension(String filename) {
        // Local variable to mutate
        String fileExtension = "";
        // Get . position for substring
        int dot = filename.lastIndexOf(".");
        // Check if dot exists; binary files could be extensionless
        if(dot != -1) {
            // Break string at . and return the extension
            fileExtension += filename.substring(dot);
        }
        return fileExtension;
    }
    // TODO: Read and write in binary after testing for extension
    ArrayList readFile(String filename) throws IOException {
        // To be able to read the files correctly dependent on format, we need to get the file extension
        String fileExtension = getExtension(filename);
        // Check if its txt, so we deal with the file in a text way
        if(fileExtension.equals("txt")) {
            return txtReader(filename);
        }
        else {
            // Should return binary
            return txtReader(filename);
        }
    }
}
