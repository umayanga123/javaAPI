package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class UpdateStudentMarkView {
    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public UpdateStudentMarkView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               UPDATE    MARKS                                            |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void updateMark() {

        final Scanner scanner = new Scanner(System.in);
        boolean isupdateMark = true;
        while (isupdateMark) {
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
                        isupdateMark = true;
                    } else {
                        isupdateMark = false;
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
                int programingMark =studentInDb.getProgramingMark();
                int databaseMark =studentInDb.getDatabseMark();
                System.out.println("Prograaming Fundermental marks : " + programingMark);
                System.out.println("Database Mangement System marks : " + databaseMark);
                // Validate Programming Fundamentals mark
                do {
                    System.out.print("Enter the new Programming Fundamentals Mark: ");
                    programingMark = scanner.nextInt();
                    if (programingMark < 0 || programingMark > 100) {
                        System.out.println("Invalid mark. Please enter correct mark(0-100)");
                    }
                } while (programingMark < 0 || programingMark > 100);

                // Validate Database Management System mark
                do {
                    System.out.print("Enter the new Database Management System Mark: ");
                    databaseMark = scanner.nextInt();
                    if (databaseMark < 0 || databaseMark > 100) {
                        System.out.println("Invalid mark please enter correct mark(0-100)");
                    }
                } while (databaseMark < 0 || databaseMark > 100);

                studentInDb.setDatabseMark(databaseMark);
                studentInDb.setProgramingMark(programingMark);
                System.out.println("Marks have been updated susccsfully");
                System.out.println("Do you want to update mark for another sutudent ? [Y/n]");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isupdateMark = true;
                } else {
                    isupdateMark = false;
                    // go back main view
                    mainView.clearConsole();
                    mainView.ShowMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("This student's marks yet to be added");
                System.out.println("Do you want to update mark for another student ? (Y/n)");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isupdateMark = true;
                } else {
                    isupdateMark = false;
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
