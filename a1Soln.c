#include <stdio.h>

int main(){
    
    const double weightLab = 20.0;
    const double weightQuiz = 20.0;
    const double weightLE = 10.0;
    const double weightA1 = 5.0;
    const double weightA2 = 7.0;
    const double weightA3 = 8.0;
    const double weightFinal = 30.0;
    
    double sumMarks = 0.0;
    double weightNewFinal, weightNewLE;
    double lab1, lab2, lab3, lab4, lab5, overallLabs;
    double q1, q2, q3, q4, overallQuiz;
    double labExam, overallLE;
    double a1;
    double a2;
    double a3;
    double overallAssns;
    int mossA1, mossA2, mossA3;
    
    double overallMarks;
    double finalExam, overallFinalExam, overallFinalExamOriginal;
    double sumQuizFinal;
    char more = 'y';
    char surveyLE = 'n';
    
    int stuNumber = 1;
    
    printf ("Welcome to ALT1300 admin page\n\n");
    
    while (more != 'n') {
        
        printf ("Student# %d: \n\n", stuNumber);
        
        printf ("Enter 5 lab marks, each separated by a space: ");
        scanf ("%lf%lf%lf%lf%lf", &lab1, &lab2, &lab3, &lab4, &lab5);
        
        overallLabs = (lab1 + lab2 + lab3 + lab4 + lab5) / 500 * weightLab;
        
        printf ("Overall lab grade (20) = %.2lf / %.0lf\n\n", overallLabs, weightLab);
        
        // check 1 and 2
        
        printf ("Enter 3 assignment marks, each separated by a space: ");
        scanf ("%lf%lf%lf", &a1, &a2, &a3);
        
        printf ("Enter MOSS result for A1: ");
        scanf ("%d", &mossA1);
        
        printf ("Enter MOSS result for A2: ");
        scanf ("%d", &mossA2);
        
        printf ("Enter MOSS result for A3: ");
        scanf ("%d", &mossA3);
        
        if (mossA1 == 0)
        {
            a1 = 0;
        }
        
        if (mossA2 == 0)
        {
            a2 = 0;
        }
        
        if (mossA3 == 0)
        {
            a3 = 0;
        }
        
        
        overallAssns = a1 / 100 * 5 + a2 / 100 * 7 + a3 / 100 * 8;
        
        printf ("Overall assignment grade (20) = %.2lf / %.0lf\n\n", overallAssns, (weightA1 + weightA2 + weightA3));
        
        printf ("Enter 4 quiz marks, each separated by a space: ");
        scanf ("%lf%lf%lf%lf", &q1, &q2, &q3, &q4);
        
        overallQuiz = (q1 + q2 + q3 + q4) / 400 * weightQuiz;
        
        printf ("Overall quiz grade (20) = %.2lf / %.0lf\n\n", overallQuiz, weightQuiz);
        
        printf ("Enter lab exam mark: ");
        scanf ("%lf", &labExam);
        
        overallLE = labExam / 100 * weightLE;
        
        printf ("Enter the survey response (‘y’ or ‘n’): ");
        getchar();
        scanf ("%c", &surveyLE);
        
        // check 3
        
        weightNewFinal = weightFinal;
        weightNewLE = weightLE;
        
        if (surveyLE == 'y') {
            weightNewLE = 0;
            weightNewFinal = 40;
            overallLE = 0;
        }
        
        printf ("Your lab exam is worth %d now, and final exam is worth %d \n\n", (int) weightNewLE, (int) weightNewFinal);
        
        printf ("Enter final exam mark: ");
        scanf ("%lf", &finalExam);
        
        overallFinalExam = finalExam / 100 * weightNewFinal;
        overallFinalExamOriginal = finalExam / 100 * weightFinal;
        
        printf ("Overall final exam grade (%.0lf) = %.2lf / %.0lf\n\n", weightNewFinal, overallFinalExam, weightNewFinal);
        
        sumQuizFinal = overallQuiz + overallFinalExamOriginal;
        
        // check 4 - note that final exam is still worth 30% for this check
        if (sumQuizFinal >= 25) {
            printf ("Quizzes + Final Exam = %.2lf + %.2lf = %.2lf (>=25) \n\n", overallQuiz, overallFinalExamOriginal, sumQuizFinal);
            
            
        }
        else {
            printf ("Quizzes + Final Exam = %.2lf + %.2lf = %.2lf (< 25) \n\n", overallQuiz, overallFinalExamOriginal, sumQuizFinal);
            
        }
        
        printf ("Overall course marks (%%)\n");
        
        for (int i = 0; i < 25; i++) {
            printf ("*");
        }
        
        printf ("\n");
        
        printf ("Labs = %.2lf \n", overallLabs);
        printf ("Assignments = %.2lf \n", overallAssns);
        printf ("Quizzes = %.2lf \n", overallQuiz);
        
        if (surveyLE != 'y') {
            printf ("Lab Exam = %.2lf \n", overallLE);
        }
        
        printf ("Final Exam = %.2lf \n", overallFinalExam);
        
        for (int i = 0; i < 25; i++) {
            printf ("*");
        }
        
        printf ("\n");
        
        overallMarks = overallLabs + overallAssns + overallQuiz + overallLE + overallFinalExam;
        
        
        if (mossA1 == 0 && mossA2 == 0 && mossA3 == 0)
        {
            overallMarks = 0.0;
            printf ("All your assignments were flagged for plagiarism, and therefore \n");
        }
        
        printf ("Your overall course marks = %.2lf \n", overallMarks);
        
        if (sumQuizFinal < 25) {
            printf ("Overall grade = F\n");
        }
        else {
            if (overallMarks >= 80) {
                printf ("Overall grade = A\n");
            }
            else if (overallMarks >= 70) {
                printf ("Overall grade = B\n");
            }
            else if (overallMarks >= 60) {
                printf ("Overall grade = C\n");
            }
            else if (overallMarks >= 50) {
                printf ("Overall grade = D\n");
            }
            else {
                printf ("Overall grade = F\n");
            }
        }
        
        sumMarks = sumMarks + overallMarks;
        
        stuNumber++;
        
        printf ("Would you like to continue - enter y for yes, n for no: ");
        getchar();
        scanf ("%c", &more);
        
    }
    
    printf ("Average course mark of %d students = %.2lf%%\n", stuNumber - 1, sumMarks / (stuNumber - 1));
    return 0;
    
}
