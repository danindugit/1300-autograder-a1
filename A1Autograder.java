import java.io.*;
import java.util.Scanner;

public class A1Autograder {
    private Scanner input;
    private String answer;
    private static String fileName;
    private float marksAchieved;
    private final int totalDenominator = 50;
    private File code;

    public A1Autograder (){
        this.input = new Scanner(System.in);
    }

    public void runGrader(){
        String strCode;
        Check newCheck;
        String yesNo [] = {"y", "n"};

        System.out.println("Please open the student's file and ensure that it is named in the following fashion:\nLastNameFirstNameA1.c");
        System.out.println("Please enter the file name:");
        this.fileName = this.input.nextLine();

        System.out.println("\nOutput of gcc compilation:\n");
        if(!(Check.CompileCprog(fileName))){
            //there are gcc errors/warnings
            newCheck = new Check("Are there any gcc errors?", yesNo, 'i');
            newCheck.displayPrompt();
            if(newCheck.getAnswerInput().equalsIgnoreCase("y")){
                this.setMarksAchieved(0);
                this.displayGrade();
                return;
            }
        }

        strCode = this.readCode();
        displayCode(strCode);
    }

    public String readCode() {
        String strCode = "";
        this.code = new File(fileName);
        Scanner reader = null;
        try {
            reader = new Scanner(this.code);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
            System.exit(0);
        }
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            strCode += data + "\n";
        }
        reader.close();

        return strCode;
    }

    public static void displayCode(String strCode){
        System.out.println("\u001b[35m" + strCode + "\u001b[0m");
        System.exit(0);
    }

    public void displayGrade(){
        System.out.println("\n\n\u001b[34mThis student's final grade is " + marksAchieved + "/" + this.totalDenominator + "\u001b[0m");
    }

    public static String getFileName() {
        return fileName;
    }

    public float getMarksAchieved() {
        return marksAchieved;
    }

    public void setMarksAchieved(float marksAchieved) {
        this.marksAchieved = marksAchieved;
    }
}
