package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class PrintStudentRankiView {
    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public PrintStudentRankiView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Student's Ranks                                     |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void printStudentRank() {

        final Scanner scanner = new Scanner(System.in);
        boolean isPrintStudentRank = true;
        while (isPrintStudentRank) {
            // Validate Programming Fundamentals mark
            int rank = 1;
            Student[] students = studentContoller.getFilterStudents();
  
            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("| Rank     |  ID       |   Name      | Total Marks    |  Avarg. Marks  |");
            System.out.println("|----------------------------------------------------------------------|");
            for (Student student : students) {
                System.out.println("|-------------------------------------------------------------------------------|");
                System.out.println("|"+ rank + "|" + student.getId() +  "|" + student.getName() + "|" + student.getTotalMark() + "|"+ student.getAvgMark() + "|");
                System.out.println("|-------------------------------------------------------------------------------|");
                rank +=1; 
            }

            System.out.println("Do you want to search another sutudent details ? [Y/n]");
            String isExisit = scanner.next();
            if (isExisit.equals("Y")) {
                isPrintStudentRank = true;
            } else {
                isPrintStudentRank = false;
                // go back main view
                mainView.clearConsole();
                mainView.ShowMainMenu();
                scanner.close();
                break;
            }
        }

    }
}
