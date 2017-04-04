package uk.co.niallcurtis.JavaCoursework3;

// TODO: CHECK ALL EXCEPTIONS!

import java.lang.reflect.Array;
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
            System.out.println("3: Delete Line");
            System.out.println("4: Search Coursename");
            System.out.println("5: Search Address");
            System.out.println("6: Search Subset\n");

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
                    // TODO: Read from binary
                    try {
                        ArrayList<String> lines = TextReader.readFile(filename);
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

                    // Run our method to create a student
                    StudentRecord.createStudent(filename);
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
                    try {
                        lines = TextReader.readFile(filename);
                    }
                    catch (Exception e){
                        System.out.println("Reading Failure!");
                        e.printStackTrace();
                        break;
                    }

                    // Run delete method
                    StudentRecord.deleteStudent(filename, lineNumber, lines);
                    break;
                // For all search cases, we simply just run their respective method and print the results, or no results if none are found.
                case 4:
                    ArrayList<String> records = StudentRecord.searchCourse(filename);
                    for(String record: records) {
                        System.out.println(record);
                    }
                    if(records.size() == 0) {
                        System.out.println("No results found.");
                    }
                    break;
                case 5:
                    ArrayList<String> records2 = StudentRecord.searchAddress(filename);
                    for(String record: records2) {
                        System.out.println(record);
                    }
                    if(records2.size() == 0) {
                        System.out.println("No results found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter Lower Limit of Subset:");
                    int lowerBound;
                    int upperBound;
                    // Read in line number
                    try {
                        lowerBound = in.nextInt();
                    }
                    catch (Exception e) {
                        System.out.println("Please enter only a number.\n");
                        in.nextLine();
                        break;
                    }
                    System.out.println("Enter Upper Limit of Subset:");
                    try {
                        upperBound = in.nextInt();
                    }
                    catch (Exception e) {
                        System.out.println("Please enter only a number.\n");
                        in.nextLine();
                        break;
                    }
                    ArrayList<String> records3 = StudentRecord.searchSubset(filename, lowerBound, upperBound);
                    for(String record: records3) {
                        System.out.println(record);
                    }
                    if(records3.size() == 0) {
                        System.out.println("No results found.");
                    }
                    break;
            }
        } while(menu != 9);
    }
}
