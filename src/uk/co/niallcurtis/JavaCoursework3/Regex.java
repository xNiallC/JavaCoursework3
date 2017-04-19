/*
 * Name: Niall Curtis
 * Student number: C1623580
 */

package uk.co.niallcurtis.JavaCoursework3;

// Regex expressions adapted from tutorial/API here:
// https://docs.oracle.com/javase/tutorial/essential/regex/

class Regex {
    static boolean nameCheck(String input) {
        // Check for at least one non-space character, but allow spaces otherwise
        return input.matches("[a-zA-Z][a-zA-Z ]*");
    }
    static boolean numberCheck(String input) {
        // Check for C followed by 6 digits
        return input.matches("^[C][0-9]{6}");
    }
    static boolean courseNumberCheck(String input) {
        // Check for 2 upper case letters, then 4 digits
        return input.matches("^[A-Z]{2}[0-9]{4}");
    }
    static boolean houseNumberCheck(String input) {
        // Check for at least 1 digit followed by at most 1 character
        return input.matches("^[0-9]+[a-zA-Z]?");
    }
    static boolean postcodeCheck(String input) {
        // Check for 2 upper case, 1 digit, 2 lower
        return input.matches("^[A-Z]{2}[0-9][A-Z]{2}");
    }
}
