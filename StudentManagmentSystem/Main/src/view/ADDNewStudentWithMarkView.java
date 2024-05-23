package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class ADDNewStudentWithMarkView {

    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public ADDNewStudentWithMarkView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               ADD NEW STUDENT WITH MARKS                                |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void addStudentWithMark() {

        final Scanner scanner = new Scanner(System.in);
        boolean isAddNewRecord = true;
        while (isAddNewRecord) {
            System.out.print("Enter Student Id : ");
            String id = scanner.next();
            boolean isExisitID = studentContoller.serchStudent(id);
            if (!isExisitID) {
                System.out.print("Enter student Name : ");
                String name = scanner.next();

                int programingMark, databaseMark;

                // Validate Programming Fundamentals mark
                do {
                    System.out.print("Programming Fundamentals Mark: ");
                    programingMark = scanner.nextInt();
                    if (programingMark < 0 || programingMark > 100) {
                        System.out.println("Invalid mark. Please enter correct mark(0-100)");
                    }
                } while (programingMark < 0 || programingMark > 100);
        
                // Validate Database Management System mark
                do {
                    System.out.print("Database Management System Mark: ");
                    databaseMark = scanner.nextInt();
                    if (databaseMark < 0 || databaseMark > 100) {
                        System.out.println("Invalid mark please enter correct mark(0-100)");
                    }
                } while (databaseMark < 0 || databaseMark > 100);
                
                
                int i = studentContoller.AddStudent(new Student(id, name,programingMark,databaseMark));
                if (i == 1) {
                    System.out.println("Student added sussfully.Do you want to added new student ? [Y/n]");
                    String isExisit = scanner.next();
                    if (isExisit.equals("Y")) {
                        isAddNewRecord = true;
                    } else {
                        isAddNewRecord = false;
                        //go back main view
                        mainView.clearConsole();
                        mainView.ShowMainMenu();
                        scanner.close();
                        break;
                    }
                } else {
                    System.out.println("Student id already exist");
                }
            } else {
                System.out.println("The student id already exisit");
            }
        }

    }
}
