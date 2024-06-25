package org.reitumetse;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with a name, subjects, and grades.
 */
public class Student {
    // The name of the student
    private String name;
    // List of subjects the student is enrolled in
    private List<String> subjects;
    // List of grades the student has received
    private List<Integer> grades;


    /**
     * Constructs a new Student with the given name.
     *
     * @param name the name of the student
     */
    public Student(String name) {
        this.name = name;
//        this.subjects = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    /**
     * Returns the name of the student.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a subject and the corresponding grade to the student's record.
     *
     * @param subject the name of the subject
     * @param grade the grade for the subject
     */
    public void addSubjectGrade(String subject, int grade) {
        subjects.add(subject);
        grades.add(grade);
    }

    /**
     * Returns the list of subjects the student is enrolled in.
     *
     * @return a list of subjects
     */
    public List<String> getSubjects() {
        this.subjects = new ArrayList<>();

        return subjects;
    }

    /**
     * Returns the list of grades the student has received.
     *
     * @return a list of grades
     */
    public List<Integer> getGrades() {
        return grades;
    }

    /**
     * Calculates and returns the average grade of the student.
     *
     * @return the average grade as a float
     */
    public float getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        int total = 0;
        // Sum all grades
        for (int grade : grades) {
            total += grade;
        }
        // Calculate average
        return (float) total / grades.size();
    }

    /**
     * Returns the average grade as a letter symbol.
     *
     * @return the average grade symbol
     */
    public String getAverageGradeSymbol() {
        float average = getAverageGrade();
        // Determine grade symbol based on average grade
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 60) return "B";
        else if (average >= 40) return "C";
        else return "D";
    }
}
