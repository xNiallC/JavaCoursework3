/*
 * Name: Niall Curtis
 * Student number: C1623580
 */

package uk.co.niallcurtis.JavaCoursework3;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.StringBuilder;

public class Shortener {
    // This class is only a starting point. You should complete all members
    // below, but you may also need to add other fields and methods to
    // finish the implementation as per the question on the assignment sheet.
    private File filename;
    /*
     * Default constructor that will load a default abbreviations text file.
     */
    public Shortener() {
        this.filename = new File("abbreviations.txt");
    }
    
    /*
     * Constructor that will load the abbreviations file represented by the
     * File parameter.
     */
    public Shortener( File inAbbreviationsFile ) {
        this.filename = inAbbreviationsFile;
    }
    
    /*
     * Constructor that will load the abbreviations file that the String 
     * parameter is a file path for.
     */
    public Shortener( String inAbbreviationsFilePath ) {
        this.filename = new File(inAbbreviationsFilePath);
    }

    // Methods to change the abbreviations file
    public void changeFile(String path){
        this.filename = new File(path);
    }
    public void changeFile(File path){
        this.filename = path;
    }

    public String shortenWord( String inWord ) {
        inWord = inWord.trim();
        // We get String path and reuse method from last question. OO!
        String pathTo = filename.getPath();
        String lastLetter = "";
        try {
            ArrayList<String> shorts = TextReader.readFile(pathTo);
            // We now have an array of strings with each object being a String with the word and its short

            // We are also going to make an array of our punctuation
            // To make a one line list, we must use 'Arrays.asList' as stated in the java docs
            List<String> punc = new ArrayList<>(Arrays.asList(",", "?", ".", "!", ";"));
            // We get the last character of the inWord to check its punctuation
            String endPunc = inWord.substring(inWord.length() - 1);
            if(punc.contains(endPunc)) {
                // If we look at substring java docs, we can make a substring from characters' index in a string
                // Now we have the word without punctuation
                inWord = inWord.substring(0, inWord.length() - 1);
                // Return our punctuation
                lastLetter += endPunc;
            }
            for(String word : shorts) {
                // Same split we use in StudentRecord
                String[] splitWord = word.split(",");
                // If the word matches the first word of the abbreviations line, we make a string of the abbreviation + the punctuation and return it
                if(splitWord[0].toLowerCase().matches(inWord.toLowerCase())) {
                    String finalWord = splitWord[1] + lastLetter;
                    return finalWord;
                }
            }
            // Else return the original word
            String finalWord = inWord + lastLetter;
            return finalWord;
        }
        catch(Exception e) {
            System.out.println("Reading Failure!");
            e.printStackTrace();
            return(filename.getPath() + " could not be read.");
        }
    }
    public String shortenPhrase( String inPhrase) {
        inPhrase = inPhrase.trim();

        String pathTo = filename.getPath();
        String lastLetter = "";

        List<String> punc = new ArrayList<>(Arrays.asList(",", "?", ".", "!", ";"));
        // We get the last character of the inWord to check its punctuation
        String endPunc = inPhrase.substring(inPhrase.length() - 1);
        if(punc.contains(endPunc)) {
            // If we look at substring java docs, we can make a substring from characters' index in a string
            // Now we have the word without punctuation
            inPhrase = inPhrase.substring(0, inPhrase.length() - 1);
            lastLetter += endPunc;
        }
        try {
            ArrayList<String> shorts = TextReader.readFile(pathTo);
            for(String word : shorts) {
                // Same split we use in StudentRecord
                String[] splitWord = word.split(",");
                // If the word matches the first word of the abbreviations line, we make a string of the abbreviation + the punctuation and return it
                if(splitWord[0].toLowerCase().equals(inPhrase.toLowerCase())) {
                    String finalWord = splitWord[1] + lastLetter;
                    return finalWord;
                }
            }
            // Else return the original word
            String finalWord = inPhrase + lastLetter;
            return finalWord;
        }
        catch(Exception e) {
            System.out.println("Reading Failure!");
            e.printStackTrace();
            return(filename.getPath() + " could not be read.");
        }
    }

    /*

    shortenMessage was designed using a flow chart with pen and paper, but I will try to explain it here.
    The method follows a cyclic order of operation that allows it to efficiently deal with phrases, individual words
    and non matches efficiently with one consistent process. This is kind of the process, starting with a copy of the inMessage

    --- Start ---
    Is the message length > 0?
    No --> End and return final abbreviated message
    Yes --> Move forward to match

    Does the current entire message match any abbreviation?
    No --> Remove first word (before first whitespace) and return to the start again
    Yes --> Remove the matched word/phrase from END of message, add abbreviation/word to final message, return to start

    It essentially starts with a long sentence, reducing by one word from the left each cycle until it gets a match.
    The match will ALWAYS be the right most phrase/word, so we can safely remove that and restart the process

     */
    public String shortenMessage(String inMessage) {
        // To make things easier, we simply return single word entries straight away to avoid complication
        // We check if any characters non-space are split by a space. If not, we just return shortenWord
        if(inMessage.split(" ").length == 1) {
            return shortenWord(inMessage);
        }

        // Final message to be changed
        String finalMessage = "";
        String tempMessage = inMessage;
        // Whenever the finalMessage is added to, we no longer need the word that was added. So we remove it
        // Is the message length > 0?
        while(tempMessage.length() > 0) {
            tempMessage = tempMessage.trim();
            // Is it a phrase or a single word?
            if(tempMessage.contains(" ")) {
                String phraseMatch = shortenPhrase(tempMessage);
                // If our input message still equals what we tried to shorten, then it didn't work. So we remove the first word from the string
                if(tempMessage.equals(phraseMatch)) {
                    // We now remove the first word from the input message, and try again
                    int firstWhitespace = tempMessage.indexOf(" ");
                    tempMessage = new StringBuilder(tempMessage).replace(0, firstWhitespace + 1, "").toString();
                }
                // Else, we have a match, so we add the abbreviation to final result and remove our match from the end
                else {
                    finalMessage = phraseMatch + " " + finalMessage;
                    int indexOfLastMatch = inMessage.lastIndexOf(tempMessage);
                    // We check for trailing whitespace to ensure we deal with it correctly
                    tempMessage = new StringBuilder(inMessage).replace(indexOfLastMatch, inMessage.length(), "").toString();
                    inMessage = tempMessage;
                }
            }
            else {
                // Add to our final message and reset our inMessage to find the next abbreviation
                finalMessage = shortenWord(tempMessage) + " " + finalMessage;
                // Check for whitespace. \\s means whitespace, ++ means trailing and $ means end of string
                tempMessage = tempMessage.replaceFirst("\\s++$", "");
                // Get last version of our abbreviated message
                int matchIndex = inMessage.lastIndexOf(tempMessage);
                // Now we reset temp message by removing what we added to final message
                if(tempMessage.split(" ").length == 1) {
                    tempMessage = new StringBuilder(inMessage).replace(matchIndex, inMessage.length(), "").toString();
                }
                else {
                    tempMessage = new StringBuilder(inMessage).replace(matchIndex - 1, inMessage.length(), "").toString();
                }
                inMessage = tempMessage;
            }
        }
        return finalMessage;
    }
}