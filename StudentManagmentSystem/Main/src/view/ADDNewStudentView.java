package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class ADDNewStudentView {

    private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public ADDNewStudentView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               ADD NEW STUDENT                                            |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void AddStudent() {

        final Scanner scanner = new Scanner(System.in);
        boolean isAddNewRecord = true;
        while (isAddNewRecord) {
            System.out.print("Enter Student Id : ");
            String id = scanner.next();
            boolean isExisitID = studentContoller.serchStudent(id);
            if (!isExisitID) {
                System.out.print("Enter student Name : ");
                String name = scanner.next();
                int i = studentContoller.AddStudent(new Student(id, name));
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
