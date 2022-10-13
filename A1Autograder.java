import java.io.*;
import java.util.Scanner;

public class A1Autograder {
    private Scanner input;
    private static String fileName;
    private static double marksAchieved;
    private final int totalDenominator = 50;
    private File code;

    public A1Autograder (){
        this.input = new Scanner(System.in);
    }

    public void runGrader(){
        String strCode;
        Check newCheck;
        String yesNo [] = {"y", "n"};

        //late submission check
        newCheck = new Check("Has the student submitted late?", yesNo, -2.5, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //file name check
        System.out.println("Please open the student's file and ensure that it is named in the following fashion:\nLastNameFirstNameA1.c");
        newCheck = new Check("Is the file name incorrect?", yesNo, -2.5, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();

        System.out.println("Please enter the file name:");
        this.fileName = this.input.nextLine();

        //gcc check
        System.out.println("\nOutput of gcc compilation:\n");
        if(!(Check.CompileCprog(fileName))){
            //gcc error check
            newCheck = new Check("Are there any gcc errors?", yesNo, 'i');
            this.handleInstantZeroCheck(newCheck);

            //gcc warning check
            newCheck = new Check("Enter the number of unique gcc warnings there are:", 'a');
            newCheck.displayPrompt();
            newCheck.implementMarks();
        }

        System.out.println("Here is the student's code");
        System.out.println("***************");
        strCode = this.readCode();
        displayCode(strCode);

        //global variable check
        newCheck = new Check("Does the program appear to use global variables?", yesNo, 'i');
        this.handleInstantZeroCheck(newCheck);

        //goto statement check
        newCheck = new Check("Goes the program appear to use goto statements?", yesNo, 'i');
        this.handleInstantZeroCheck(newCheck);

        //header comment check
        newCheck = new Check("Please assign a grade out of 3 for the student's header comment.", 'n', "0-3");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //variable naming check
        newCheck = new Check("Please assign a grade out of 2 for the student's variable naming.", 'n', "0-2");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //commenting check
        newCheck = new Check("Please assign a grade out of 2 for the student's commenting.", 'n', "0-2");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //consistent spacing/readability check
        newCheck = new Check("Please assign a grade out of 2 for the student's spacing/readability.", 'n', "0-2");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        System.out.println("It's time to run the program. In a new terminal, run the student's program. There should already be an 'a.out' file waiting for you. Use the test cases provided.\n" +
                "After running the test cases, come back here and continue with the remaining checks.");

        //loop check
        newCheck = new Check("Please assign a grade out of 6 for the student's use of loops.", 'n', "0-6");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //if statement check
        newCheck = new Check("Please assign a grade out of 6 for the student's use of if statements.", 'n', "0-6");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //input check 1
        newCheck = new Check("Were you able to successfully input all the required grades?", yesNo, 2, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //input check 2
        newCheck = new Check("Were you able to enter a 'y' or 'n' to continue to the next student?", yesNo, 2, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //output check 1
        newCheck = new Check("Did the program include the correct welcome message?", yesNo, 2, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //output check 2
        newCheck = new Check("Did the program include the correct prompts for student grades?", yesNo, 2, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //output check 3
        newCheck = new Check("If you entered 'y' for the continue to the next student, is the same prompts repeated for the next student?", yesNo, 1, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //output check 4
        newCheck = new Check("If you entered 'n' for the continue to the next student, is the class average displayed?", yesNo, 1, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //expected output check
        newCheck = new Check("Please assign a grade out of 8 for how well the student's output numbers matched the expected output for the test cases.", 'n', "0-8");
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //program check 1
        newCheck = new Check("Check 1\nWere assignment grades replaced by 0 if the MOSS output was 0?", yesNo, 3, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //program check 2
        newCheck = new Check("Check 2\nIf all MOSS outputs were 0, was the student assigned a 0 as the final mark?", yesNo, 3, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //program check 3
        newCheck = new Check("Check 3\nIf the survey response was 'y', was the lab exam weight transferred correctly?", yesNo, 3, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();
        //program check 4
        newCheck = new Check("Check 4\nDid the student get an F if the student got less than 25/50 ib the exams+quizzes total?", yesNo, 3, 'd');
        newCheck.displayPrompt();
        newCheck.implementMarks();

        //program end
        if(getMarksAchieved() < 0){
            //if the mark total is less than 0, make it 0
            setMarksAchieved(0);
        }
        displayGrade();
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
    }

    public void displayGrade(){
        System.out.println("\n\n\u001b[34mThis student's final grade is " + marksAchieved + "/" + this.totalDenominator + "\u001b[0m");
    }

    public void handleInstantZeroCheck(Check newCheck){
        newCheck.displayPrompt();
        if(!newCheck.implementMarks()){
            //end program if implementMarks returns false
            this.displayGrade();
            System.exit(0);
        }
    }

    public static double getMarksAchieved() {
        return marksAchieved;
    }

    public static void setMarksAchieved(double marks) {
        marksAchieved = marks;
    }
}
