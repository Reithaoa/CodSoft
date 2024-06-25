//package org.reitumetse;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Data {
//
//    private static final String FILE_NAME = "student_data.dat";
//
//    /**
//     * Save list of students to a file.
//     *
//     * @param students the list of students to save
//     */
//    public static void saveStudents(List<Student> students) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
//            oos.writeObject(students);
//            System.out.println("Student data saved successfully.");
//        } catch (IOException e) {
//            System.err.println("Error saving student data: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Load list of students from a file.
//     *
//     * @return the list of students loaded from file
//     */
//    public static List<Student> loadStudents() {
//        List<Student> students = new ArrayList<>();
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
//            students = (List<Student>) ois.readObject();
//            System.out.println("Student data loaded successfully.");
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading student data: " + e.getMessage());
//        }
//        return students;
//
//        // todo: add ability to covert all student data to json file with keys name, surname, subjects, average grade, grade symbol
//
////and distinctions students being placed separately
//    }
//}
//













package org.reitumetse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private static final String FILE_PATH = "student_data.dat";

    public static void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
