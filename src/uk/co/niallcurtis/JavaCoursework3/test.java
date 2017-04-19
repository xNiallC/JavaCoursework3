package uk.co.niallcurtis.JavaCoursework3;

public class test {
    public static void main(String[] args) {
        Shortener test = new Shortener("abbreviations.txt");
        System.out.println(test.shortenMessage("see! you tomorrow? hello! see you see you tomorrow one! just kidding! one! two three, may the force be with you"));
    }
}
