package uk.co.niallcurtis.JavaCoursework3;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by niall on 03/04/2017.
 */

public class TextReader {
    private ArrayList txtReader(String filename) throws IOException{
        FileReader readFile = new FileReader(filename);
        BufferedReader inFile = new BufferedReader(readFile);

        ArrayList lines = new ArrayList();

        String currentLine;
        // Line reader tool used in Reading-Writing lectures
        while((currentLine = inFile.readLine()) != null) {
            lines.add(currentLine);
        }

        inFile.close();

        return lines;
    }
    private String binaryReader(String filename) throws IOException {
        return filename;
    }
    public ArrayList readFile(String filename) throws IOException {
        return txtReader(filename);
    }
}
