package uk.co.niallcurtis.JavaCoursework3;

// TODO: CHECK ALL EXCEPTIONS!

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Get user input for filename, convert it to Path
        System.out.println("Enter File Name:\n");
        String fileInput = in.nextLine();
        Path fileLocation = Paths.get(fileInput);

        String filename = "";

        // If file path exists, convert to string then move on
        if(Files.exists(fileLocation)) {
            filename = filename + fileLocation.toString();
            System.out.println("Using file " + filename);
        }
        // Else we try to create the file to be used, or fail and quit
        else {
            try {
                Path newFile = Files.createFile(fileLocation);
                filename = filename + newFile.toString();
                System.out.println("File not found, creating ... ");
                System.out.println("Created" + filename);

            }
            catch(Exception e) {
                System.out.println("Critical Error, quitting");
                System.out.println(e);
                System.exit(0);
            }
        }

        // Local menu variable
        int menu = 0;
        do {
            System.out.println("\n------ Student Details ------\n");
            System.out.println("1: Read File");
            System.out.println("2: Write to File");
            System.out.println("3: Delete Line\n");

            // Test user input to check its an int
            try {
                menu = in.nextInt();
            }
            catch (Exception e) {
                System.out.println("Please enter only a number.\n");
                in.next();
            }

            // Menu switch cases
            switch(menu) {
                case 1:
                    // Instantiate Text Reader instance
                    TextReader reader = new TextReader();
                    // TODO: Read from binary
                    try {
                        ArrayList<String> lines = reader.readFile(filename);
                        for(String info: lines) {
                            System.out.println(info);
                        }
                    }
                    catch (Exception e){
                        System.out.println("Reading Failure!");
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // TODO: Create 'CreateRecord' function, to iterate through required fields, get user input then parse it before finally adding them all. Will need to be added to one line, seperated by commas
                    // Write to file function
                    // TODO: Write to binary
                    // TODO: Regex input

                    System.out.println("Enter Text:");

                    in.nextLine();
                    String userInput = in.nextLine();

                    // Try to write text to file
                    try {
                        TextReader rdr = new TextReader();
                        // We check if the file is empty. If it is, we don't write a new line
                        if(rdr.readFile(filename).size() != 0) {
                            TextWriter.txtWriter("\n", filename);
                        }
                        TextWriter.txtWriter(userInput, filename);
                    }
                    catch (Exception e) {
                        System.out.println("Write Failed!");
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    // Local variables
                    int lineNumber;
                    ArrayList<String> lines;

                    System.out.println("\nEnter Line Number:");
                    // Read in line number
                    try {
                        lineNumber = in.nextInt();
                    }
                    catch (Exception e) {
                        System.out.println("Please enter only a number.\n");
                        in.nextLine();
                        break;
                    }

                    // Get list currently in the file
                    TextReader rdr = new TextReader();
                    try {
                        lines = rdr.readFile(filename);
                    }
                    catch (Exception e){
                        System.out.println("Reading Failure!");
                        e.printStackTrace();
                        break;
                    }

                    // Attempt to delete item from list, if it's there
                    try {
                        System.out.println("Deleting Line: " + lines.get(lineNumber));
                        lines.remove(lineNumber);
                    }
                    catch (Exception e) {
                        System.out.println("Line not in file!");
                        break;
                    }

                    // Run delete function
                    try {
                        TextWriter.txtDelete(filename, lines);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
            }
        } while(menu != 9);
    }
}
