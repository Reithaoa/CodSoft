package org.reitumetse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Serializable {
    private String name;
    private List<String> subjects;
    private List<Integer> grades;
    private double averageGrade;
    private String averageGradeSymbol;


    public Student() {
        this.subjects = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.averageGrade = getAverageGrade();
        this.averageGradeSymbol = getAverageGradeSymbol();

    }

    public Student(String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.averageGrade = getAverageGrade();
        this.averageGradeSymbol = getAverageGradeSymbol();
    }

    public void addSubjectGrade(String subject, int grade) {
        subjects.add(subject);
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return (double) total / grades.size();
    }

    public String getAverageGradeSymbol() {
        double average = getAverageGrade();
        if (average >= 90) {
            return "A: Outstanding";
        } else if (average >= 80) {
            return "B: Good";
        } else if (average >= 70) {
            return "C: Satisfactory";
        } else if (average >= 60) {
            return "D: Unsatisfactory";
        } else if (average >= 50) {
            return "E: Marginal";
        } else {
            return "F: Fail";
        }
    }
}
