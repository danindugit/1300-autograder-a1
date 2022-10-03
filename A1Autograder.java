import java.util.Scanner;

public class A1Autograder {
    private Scanner input;
    private String answer;

    public A1Autograder (){
        this.input = new Scanner(System.in);
    }

    public void runGrader(){
        System.out.println("Please open the student's file and ensure that it is named in the following fashion:\nLastNameFirstNameA1.c");
    }


}
