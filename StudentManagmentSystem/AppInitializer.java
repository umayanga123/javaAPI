import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * Student marks store CLI programme
 */
public class AppInitializer {

    // size of array - make 100 rooms availble 5 arrays
    private int capacity = 100;
    // store student ids in array
    private String[] studentIds = new String[capacity];
    // store student names in array
    private String[] studentNames = new String[capacity];
    // store programming marks in array
    private int[] programmingMarks = new int[capacity];
    // store database marks in array
    private int[] databaseMarks = new int[capacity];
    // store database marks in total in array
    private int[] totalMarks = new int[capacity];
    // student count
    private int size = 0;

    /***
     * 
     * Main Method
     * Start programme run/execute here
     * 
     */
    public static void main(String[] args) {
        // load view class
        AppInitializer runApp = new AppInitializer();
        runApp.initializedApp();
        runApp.showMainMenu();
    }

    /**
     * Integer array default store 0.
     * CHanges them to -1 for identify data store places and not store places
     */
    private void initializedApp() {
        // iniialiszed array for defauild values
        // Initialize all arrays to -1
        for (int i = 0; i < capacity; i++) {
            programmingMarks[i] = -1;
            databaseMarks[i] = -1;
            totalMarks[i] = -1;
        }
    }

    // show main menue
    private void showMainMenu() {
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
                addStudent();
                break;
            case 2:
                addStudentWithMark();
                break;
            case 3:
                addMark();
                break;
            case 4:
                updateStudentName();
                break;
            case 5:
                updateMark();
                break;
            case 6:
                deleteStudentsFromList();
                break;
            case 7:
                printStudentDetails();
                break;
            case 8:
                printStudentRank();
                break;
            case 9:
                printBestInProgrammingFundamental();
                break;
            case 10:
                printBestInDB();
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

    /***
     * Clear command line
     */
    private final void clearConsole() {
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
            e.printStackTrace();
        }
    }

    // Add student
    private void addStudent() {

        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               ADD NEW STUDENT                                            |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isAddNewRecord = true;
        while (isAddNewRecord) {
            System.out.print("Enter Student Id : ");
            String id = scanner.next();
            boolean isExisitID = searchStudent(id);
            if (!isExisitID) {
                System.out.print("Enter student Name : ");
                String name = scanner.next();
                // Add student information directly into arrays
                studentIds[size] = id;
                studentNames[size] = name;
                programmingMarks[size] = -1;
                databaseMarks[size] = -1;
                totalMarks[size] = -1;
                size++;

                System.out.println("Student added sussfully.Do you want to added new student ? [Y/n]");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isAddNewRecord = true;
                } else {
                    isAddNewRecord = false;
                    // go back main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("The student id already exisit");
            }
        }
    }

    // Add new student with marks
    private void addStudentWithMark() {

        // Drow Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               ADD NEW STUDENT WITH MARKS                                |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isAddNewRecord = true;
        while (isAddNewRecord) {
            System.out.print("Enter Student Id : ");
            String id = scanner.next();
            boolean isExisitID = searchStudent(id);
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

                studentIds[size] = id;
                studentNames[size] = name;
                programmingMarks[size] = programingMark;
                databaseMarks[size] = databaseMark;
                totalMarks[size] = databaseMark + programingMark;
                size++;

                System.out.println("Student added sussfully.Do you want to added new student ? [Y/n]");
                String isExisit = scanner.next();
                if (isExisit.equals("Y")) {
                    isAddNewRecord = true;
                } else {
                    isAddNewRecord = false;
                    // go back main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("The student id already exisit");
            }
        }

    }

