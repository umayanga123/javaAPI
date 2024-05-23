package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class PrintBestPregamingStudentView {
   private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public PrintBestPregamingStudentView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Prinnt Best in programming fundermental                   |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void printBestInProgrammingFundermental() {

        final Scanner scanner = new Scanner(System.in);
        boolean isPrintStudentRank = true;
        while (isPrintStudentRank) {
            // Validate Programming Fundamentals mark
            Student[] students = studentContoller.getBestPFStudents();
  
            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("|  ID       |   Name      | PE Mark    |  DB Mark  |");
            System.out.println("|----------------------------------------------------------------------|");
            for (Student student : students) {
                System.out.println("|-------------------------------------------------------------------------------|");
                System.out.println("|"+ student.getId() +  "|" + student.getName() + "|" + student.getProgramingMark() + "|"+ student.getDatabseMark() + "|");
                System.out.println("|-------------------------------------------------------------------------------|");
            }

            System.out.println("Do you want to go back to main menu ? [Y/n]");
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
