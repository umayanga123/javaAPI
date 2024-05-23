package controller;

import java.util.Arrays;
import java.util.Comparator;

import db.DBContoller;
import model.Student;

public class StudentContoller {

    // initalized data base
    private DBContoller studentDB = DBContoller.getInstance();
    private static StudentContoller iContoller;

    private StudentContoller() {

    }

    /**
     * Add student to the system
     * 
     * @param student
     * @return int
     */
    public int AddStudent(Student student) {
        // add new student object
        studentDB.addStudent(student);
        ;
        return 1;
    }

    // If student find in Array reutn true ,else return false
    public boolean serchStudent(String studentId) {
        Student students[] = studentDB.getStudentDB();
        for (Student s : students) {
            if (s != null && s.getId().equals(studentId)) {
                return true;
            }
        }
        return false;

    }

    // Delete a student to the database
    public void deleteStudent(Student studentId) {
        Student[] students = studentDB.getStudentDB();
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId().equals(studentId.getId())) {
                studentDB.deleteStudent(i); // Delete the student at index i
                return; // Exit the method after deleting the student
            }
        }
    }

    // If student find in Array reutn Student ,else return null
    public Student getStudent(String studentId) {
        Student students[] = studentDB.getStudentDB();
        for (Student s : students) {
            if (s != null && s.getId().equals(studentId)) {
                return s;
            }
        }
        return null;

    }

    // return all students
    public Student[] getAllStudent() {
        return studentDB.getStudentDB();
    }

    public Student[] getFilterStudents() {
        // Step 1: Count valid students
        Student[] students = getAllStudent();
        int validCount = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validCount++;
            }
        }

        // Step 2: Create a new array with the size of valid students
        Student[] validStudents = new Student[validCount];

        // Step 3: Populate the new array with valid students
        int index = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validStudents[index++] = student;
            }
        }

        // Step 4: Sort the array based on average marks in ascending order
        Arrays.sort(validStudents, Comparator.comparingDouble(Student::getAvgMark).reversed());

        return validStudents;
    }

    public int getRank(String id) {
        // Step 1: Count valid students
        Student[] students = getFilterStudents();
        // Step 2: Find the index of the student with the given ID
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId().equals(id)) {
                // Return the rank (index + 1 since ranks start from 1)
                return i + 1;
            }
        }

        // If student with the given ID is not found, return -1
        return -1;
    }

    public Student[] getBestPFStudents() {
        // Step 1: Count valid students
        Student[] students = getAllStudent();
        int validCount = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validCount++;
            }
        }

        // Step 2: Create a new array with the size of valid students
        Student[] validStudents = new Student[validCount];

        // Step 3: Populate the new array with valid students
        int index = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validStudents[index++] = student;
            }
        }

        // Step 4: Sort the array based on average marks in ascending order
        Arrays.sort(validStudents, Comparator.comparingDouble(Student::getProgramingMark).reversed());

        return validStudents;
    }
    public Student[] getBestDBStudents() {
        // Step 1: Count valid students
        Student[] students = getAllStudent();
        int validCount = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validCount++;
            }
        }

        // Step 2: Create a new array with the size of valid students
        Student[] validStudents = new Student[validCount];

        // Step 3: Populate the new array with valid students
        int index = 0;
        for (Student student : students) {
            if (student != null && student.getTotalMark() >= 0) {
                validStudents[index++] = student;
            }
        }

        // Step 4: Sort the array based on average marks in ascending order
        Arrays.sort(validStudents, Comparator.comparingDouble(Student::getDatabseMark).reversed());

        return validStudents;
    }

    public static StudentContoller getInstance() {
        if (iContoller == null) {
            iContoller = new StudentContoller();
        }
        return iContoller;
    }

}