    // Add Marks
    private void addMark() {

        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               ADD MARKS                                                 |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isAddNewMark = true;
        while (isAddNewMark) {
            String[] studentInfo = null;
            while (studentInfo == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInfo = getStudent(id);
                if (studentInfo == null) {
                    System.out.println("Invalid Student id. Do you want to search again? (Y/n)");
                    String isExist = scanner.next();
                    if (isExist.equalsIgnoreCase("Y")) {
                        isAddNewMark = true;
                    } else {
                        isAddNewMark = false;
                        // Go back to main view
                        clearConsole();
                        showMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            String name = studentInfo[1];
            System.out.println("Student Name : " + name);

            int index = Integer.parseInt(studentInfo[2]);

            if (programmingMarks[index] == -1 && databaseMarks[index] == -1) {
                int programmingMark, databaseMark;
                // Validate Programming Fundamentals mark
                do {
                    System.out.print("Programming Fundamentals Mark: ");
                    programmingMark = scanner.nextInt();
                    if (programmingMark < 0 || programmingMark > 100) {
                        System.out.println("Invalid mark. Please enter correct mark(0-100)");
                    }
                } while (programmingMark < 0 || programmingMark > 100);

                // Validate Database Management System mark
                do {
                    System.out.print("Database Management System Mark: ");
                    databaseMark = scanner.nextInt();
                    if (databaseMark < 0 || databaseMark > 100) {
                        System.out.println("Invalid mark please enter correct mark(0-100)");
                    }
                } while (databaseMark < 0 || databaseMark > 100);

                // Update marks in the arrays
                programmingMarks[index] = programmingMark;
                databaseMarks[index] = databaseMark;
                totalMarks[index] = databaseMark + programmingMark;

                System.out.println("Marks have been added. Do you want to add marks for another student? [Y/n]");
                String isExist = scanner.next();
                if (!isExist.equalsIgnoreCase("Y")) {
                    isAddNewMark = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("This student's marks already added.");
                System.out.println("If you want to update the marks, please use [5] update mark option.\n");
                System.out.println("Do you want to add marks for another student? (Y/n)");
                String isExist = scanner.next();
                if (isExist.equalsIgnoreCase("Y")) {
                    isAddNewMark = true;
                } else {
                    isAddNewMark = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            }
        }

    }

    // update Studet details
    public void updateStudentName() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Update Student Details                                     |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isUpdateName = true;
        while (isUpdateName) {
            String[] studentInfo = null;
            while (studentInfo == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInfo = getStudent(id);
                if (studentInfo == null) {
                    System.out.println("Invalid Student id. Do you want to search again? (Y/n)");
                    String isExist = scanner.next();
                    if (isExist.equalsIgnoreCase("Y")) {
                        isUpdateName = true;
                    } else {
                        isUpdateName = false;
                        // Go back to main view
                        clearConsole();
                        showMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            String name = studentInfo[1];
            System.out.println("Student Name : " + name);

            System.out.print("Enter the new student name : ");
            String newName = scanner.next();

            int index = Integer.parseInt(studentInfo[2]);

            // Update student name in the array
            studentNames[index] = newName;

            System.out.println("Student details have been updated successfully.");
            System.out.println("Do you want to update name for another student? [Y/n]");
            String isExist = scanner.next();
            if (!isExist.equalsIgnoreCase("Y")) {
                isUpdateName = false;
                // Go back to main view
                clearConsole();
                showMainMenu();
                scanner.close();
                break;
            }
        }
    }

    // Update student marks
    private void updateMark() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               UPDATE    MARKS                                            |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isUpdateMark = true;
        while (isUpdateMark) {
            String[] studentInfo = null;
            while (studentInfo == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInfo = getStudent(id);
                if (studentInfo == null) {
                    System.out.println("Invalid Student id. Do you want to search again? (Y/n)");
                    String isExist = scanner.next();
                    if (isExist.equalsIgnoreCase("Y")) {
                        isUpdateMark = true;
                    } else {
                        isUpdateMark = false;
                        // Go back to main view
                        clearConsole();
                        showMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            String name = studentInfo[1];
            System.out.println("Student Name : " + name);

            int index = Integer.parseInt(studentInfo[2]);

            if (programmingMarks[index] != -1 && databaseMarks[index] != -1) {
                int programmingMark = programmingMarks[index];
                int databaseMark = databaseMarks[index];
                System.out.println("Programming Fundamentals marks : " + programmingMark);
                System.out.println("Database Management System marks : " + databaseMark);

                // Validate Programming Fundamentals mark
                do {
                    System.out.print("Enter the new Programming Fundamentals Mark: ");
                    programmingMark = scanner.nextInt();
                    if (programmingMark < 0 || programmingMark > 100) {
                        System.out.println("Invalid mark. Please enter correct mark(0-100)");
                    }
                } while (programmingMark < 0 || programmingMark > 100);

                // Validate Database Management System mark
                do {
                    System.out.print("Enter the new Database Management System Mark: ");
                    databaseMark = scanner.nextInt();
                    if (databaseMark < 0 || databaseMark > 100) {
                        System.out.println("Invalid mark please enter correct mark(0-100)");
                    }
                } while (databaseMark < 0 || databaseMark > 100);

                // Update marks in the arrays
                programmingMarks[index] = programmingMark;
                databaseMarks[index] = databaseMark;
                totalMarks[index] = programmingMark + databaseMark;

                System.out.println("Marks have been updated successfully.");
                System.out.println("Do you want to update marks for another student? [Y/n]");
                String isExist = scanner.next();
                if (!isExist.equalsIgnoreCase("Y")) {
                    isUpdateMark = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("This student's marks yet to be added.");
                System.out.println("Do you want to update marks for another student? (Y/n)");
                String isExist = scanner.next();
                if (isExist.equalsIgnoreCase("Y")) {
                    isUpdateMark = true;
                } else {
                    isUpdateMark = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            }
        }
    }

    // Delete student
    private void deleteStudentsFromList() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Delete Student                                            |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isDelete = true;
        while (isDelete) {
            String[] studentInfo = null;
            while (studentInfo == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInfo = getStudent(id);
                if (studentInfo == null) {
                    System.out.println("Invalid Student id. Do you want to search again? (Y/n)");
                    String isExist = scanner.next();
                    if (isExist.equalsIgnoreCase("Y")) {
                        isDelete = true;
                    } else {
                        isDelete = false;
                        // Go back to main view
                        clearConsole();
                        showMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            int index = Integer.parseInt(studentInfo[2]);

            // Delete called
            deleteStudent(index);

            System.out.println("Student has been deleted successfully.");
            System.out.println("Do you want to delete another student? [Y/n]");
            String isExist = scanner.next();
            if (!isExist.equalsIgnoreCase("Y")) {
                isDelete = false;
                // Go back to main view
                clearConsole();
                showMainMenu();
                scanner.close();
                break;
            }
        }
    }

    // print student details
    private void printStudentDetails() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Student Details                                     |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isViewStudentDetails = true;
        while (isViewStudentDetails) {
            String[] studentInfo = null;
            while (studentInfo == null) {
                System.out.print("Enter Student Id : ");
                String id = scanner.next();
                studentInfo = getStudent(id);
                if (studentInfo == null) {
                    System.out.println("Invalid Student id. Do you want to search again? (Y/n)");
                    String isExist = scanner.next();
                    if (isExist.equalsIgnoreCase("Y")) {
                        isViewStudentDetails = true;
                    } else {
                        isViewStudentDetails = false;
                        // Go back to main view
                        clearConsole();
                        showMainMenu();
                        scanner.close();
                        break;
                    }
                }
            }

            String name = studentInfo[1];
            System.out.println("Student Name : " + name);

            int index = Integer.parseInt(studentInfo[2]);

            if (programmingMarks[index] != -1 && databaseMarks[index] != -1) {
                int programmingMark = programmingMarks[index];
                int databaseMark = databaseMarks[index];
                int totalMarks = programmingMark + databaseMark;
                double averageMark = Math.round(((double) totalMarks / 2) * 100.0) / 100.0;

                System.out.println("-------------------------------------------------------");
                System.out.println("Programming Fundamentals marks : " + programmingMark);
                System.out.println("Database Management System marks : " + databaseMark);
                System.out.println("Total marks : " + totalMarks);
                System.out.println("Avg. marks : " + averageMark);
                int rank = getRank(studentInfo[0]);
                String rankText = "";
                switch (rank) {
                    case 1:
                        rankText = "( First )";
                        break;
                    case 2:
                        rankText = "( Second) ";
                        break;
                    case 3:
                        rankText = "( Thred )";
                        break;
                    case 4:
                        rankText = "( Last )";
                        break;
                    default:
                        break;
                }

                System.out.println("Rank : " + rank + rankText);
                System.out.println("-------------------------------------------------------");

                System.out.println("Do you want to search another student details ? [Y/n]");
                String isExist = scanner.next();
                if (!isExist.equalsIgnoreCase("Y")) {
                    isViewStudentDetails = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("Marks yet to be added.");
                System.out.println("Do you want to search another student details? (Y/n)");
                String isExist = scanner.next();
                if (!isExist.equalsIgnoreCase("Y")) {
                    isViewStudentDetails = false;
                    // Go back to main view
                    clearConsole();
                    showMainMenu();
                    scanner.close();
                    break;
                }
            }
        }
    }

    // Print student ranks sorted by rank
    private void printStudentRank() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Student's Ranks                                     |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isPrintStudentRank = true;
        while (isPrintStudentRank) {
            String[] studentIds = getAllStudentIds();
            int[] totalMarks = getTotalMarks();

            // Create an array to store the indices of studentIds array
            int[] indices = new int[size];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }

            // Sort the indices array based on student rank
            // Arrays.sort(indices, Comparator.comparingInt(index -> getRank(studentIds[index])));
            sortIndicesByRank(indices,studentIds);

            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("| Rank     |  ID       |   Name      | Total Marks    |  Avg. Marks    |");
            System.out.println("|----------------------------------------------------------------------|");

            for (int i = 0; i < size; i++) {
                int index = indices[i];
                if (totalMarks[index] >= 0) {
                    System.out.printf("| %-8d | %-9s | %-12s | %-14d | %-14.2f |\n",
                            i + 1, studentIds[index], studentNames[index],
                            totalMarks[index], getAverageMark(index));
                }
            }

            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("Do you want to search another student's details? [Y/n]");
            String isExist = scanner.next();
            if (!isExist.equalsIgnoreCase("Y")) {
                isPrintStudentRank = false;
                // Go back to main view
                clearConsole();
                showMainMenu();
                scanner.close();
                break;
            }
        }
    }

    // Method to print best in programming fundamentals
    private void printBestInProgrammingFundamental() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Best in Programming Fundamental                      |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isPrintBest = true;
        while (isPrintBest) {
            String[] bestIds = getBestProgrammingFundermentailStudentIds();

            System.out.println("|--------------------------------------------------|");
            System.out.println("|  ID       |   Name      | PF Mark    |  DB Mark  |");
            System.out.println("|--------------------------------------------------|");
            for (String id : bestIds) {
                int index = getIndexFromId(id);
                System.out.printf("| %-10s | %-10s | %-10d | %-10d |\n", id, studentNames[index],
                        programmingMarks[index], databaseMarks[index]);
            }

            System.out.println("|---------------------------------------------------|");
            System.out.println("Do you want to go back to the main menu? [Y/n]");
            String isExist = scanner.next();
            if (isExist.equals("Y")) {
                isPrintBest = true;
            } else {
                isPrintBest = false;
                // Go back to the main view
                clearConsole();
                showMainMenu();
                scanner.close();
                break;
            }
        }
    }

    // Method to print the best in Database Management
    private void printBestInDB() {
        // Draw Menu Items
        System.out.println("-------------------------------------------------------------------------- ");
        System.out.println("|               Print Best in Database Management                          |");
        System.out.println("-------------------------------------------------------------------------- ");

        final Scanner scanner = new Scanner(System.in);
        boolean isPrintBest = true;
        while (isPrintBest) {
            String[] bestIds = getBestDataBaseStudentsIds();

            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("|  ID       |   Name        | DB Mark | PF Mark |");
            System.out.println("|----------------------------------------------------------------------|");
            for (String id : bestIds) {
                int index = getIndexFromId(id);
                String name = studentNames[index];
                int dbMark = databaseMarks[index];
                int pfMark = programmingMarks[index];

                // Format the output to align with the table header
                System.out.printf("| %-10s| %-14s| %-8d| %-8d|\n", id, name, dbMark, pfMark);
            }

            System.out.println("|----------------------------------------------------------------------|");
            System.out.println("Do you want to go back to the main menu? [Y/n]");
            String isExist = scanner.next();
            if (isExist.equals("Y")) {
                isPrintBest = true;
            } else {
                isPrintBest = false;
                // Go back to the main view
                clearConsole();
                showMainMenu();
                scanner.close();
                break;
            }
        }
    }

    /// ------------Controled oprations--------------------------------//
    // Check if student exists in the arrays
    private boolean searchStudent(String studentId) {
        for (int i = 0; i < size; i++) {
            if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    // Delete a student from the database
    private void deleteStudent(int index) {
        // Shift all elements to the left to overwrite the deleted student
        for (int i = index; i < size - 1; i++) {
            studentIds[i] = studentIds[i + 1];
            studentNames[i] = studentNames[i + 1];
            programmingMarks[i] = programmingMarks[i + 1];
            databaseMarks[i] = databaseMarks[i + 1];
            totalMarks[i] = totalMarks[i + 1];

        }
        // Set the last element to null to remove the duplicate data
        studentIds[size - 1] = null;
        studentNames[size - 1] = null;
        programmingMarks[size - 1] = -1;
        databaseMarks[size - 1] = -1;
        totalMarks[size - 1] = -1;
        size--; // Decrement the size
    }

    // Method to get student information based on ID
    private String[] getStudent(String studentId) {
        for (int i = 0; i < size; i++) {
            if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                // If student ID matches, return an array containing student information
                return new String[] { studentIds[i], studentNames[i], Integer.toString(i) };
            }
        }
        return null; // If student not found
    }

    // Method to get all student IDs
    private String[] getAllStudentIds() {
        // Assuming studentIds is an array storing student IDs
        return studentIds;
    }

    // Method to get total marks of all students
    private int[] getTotalMarks() {
        // Assuming totalMarks is an array storing total marks of students
        return totalMarks;
    }

    // Method to calculate average mark for a student at given index
    private double getAverageMark(int index) {
        // Assuming programingMarks and databaseMarks are arrays storing marks
        int programingMark = programmingMarks[index];
        int databaseMark = databaseMarks[index];
        return Math.round(((double) (programingMark + databaseMark) / 2) * 100.0) / 100.0;
    }

    // Get rank of a student based on their ID
    private int getRank(String id) {
        int rank = 1;
        double targetAverage = getAverageMark(getIndexFromId(id));

        for (int i = 0; i < size; i++) {
            if (programmingMarks[i] != -1 && databaseMarks[i] != -1) {
                double otherAverage = getAverageMark(i);
                if (otherAverage > targetAverage) {
                    rank++;
                }
            }
        }
        return rank;
    }

    // Method to get IDs of students with the best Programming Fundamental marks
    private String[] getBestProgrammingFundermentailStudentIds() {
        // Step 1: Count valid students
        int validCount = 0;
        for (int mark : programmingMarks) {
            if (mark >= 0) {
                validCount++;
            }
        }

        // Step 2: Create a new array with the size of valid students
        String[] validIds = new String[validCount];

        // Step 3: Populate the new array with valid student IDs
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (programmingMarks[i] >= 0) {
                validIds[index++] = studentIds[i];
            }
        }

        // Step 4: Sort the array based on Programming Fundamental marks in descending
        // order
        if (validIds.length > 1) {
            Arrays.sort(validIds, Comparator.comparingInt(this::getProgrammingMark).reversed());
        }

        return validIds;
    }

    // Method to get Programming Fundamental mark for a student with given ID
    private int getProgrammingMark(String id) {
        int index = getIndexFromId(id);
        return programmingMarks[index];
    }

    // Method to get IDs of students with the best Database Management marks
    private String[] getBestDataBaseStudentsIds() {
        // Step 1: Count valid students
        int validCount = 0;
        for (int mark : databaseMarks) {
            if (mark >= 0) {
                validCount++;
            }
        }

        // Step 2: Create a new array with the size of valid students
        String[] validIds = new String[validCount];

        // Step 3: Populate the new array with valid student IDs
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (databaseMarks[i] >= 0) {
                validIds[index++] = studentIds[i];
            }
        }

        // Step 4: Sort the array based on Database Management marks in descending order
        if (validIds.length > 1) {
            Arrays.sort(validIds, Comparator.comparingInt(this::getDatabaseMark).reversed());
        }
        return validIds;
    }

    // Method to get Database Management mark for a student with given ID
    private int getDatabaseMark(String id) {
        int index = getIndexFromId(id);
        return databaseMarks[index];
    }

    // Method to get index of student with given ID
    private int getIndexFromId(String id) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(studentIds[i], id)) {
                return i;
            }
        }
        return -1; // ID not found
    }

    // sorting algorthem
    private void sortIndicesByRank(int[] indices, String[] studentIds) {
        // Create an array to store ranks corresponding to indices
        int[] ranks = new int[indices.length];

        // Populate ranks array
        for (int i = 0; i < indices.length; i++) {
            ranks[i] = getRank(studentIds[indices[i]]);
        }

        // Sort indices array based on ranks using Bubble Sort
        bubbleSort(ranks, indices, studentIds);
    }

    // Custom implementation of Bubble Sort
    private void bubbleSort(int[] arr, int[] indices, String[] studentIds) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap indices array
                    int tempIndex = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = tempIndex;

                    // Swap arr array
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
