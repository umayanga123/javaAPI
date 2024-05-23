
package db;

import model.Student;

/***
 * This class hold the list and it will take student object as database
 */
public class DBContoller {

    private Student[] students;
    private int capacity;
    private int size;

    private static DBContoller instance;

    private DBContoller() {
        // Initialize the array with an initial capacity
        capacity = 100; // You can adjust the initial capacity as needed
        students = new Student[capacity];
        size = 0;
    }

    public static DBContoller getInstance() {
        if (instance == null) {
            instance = new DBContoller();
        }
        return instance;
    }

    // Return array of Student objects
    public Student[] getStudentDB() {
        return students;
    }

    // Add a student to the database
    public void addStudent(Student student) {
        if (size == capacity) {
            // If the array is full, increase its capacity
            increaseCapacity();
        }
        students[size] = student;
        size++;
    }

     // Delete a student from the database
     public void deleteStudent(int index) {
        if (index >= 0 && index < size) {
            // Shift elements to the left to fill the gap left by the deleted student
            for (int i = index; i < size - 1; i++) {
                students[i] = students[i + 1];
            }
            // Set the last element to null and decrement the size
            students[size - 1] = null;
            size--;
        }
    }


    // Increase the capacity of the array
    private void increaseCapacity() {
        int newCapacity = capacity * 2; // Double the capacity
        Student[] newArray = new Student[newCapacity];
        // Copy existing elements to the new array
        System.arraycopy(students, 0, newArray, 0, size);
        students = newArray;
        capacity = newCapacity;
    }
}
