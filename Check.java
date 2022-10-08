import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Check {
    private String prompt;
    private String[] answers;
    private String answerInput;
    private float deduction;
    private float addition;
    private Scanner input = new Scanner(System.in);
    private char checkType;

    public Check() {
        this.prompt = "";
        this.answers = new String[0];
        this.answerInput = "";
        this.deduction = 0;
        this.addition = 0;
        this.checkType = 'n';
    }

    public Check(String prompt, String[] answers, char checkType) {
        this.prompt = prompt;
        this.answers = answers;
        this.addition = 0;
        this.deduction = 0;
        this.answerInput = "";
        this.checkType = checkType;
    }

    public Check(String prompt, String[] answers, float deduction, float addition, char checkType) {
        this.prompt = prompt;
        this.answers = answers;
        this.deduction = deduction;
        this.addition = addition;
        this.answerInput = "";
        this.checkType = checkType;
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

    public String formatAnswers (){
        String formattedAnswers = "(";

        for (int i = 0; i < this.answers.length; i++) {
            formattedAnswers = formattedAnswers + this.answers[i];
            //if we're not on the last answer, add a comma and a space
            if(i != this.answers.length - 1) {
                formattedAnswers = formattedAnswers + ", ";
            }
        }

        formattedAnswers += ")";

        return formattedAnswers;
    }

    public void displayPrompt(){
        System.out.println(this.prompt + " " + this.formatAnswers());
        System.out.println("To display the code, please enter c.");
        answerInput = input.nextLine();
    }

    public void handleAnswer(String strCode){
        if(this.answerInput.equalsIgnoreCase("c")){
            A1Autograder.displayCode(strCode);
        }
    }

    public void setAddition(float addition) {
        this.addition = addition;
    }

    public void setDeduction(float deduction) {
        this.deduction = deduction;
    }

    public float getDeduction() {
        return deduction;
    }

    public float getAddition() {
        return addition;
    }
}
