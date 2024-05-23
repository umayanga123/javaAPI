package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class PrintStudentDetailView {

    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public PrintStudentDetailView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Student Details                                     |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void printStudentDetails() {

        final Scanner scanner = new Scanner(System.in);
        boolean isViewStudentDetials = true;
        while (isViewStudentDetials) {
            // Validate Programming Fundamentals mark
            Student studentInDb = null;
            while (studentInDb == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInDb = studentContoller.getStudent(id);
                if (studentInDb == null) {
                    System.out.println("Invalid Student id.Do you want serach again ? (Y/n)");
                    String isExisit = scanner.next();
                    if (isExisit.equals("Y")) {
                        isViewStudentDetials = true;
                    } else {
                        isViewStudentDetials = false;
                        // go back main view
                        mainView.clearConsole();
                        mainView.ShowMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            String name = studentInDb.getName();
            System.out.println("Student Name : " + name);

            if (studentInDb.getDatabseMark() != -1 && studentInDb.getProgramingMark() != -1) {
                int programingMark = studentInDb.getProgramingMark();
                int databaseMark = studentInDb.getDatabseMark();
                System.out.println("-------------------------------------------------------");
                System.out.println("Prograaming Fundermental marks : " + programingMark);
                System.out.println("Database Mangement System marks : " + databaseMark);
                System.out.println("Total marks : " + studentInDb.getTotalMark());
                System.out.println("Avg.  marks : " + studentInDb.getAvgMark());
                System.out.println("Ranks : " + studentContoller.getRank(studentInDb.getId()));
                System.out.println("-------------------------------------------------------");

                System.out.println("Do you want to search another sutudent details ? [Y/n]");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isViewStudentDetials = true;
                } else {
                    isViewStudentDetials = false;
                    // go back main view
                    mainView.clearConsole();
                    mainView.ShowMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("Marks yet to added");
                System.out.println("Do you want to search another student Detials ? (Y/n)");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isViewStudentDetials = true;
                } else {
                    isViewStudentDetials = false;
                    // go back main view
                    mainView.clearConsole();
                    mainView.ShowMainMenu();
                    scanner.close();
                    break;
                }
            }

        }
    }

}
