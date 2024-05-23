package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class UpdateStudentDetailsView {
    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public UpdateStudentDetailsView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Update Student Details                                     |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void updateStudentName() {

        final Scanner scanner = new Scanner(System.in);
        boolean isUpdateName = true;
        while (isUpdateName) {
            // Validate Programming Fundamentals mark
            Student studentInDb = null;
            while (studentInDb == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInDb = studentContoller.getStudent(id);
                if (studentInDb == null) {
                    System.out.println("Invalid Student id. Do you want serach again ? (Y/n)");
                    String isExisit = scanner.next();
                    if (isExisit.equals("Y")) {
                        isUpdateName = true;
                    } else {
                        isUpdateName = false;
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

            System.out.print("Enter the new student name : ");
            String newName = scanner.next();

            studentInDb.setName(newName);
            
            System.out.println("Student details has been updated sucessfully");
            System.out.println("Marks have beed added. do you want to add mark for another sutudent ? [Y/n]");
            String isExisit = scanner.next();
            if (isExisit.equals("Y")) {
                isUpdateName = true;
            } else {
                isUpdateName = false;
                // go back main view
                mainView.clearConsole();
                mainView.ShowMainMenu();
                scanner.close();
                break;
            }

        }
    }

}
