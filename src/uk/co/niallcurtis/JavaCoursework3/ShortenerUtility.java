package uk.co.niallcurtis.JavaCoursework3;

/*
 * Name: Niall Curtis
 * Student number: C1623580
 */

/*
 * A command-line application that shortens a message.
 */
public class ShortenerUtility {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Please Enter Only 1 Argument.");
        }
        else {
            Shortener msgshort = new Shortener("abbreviations.txt");
            System.out.println(msgshort.shortenMessage(args[0]));
        }
    }
}