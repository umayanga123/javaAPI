package view;

import java.util.Scanner;

import controller.StudentContoller;
import model.Student;

public class DeleteStudentView {
 private StudentContoller studentContoller = StudentContoller.getInstance();
    private final MainView mainView;

    public DeleteStudentView(MainView mainView) {
        this.mainView = mainView;
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Delete Student                                            |");
        System.out.println("-------------------------------------------------------------------------- ");
    }

    public void deteleStudent() {

        final Scanner scanner = new Scanner(System.in);
        boolean isDelete = true;
        while (isDelete) {
            Student studentInDb = null;
            while (studentInDb == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInDb = studentContoller.getStudent(id);
                if (studentInDb == null) {
                    System.out.println("Invalid Student id. Do you want serach again ? (Y/n)");
                    String isExisit = scanner.next();
                    if (isExisit.equals("Y")) {
                        isDelete = true;
                    } else {
                        isDelete = false;
                        // go back main view
                        mainView.clearConsole();
                        mainView.ShowMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

           
            //delete called
            studentContoller.deleteStudent(studentInDb) ;     
            
            System.out.println("Student has been Deleted sucessfully");
            System.out.println("Do you want to delete another sutudent ? [Y/n]");
            String isExisit = scanner.next();
            if (isExisit.equals("Y")) {
                isDelete = true;
            } else {
                isDelete = false;
                // go back main view
                mainView.clearConsole();
                mainView.ShowMainMenu();
                scanner.close();
                break;
            }

        }
    }

}
