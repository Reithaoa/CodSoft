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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private static final String FILE_PATH = "src/main/java/org/reitumetse/studentData.json";

    /**
     * Saves the list of students to a JSON file.
     *
     * @param students the list of students to save
     */
    public static void saveStudents(List<Student> students) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), students);
//            mapper.writeValue(new File(FILE_PATH), students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of students from a JSON file.
     *
     * @return the list of students loaded from the file, or an empty list if file not found
     */
    public static List<Student> loadStudents() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Initializes the student data file if it doesn't already exist.
     */
    public static void initializeDataFile() {
        Path filePath = Path.of(FILE_PATH);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}













