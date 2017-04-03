package uk.co.niallcurtis.JavaCoursework3;

// TODO: CHECK ALL EXCEPTIONS!

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by niall on 03/04/2017.
 */
public class QuestionOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // System.out.println("Enter File");
        // filename = in.nextLine();

        String filename = "/Users/niall/Documents/NiallUni/Java/Coursework3/out/production/Coursework3/uk/co/niallcurtis/JavaCoursework3/test.txt";

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
                in.nextLine();
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
                    int writeMenu = 0;

                    System.out.println("\n------ Write to File ------");
                    System.out.println("\n1: Write to .txt");
                    // TODO: Write to binary
                    // TODO: Regex input

                    // Read txt
                    try {
                        writeMenu = in.nextInt();
                    }
                    catch (Exception e) {
                        System.out.println("Please enter only a number.\n");
                        in.nextLine();
                    }

                    switch(writeMenu) {
                        case 1:
                            System.out.println("Enter Text:");

                            in.nextLine();
                            String userInput = in.nextLine();

                            // Try to write text to file
                            try {
                                TextWriter.txtWriter(userInput, filename);
                            }
                            catch (Exception e) {
                                System.out.println("Write Failed!");
                                e.printStackTrace();
                            }
                            break;
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
                        TextWriter.txtDelete(lineNumber, filename, lines);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
            }
        } while(menu != 9);
    }
}
