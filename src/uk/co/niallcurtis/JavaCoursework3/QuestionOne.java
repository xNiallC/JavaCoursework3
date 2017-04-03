package uk.co.niallcurtis.JavaCoursework3;

import java.util.ArrayList;
/**
 * Created by niall on 03/04/2017.
 */
public class QuestionOne {
    public static void main(String[] args) {
        // Instantiate Text Reader instance
        TextReader test = new TextReader();
        try {
            ArrayList newData = test.readFile("/Users/niall/Documents/NiallUni/Java/Coursework3/out/production/Coursework3/uk/co/niallcurtis/JavaCoursework3/test.txt");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
