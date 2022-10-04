import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class A1Autograder {
    private Scanner input;
    private String answer;
    private String fileName;

    public A1Autograder (){
        this.input = new Scanner(System.in);
    }

    public void runGrader(){
        System.out.println("Please open the student's file and ensure that it is named in the following fashion:\nLastNameFirstNameA1.c");
        System.out.println("Please enter the file name:");
        this.fileName = this.input.nextLine();

        System.out.println("\nOutput of gcc compilation:\n");
        CompileCprog(fileName);
    }

    public static void CompileCprog(String filename){
        File dir = new File(".");
        try {
            String exeName = filename.substring(0, filename.length() - 2);
            Process p = Runtime.getRuntime().exec("gcc -std=c99 -Wall " + filename, null, dir);
//            p = Runtime.getRuntime().exec("./a.out", null, dir);
            // Process p = Runtime.getRuntime().exec("ls", null, dir);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                // System.out.println("makes it to the compiler");
                System.out.println("\u001B[31m" + line + "\u001B[0m");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
