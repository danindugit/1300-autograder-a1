import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Check {
    private String prompt;
    private String[] answers;
    private float deduction;
    private float addition;

    public Check(String prompt, String[] answers) {
        this.prompt = prompt;
        this.answers = answers;
        this.addition = 0;
        this.deduction = 0;
    }

    public Check(String prompt, String[] answers, float deduction, float addition) {
        this.prompt = prompt;
        this.answers = answers;
        this.deduction = deduction;
        this.addition = addition;
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

    public String formatAnswers (String[] answers){
        String formattedAnswers = "(";

        for (int i = 0; i < answers.length; i++) {
            formattedAnswers = formattedAnswers + answers[i];
            //if we're not on the last answer, add a comma and a space
            if(i != answers.length - 1) {
                formattedAnswers = formattedAnswers + ", ";
            }
        }

        formattedAnswers += ")";

        return formattedAnswers;
    }

    public void setAddition(float addition) {
        this.addition = addition;
    }
}
