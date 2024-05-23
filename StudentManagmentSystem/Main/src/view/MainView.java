package view;

import java.util.Scanner;

public class MainView {
    public void ShowMainMenu() {
        // clearConsole();
        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Welcome to GSDS Marks Management System                   |");
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("[1] Add New Stutent                    [2] Add new Student with mark");
        System.out.println("[3] Add Marks                          [4] Update Student Detials");
        System.out.println("[5] Update Marks                       [6] Delete Student");
        System.out.println("[7] Print Student Details              [8] Print student Rank");
        System.out.println("[9] Best In programming Fundermental   [10] Best In Database managment");
        System.out.println("[11] Quit\n");

        // Defiend scanner to get key board input
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                ADDNewStudentView addNewStudentView = new ADDNewStudentView(this);
                addNewStudentView.AddStudent();
                break;
            case 2:
                ADDNewStudentWithMarkView addNewStudentWithMarkView = new ADDNewStudentWithMarkView(this);
                addNewStudentWithMarkView.addStudentWithMark();
                break;
            case 3:
                AddMarkView addMarkView = new AddMarkView(this);
                addMarkView.addMark();
                break;
            case 4:
                UpdateStudentDetailsView updateStudentDetailsView = new UpdateStudentDetailsView(this);
                updateStudentDetailsView.updateStudentName();
                break;
            case 5:
                UpdateStudentMarkView updateStudentMarkView = new UpdateStudentMarkView(this);
                updateStudentMarkView.updateMark();
                break;
            case 6:
                DeleteStudentView deleteStudentMarkView = new DeleteStudentView(this);
                deleteStudentMarkView.deteleStudent();
                break;
            case 7:
                PrintStudentDetailView printStudentDetailView = new PrintStudentDetailView(this);
                printStudentDetailView.printStudentDetails();
                break;
            case 8:
                PrintStudentRankiView printStudentRankiView = new PrintStudentRankiView(this);
                printStudentRankiView.printStudentRank();
                break;
            case 9:
            PrintBestPregamingStudentView printBestPFView = new PrintBestPregamingStudentView(this);
                printBestPFView.printBestInProgrammingFundermental();
                break;
            case 10:
                PrintBestDBStudentView printBestDBView = new PrintBestDBStudentView(this);
                printBestDBView.printBestInDB();
                break;
            case 11:
                // exit from main menu
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Selection");
                scanner.close();
                System.exit(0);
        }

    }

    public final void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace(); //
        }
    }
}
