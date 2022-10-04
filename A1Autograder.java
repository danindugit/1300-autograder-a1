import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class A1Autograder {
    private Scanner input;
    private String answer;
    private static String fileName;
    private float marksAchieved;
    private final int totalDenominator = 50;

    public A1Autograder (){
        this.input = new Scanner(System.in);
    }

    public void runGrader(){
        System.out.println("Please open the student's file and ensure that it is named in the following fashion:\nLastNameFirstNameA1.c");
        System.out.println("Please enter the file name:");
        this.fileName = this.input.nextLine();

        System.out.println("\nOutput of gcc compilation:\n");
        Check.CompileCprog(fileName);
    }

    public static String getFileName() {
        return fileName;
    }
}
