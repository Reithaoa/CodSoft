package org.reitumetse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages a list of students with their subjects and grades, providing a GUI to add, remove, edit, and search students.
 */
public class Manager extends JFrame {
    // List to store students
    private List<Student> students = new ArrayList<>();
    private JTable studentTable;
    private DefaultTableModel model;
    private JTextField searchInput;

    // Predefined allowed subjects
    private static final List<String> ALLOWED_SUBJECTS = Arrays.asList(
            "Economics", "Mathematical Literacy", "Mathematics", "Technical Mathematics", "Business Studies", "Geography", "History", "Life Sciences",
            "Accounting", "Agricultural Science", "Technical Science", "Physical sciences", "Computer and Information Technology",
            "Numeracy", "Life Orientation", "Visual Arts", "Consumer Studies", "Religious Studies",
            "Social Sciences", "Performing arts", "Music", "Electrical Engineering and Technology",
            "Hospitality", "Engineering Graphics and Design", "Second Additional Language", "First Additional Language",
            "Home language");

    /**
     * Constructs a new Manager frame.
     */
    public Manager() {
        setTitle("Student Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        students = Data.loadStudents();

        // Input field for searching students
        searchInput = new JTextField("", 30);
        // Buttons for various actions
        JButton addStudentButton = new JButton("Add");
        JButton removeStudentButton = new JButton("Remove");
        JButton editStudentButton = new JButton("Edit");
        JButton searchStudentButton = new JButton("Search");
        JButton showAllButton = new JButton("All"); // New button to show all students

        // Action listener for adding a student
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // Action listener for removing a student
        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        // Action listener for editing a student
        editStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        // Action listener for searching a student
        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent(searchInput.getText().trim());
            }
        });

        // Action listener for showing all students
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        // Search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setSize(500, 50);
        searchPanel.add(new JLabel("Search"), BorderLayout.WEST);
        searchPanel.add(searchInput, BorderLayout.CENTER);
        searchPanel.add(searchStudentButton, BorderLayout.EAST);

        // Control button panel
        JPanel controlButtonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        controlButtonPanel.add(addStudentButton);
        controlButtonPanel.add(removeStudentButton);
        controlButtonPanel.add(editStudentButton);
        controlButtonPanel.add(showAllButton); // Adding the "All" button to the control panel

        // Table for displaying students
        model = new DefaultTableModel();
        studentTable = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Number", "Name", "Subject", "Grade", "Average Grade", "Grade Symbol"});
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        // Setting layout and adding components
        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(controlButtonPanel, BorderLayout.SOUTH);

        refreshTable(); // Add this line
    }

    /**
     * Adds a new student to the list.
     */
    private void addStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name:");

        if (studentName != null && !studentName.trim().isEmpty()) {
            Student newStudent = new Student(studentName);
            boolean addingSubjects = true;

            // Loop for adding subjects and grades
            while (addingSubjects) {
                JComboBox<String> subjectDropdown = new JComboBox<>(ALLOWED_SUBJECTS.toArray(new String[0]));
                int subjectResult = JOptionPane.showConfirmDialog(null, subjectDropdown, "Select Subject", JOptionPane.OK_CANCEL_OPTION);
                if (subjectResult == JOptionPane.CANCEL_OPTION) {
                    addingSubjects = false;
                } else {
                    String subject = (String) subjectDropdown.getSelectedItem();
                    boolean validGrade = false;
                    try {
                        int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
                        if (grade > 100) {
                            JOptionPane.showMessageDialog(null, "Grade cannot be more than 100. Please enter a valid grade.");
                        } else {
                            newStudent.addSubjectGrade(subject, grade);
                            validGrade = true;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
                    }
                }
            }
            students.add(newStudent);
            Data.saveStudents(students); // Save updated student list
            refreshTable();
        }
    }

    /**
     * Removes a student from the list.
     */
    private void removeStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name to Remove:");
        if (studentName != null && !studentName.trim().isEmpty()) {
            students.removeIf(student -> student.getName().equalsIgnoreCase(studentName));
            Data.saveStudents(students); // Save updated student list
            refreshTable();
        }
    }

    /**
     * Edits a student in the list.
     */
    private void editStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name to Edit:");
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                boolean editingSubjects = true;

                // Loop for editing subjects and grades
                while (editingSubjects) {
                    JComboBox<String> subjectDropdown = new JComboBox<>(ALLOWED_SUBJECTS.toArray(new String[0]));
                    int subjectResult = JOptionPane.showConfirmDialog(null, subjectDropdown, "Select Subject", JOptionPane.OK_CANCEL_OPTION);
                    if (subjectResult == JOptionPane.CANCEL_OPTION) {
                        editingSubjects = false;
                    } else {
                        String subject = (String) subjectDropdown.getSelectedItem();
                        try {
                            int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
                            student.addSubjectGrade(subject, grade);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
                        }
                    }
                }
                Data.saveStudents(students); // Save updated student list
                refreshTable();
                return;
            }
        }
    }

    /**
     * Searches for a student by name.
     *
     * @param studentName the name of the student to search for
     */
    private void searchStudent(String studentName) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                result.add(student);
            }
        }
        refreshTable(result);
    }

    /**
     * Refreshes the table with the full list of students.
     */
    private void refreshTable() {
        refreshTable(students);
    }

    /**
     * Refreshes the table with a specific list of students.
     *
     * @param studentList the list of students to display
     */
    private void refreshTable(List<Student> studentList) {
        model.setRowCount(0); // Clear existing data
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            List<String> subjects = student.getSubjects();
            List<Integer> grades = student.getGrades();
            // Populate table rows with student data
            for (int j = 0; j < subjects.size(); j++) {
                String[] rowData = new String[6];
                rowData[0] = (j == 0) ? String.valueOf(i + 1) : ""; // Number only in the first row of each student
                rowData[1] = (j == 0) ? student.getName() : ""; // Name only in the first row of each student
                rowData[2] = subjects.get(j);
                rowData[3] = String.valueOf(grades.get(j));
                rowData[4] = (j == 0) ? String.valueOf(student.getAverageGrade()) : ""; // Average grade only in the first row of each student
                rowData[5] = (j == 0) ? student.getAverageGradeSymbol() : ""; // Grade symbol only in the first row of each student
                model.addRow(rowData);
            }
        }
    }

    /**
     * The main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Manager manager = new Manager();
            manager.setVisible(true);
        });
    }
}
