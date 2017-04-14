package uk.co.niallcurtis.JavaCoursework3;

import java.util.ArrayList;
import java.util.Scanner;

class StudentRecord {
    // A few fields used the same regex, so I made it into a function to avoid redundancy
    private static String createNameField(String userHint, Scanner in) {
        boolean subGood = false;
        do {
            System.out.println(userHint);
            String userInput = in.nextLine();
            if(Regex.nameCheck(userInput)) {
                return (userInput + ",");
            }
            else {
                System.out.println("Only letters allowed, at least one character.");
            }
        } while(!subGood);
        return null;
    }

    static void createStudent(String filename) {
        // Fields:
        // name, student number, coursename, courseID, house number, street name, town, postcode

        String studentRecord = "";

        boolean good = false;

        Scanner in = new Scanner(System.in);

        // Regex name
        studentRecord += createNameField("Enter Name:", in);

        // Regex student number
        do {
            System.out.println("Enter Student number:");
            String userInput = in.nextLine();
            if(Regex.numberCheck(userInput)) {
                studentRecord += (userInput + ",");
                good = true;
            }
            else {
                System.out.println("Number must be 'C' followed by six digits.");
            }
        } while(!good); good = false;

        // Regex course name
        studentRecord += createNameField("Enter Course Name:", in);

        // Regex course ID
        do {
            System.out.println("Enter Course ID:");
            String userInput = in.nextLine();
            if(Regex.courseNumberCheck(userInput)) {
                studentRecord += (userInput + ",");
                good = true;
            }
            else {
                System.out.println("ID must be two Upper case characters followed by six digits.");
            }
        } while(!good); good = false;

        // Regex House Number
        do {
            System.out.println("Enter House Number:");
            String userInput = in.nextLine();
            if(Regex.houseNumberCheck(userInput)) {
                studentRecord += (userInput + ",");
                good = true;
            }
            else {
                System.out.println("At least one digit followed by at most one letter.");
            }
        } while(!good); good = false;

        // Regex street name
        studentRecord += createNameField("Enter Street Name:", in);

        // Regex town
        studentRecord += createNameField("Enter Town:", in);

        // Regex postcode
        do {
            System.out.println("Enter Postcode:");
            String userInput = in.nextLine();
            if(Regex.postcodeCheck(userInput)) {
                studentRecord += (userInput);
                good = true;
            }
            else {
                System.out.println("Two upper case letters, then a digit, then two upper case letters.");
            }
        } while(!good);

        // Try to write text to file
        try {
            // We check if the file is empty. If it is, we don't write a new line
            if(TextReader.readFile(filename).size() != 0) {
                TextWriter.txtWriter("\n", filename);
            }
            TextWriter.txtWriter(studentRecord, filename);
        }
        catch (Exception e) {
            System.out.println("Write Failed!");
            e.printStackTrace();
        }
    }
    static void deleteStudent(String filename, int lineNumber, ArrayList lines) {
        // Attempt to delete item from list, if it's there
        try {
            System.out.println("Deleting Line: " + lines.get(lineNumber));
            lines.remove(lineNumber);
            // Run delete function
            try {
                TextWriter.txtDelete(filename, lines);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        catch (Exception e) {
            System.out.println("Line not in file!");
        }
    }
    static ArrayList searchCourse(String filename) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Course Search Term:");
        // Get our search term
        String search = in.nextLine();
        // Make an array for results
        ArrayList<String> searchResults = new ArrayList<>();
        try {
            // Make an array from all current records
            ArrayList<String> allRecords = TextReader.readFile(filename);
            // If the search matches a term in the record
            for(String record: allRecords) {
                // Split record at , and make array
                String[] row = record.split(",");
                // We get the course string, make it lower case, and compare it to the search in lower case
                if(row[2].toLowerCase().contains(search.toLowerCase())) {
                    searchResults.add(record);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Read Failed!");
        }
        return searchResults;
    }
    static ArrayList searchAddress(String filename) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Address Search Term:");
        // Get our search term
        String search = in.nextLine();
        // Make an array for results
        ArrayList<String> searchResults = new ArrayList<>();
        try {
            // Make an array from all current records
            ArrayList<String> allRecords = TextReader.readFile(filename);
            // If the search matches a term in the record
            for(String record: allRecords) {
                // Split record at , and make array
                String[] row = record.split(",");
                // Make strings of the row to search with
                String houseNumber = row[4].toLowerCase();
                String streetName = row[5].toLowerCase();
                String town = row[6].toLowerCase();
                String postcode = row[7].toLowerCase();
                // We get the course string, make it lower case, and compare it to the search in lower case
                if(houseNumber.contains(search.toLowerCase()) || streetName.contains(search.toLowerCase()) || town.contains(search.toLowerCase()) || postcode.contains(search.toLowerCase())) {
                    searchResults.add(record);
                }
            }
        }
        catch(Exception e) {
            System.out.println("Read Failed!");
        }
        return searchResults;
    }
    static ArrayList searchSubset(String filename, int startNum, int endNum) {
        int count = 0;
        Scanner in = new Scanner(System.in);
        // Get our search term
        // Make an array for results
        ArrayList<String> searchResults = new ArrayList<>();
        try {
            // Make an array from all current records
            ArrayList<String> allRecords = TextReader.readFile(filename);
            ArrayList<String> finalRecords = new ArrayList<>();
            // If the search matches a term in the record
            for(String record: allRecords) {
                // Split record at , and make array
                String[] row = record.split(",");
                // Create empty final record string
                String finalRecord = "";
                for(String value: row) {
                    // We use a count to check our substring
                    // If the count is between our upper and lower values, that's a value we want
                    // so we add it to the string. We then return the string
                    if((count >= startNum) && (count <= endNum)) {
                        if(count == endNum) {
                            finalRecord += value;
                        }
                        else {
                            finalRecord += (value + ",");
                        }
                    }
                    // Add 1 to the count
                    count++;
                }
                finalRecords.add(finalRecord);
                count = 0;
            }
            return finalRecords;
        }
        catch(Exception e) {
            System.out.println("Read Failed!");
        }
        return searchResults;
    }
}
