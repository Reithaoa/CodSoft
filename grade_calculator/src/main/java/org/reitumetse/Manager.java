//package org.reitumetse;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Manages a list of students with their subjects and grades, providing a GUI to add, remove, edit, and search students.
// */
//public class Manager extends JFrame {
//    // List to store students
//    private List<Student> students = new ArrayList<>();
//
//    /**
//     * Constructs a new Manager frame.
//     */
//    public Manager() {
//        setTitle("Student Manager");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Input field for searching students
//        JTextField searchInput = new JTextField("", 30);
//        // Buttons for various actions
//        JButton addStudentButton = new JButton("Add");
//        JButton removeStudentButton = new JButton("Remove");
//        JButton editStudentButton = new JButton("Edit");
//        JButton searchStudentButton = new JButton("Search");
//
//        // Action listener for adding a student
//        addStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addStudent();
//            }
//        });
//
//        // Action listener for removing a student
//        removeStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                removeStudent();
//            }
//        });
//
//        // Action listener for editing a student
//        editStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                editStudent();
//            }
//        });
//
//        // Action listener for searching a student
//        searchStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchStudent(searchInput.getText().trim());
//            }
//        });
//
//        // Search panel
//        JPanel searchPanel = new JPanel(new BorderLayout());
//        searchPanel.setSize(500, 50);
//        searchPanel.add(new JLabel("Search"), BorderLayout.WEST);
//        searchPanel.add(searchInput, BorderLayout.CENTER);
//        searchPanel.add(searchStudentButton, BorderLayout.EAST);
//
//        // Control button panel
//        JPanel ctrlBtnPnl = new JPanel(new GridLayout(1, 3, 10, 0));
//        ctrlBtnPnl.add(addStudentButton);
//        ctrlBtnPnl.add(removeStudentButton);
//        ctrlBtnPnl.add(editStudentButton);
//
//        // Table for displaying students
//        DefaultTableModel model = new DefaultTableModel();
//        JTable studentTable = new JTable(model);
//        model.setColumnIdentifiers(new String[]{"Number", "Name", "Subject", "Grade", "Average Grade", "Grade Symbol"});
//        JScrollPane tableScrollPane = new JScrollPane(studentTable);
//
//        // Setting layout and adding components
//        setLayout(new BorderLayout());
//        add(searchPanel, BorderLayout.NORTH);
//        add(tableScrollPane, BorderLayout.CENTER);
//        add(ctrlBtnPnl, BorderLayout.SOUTH);
//    }
//
//    /**
//     * Adds a new student to the list.
//     */
//    private void addStudent() {
//        String studentName = JOptionPane.showInputDialog("Enter Student Name:");
//        if (studentName != null && !studentName.trim().isEmpty()) {
//            Student newStudent = new Student(studentName);
//            boolean addingSubjects = true;
//            //todo: account for the student already in captured data
//
//            // Loop for adding subjects and grades
//            while (addingSubjects) {
//                String subject = JOptionPane.showInputDialog("Enter Subject (or press Cancel to finish adding subjects):");
//                if (subject == null || subject.trim().isEmpty()) {
//                    addingSubjects = false;
//                } else {
//                    try {
//                        int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
//                        newStudent.addSubjectGrade(subject, grade);
//                    } catch (NumberFormatException e) {
//                        JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
//                    }
//                }
//            }
//            students.add(newStudent);
//            refreshTable();
//        }
//    }
//
//    /**
//     * Removes a student from the list.
//     */
//    private void removeStudent() {
//        String studentName = JOptionPane.showInputDialog("Enter Student Name to Remove:");
//        if (studentName != null && !studentName.trim().isEmpty()) {
//            students.removeIf(student -> student.getName().equalsIgnoreCase(studentName));
//            refreshTable();
//        }
//    }
//
//    /**
//     * Edits a student in the list.
//     */
//    private void editStudent() {
//        String studentName = JOptionPane.showInputDialog("Enter Student Name to Edit:");
//        for (Student student : students) {
//            if (student.getName().equalsIgnoreCase(studentName)) {
//                boolean editingSubjects = true;
//                // Loop for editing subjects and grades
//                while (editingSubjects) {
//                    String subject = JOptionPane.showInputDialog("Enter Subject (or press Cancel to finish editing subjects):");
//                    if (subject == null || subject.trim().isEmpty()) {
//                        editingSubjects = false;
//                    } else {
//                        try {
//                            int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
//                            student.addSubjectGrade(subject, grade);
//                        } catch (NumberFormatException e) {
//                            JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
//                        }
//                    }
//                }
//                refreshTable();
//                return;
//            }
//        }
//    }
//
//    /**
//     * Searches for a student by name.
//     *
//     * @param studentName the name of the student to search for
//     */
//    private void searchStudent(String studentName) {
//        List<Student> result = new ArrayList<>();
//        for (Student student : students) {
//            if (student.getName().equalsIgnoreCase(studentName)) {
//                result.add(student);
////                System.out.println(student);
//            } else {
//                System.out.println("Student is not on the data base.");
//            }
//        }
//        refreshTable(result);
//    }
//
//    /**
//     * Refreshes the table with the full list of students.
//     */
//    private void refreshTable() {
//        refreshTable(students);
//    }
//
//    /**
//     * Refreshes the table with a specific list of students.
//     *
//     * @param studentList the list of students to display
//     */
//    private void refreshTable(List<Student> studentList) {
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new String[]{"Number", "Name", "Subject", "Grade", "Average Grade", "Grade Symbol"});
//        for (int i = 0; i < studentList.size(); i++) {
//            Student student = studentList.get(i);
//            List<String> subjects = student.getSubjects();
//            List<Integer> grades = student.getGrades();
//            // Populate table rows with student data
//            for (int j = 0; j < subjects.size(); j++) {
//                String[] rowData = new String[6];
//                rowData[0] = String.valueOf(i + 1);
//                rowData[1] = (j == 0) ? student.getName() : ""; // Name only in the first row of each student
//                rowData[2] = subjects.get(j);
//                rowData[3] = String.valueOf(grades.get(j));
//                rowData[4] = (j == 0) ? String.valueOf(student.getAverageGrade()) : ""; // Average grade only in the first row of each student
//                rowData[5] = (j == 0) ? student.getAverageGradeSymbol() : ""; // Grade symbol only in the first row of each student
//                model.addRow(rowData);
//            }
//        }
//        // Update the table with new model
//        JTable studentTable = new JTable(model);
//        JScrollPane tableScrollPane = new JScrollPane(studentTable);
//        getContentPane().removeAll();
//
//        // Recreate search panel and control button panel
//        JPanel searchPanel = new JPanel(new BorderLayout());
//        searchPanel.setSize(500, 50);
//        JTextField searchInput = new JTextField("", 30);
//        JButton searchStudentButton = new JButton("Search");
//        searchStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchStudent(searchInput.getText().trim());
//            }
//        });
//        searchPanel.add(new JLabel("Search"), BorderLayout.WEST);
//        searchPanel.add(searchInput, BorderLayout.CENTER);
//        searchPanel.add(searchStudentButton, BorderLayout.EAST);
//
//        JPanel ctrlBtnPnl = new JPanel(new GridLayout(1, 3, 10, 0));
//        JButton addStudentButton = new JButton("Add");
//        addStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addStudent();
//            }
//        });
//        JButton removeStudentButton = new JButton("Remove");
//        removeStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                removeStudent();
//            }
//        });
//        JButton editStudentButton = new JButton("Edit");
//        editStudentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                editStudent();
//            }
//        });
//        ctrlBtnPnl.add(addStudentButton);
//        ctrlBtnPnl.add(removeStudentButton);
//        ctrlBtnPnl.add(editStudentButton);
//
//        // Setting layout and adding components
//        setLayout(new BorderLayout());
//        add(searchPanel, BorderLayout.NORTH);
//        add(tableScrollPane, BorderLayout.CENTER);
//        add(ctrlBtnPnl, BorderLayout.SOUTH);
//
//        // Refreshing the frame
//        repaint();
//        revalidate();
//    }
//
//    /**
//     * The main method.
//     *
//     * @param args command line arguments
//     */
//    public static void main(String[] args) {
//        Manager mng = new Manager();
//        mng.setVisible(true);
//    }
//}














package org.reitumetse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Manager extends JFrame {
    private List<Student> students;
    private JTextField searchInput;
    private JButton searchStudentButton;

    private static final String[] SUBJECTS = {
            "Economics", "Mathematics", "Business studies", "Geography", "History", "Biology",
            "Accounting", "Agricultural science", "Physical sciences", "Computers and information technology",
            "Numeracy", "Life Orientation", "Visual arts", "Consumer Studies", "Religious studies",
            "Social Sciences", "Performing arts", "Music", "Electrical engineering technology",
            "Hospitality", "Engineering Graphics and Design", "English", "First Additional Language",
            "Home language"
    };

    public Manager() {
        setTitle("Student Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load students from file on startup
        students = Data.loadStudents();

        JButton searchTriggerButton = new JButton("Search Students");
        searchTriggerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchComponents();
            }
        });

        JPanel ctrlBtnPnl = new JPanel(new GridLayout(1, 3, 10, 0));
        JButton addStudentButton = new JButton("Add");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        JButton removeStudentButton = new JButton("Remove");
        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        JButton editStudentButton = new JButton("Edit");
        editStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
        ctrlBtnPnl.add(addStudentButton);
        ctrlBtnPnl.add(removeStudentButton);
        ctrlBtnPnl.add(editStudentButton);

        DefaultTableModel model = new DefaultTableModel();
        JTable studentTable = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Number", "Name", "Subject", "Grade", "Average Grade", "Grade Symbol"});
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        setLayout(new BorderLayout());
        add(searchTriggerButton, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(ctrlBtnPnl, BorderLayout.SOUTH);

        // Populate table with initial student data
        refreshTable();
    }

    private void showSearchComponents() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setSize(500, 50);
        searchInput = new JTextField("", 30);
        searchStudentButton = new JButton("Search");
        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent(searchInput.getText().trim());
            }
        });
        searchPanel.add(new JLabel("Search"), BorderLayout.WEST);
        searchPanel.add(searchInput, BorderLayout.CENTER);
        searchPanel.add(searchStudentButton, BorderLayout.EAST);

        getContentPane().remove(getComponent(BorderLayout.NORTH));
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private void addStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name:");
        if (studentName != null && !studentName.trim().isEmpty()) {
            Student newStudent = new Student(studentName);
            boolean addingSubjects = true;
            while (addingSubjects) {
                JComboBox<String> subjectComboBox = new JComboBox<>(SUBJECTS);
                int result = JOptionPane.showConfirmDialog(null, subjectComboBox, "Select Subject", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String subject = (String) subjectComboBox.getSelectedItem();
                    try {
                        int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
                        newStudent.addSubjectGrade(subject, grade);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
                    }
                } else {
                    addingSubjects = false;
                }
            }
            students.add(newStudent);
            Data.saveStudents(students); // Save updated student list
            refreshTable();
        }
    }

    private void removeStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name to Remove:");
        if (studentName != null && !studentName.trim().isEmpty()) {
            students.removeIf(student -> student.getName().equalsIgnoreCase(studentName));
            Data.saveStudents(students); // Save updated student list
            refreshTable();
        }
    }

    private void editStudent() {
        String studentName = JOptionPane.showInputDialog("Enter Student Name to Edit:");
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                boolean editingSubjects = true;
                while (editingSubjects) {
                    JComboBox<String> subjectComboBox = new JComboBox<>(SUBJECTS);
                    int result = JOptionPane.showConfirmDialog(null, subjectComboBox, "Select Subject", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String subject = (String) subjectComboBox.getSelectedItem();
                        try {
                            int grade = Integer.parseInt(JOptionPane.showInputDialog("Enter Grade for " + subject + ":"));
                            student.addSubjectGrade(subject, grade);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid grade entered. Please enter a valid number.");
                        }
                    } else {
                        editingSubjects = false;
                    }
                }
                Data.saveStudents(students); // Save updated student list
                refreshTable();
                return;
            }
        }
    }

    private void searchStudent(String studentName) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                result.add(student);
            }
        }
        refreshTable(result);
    }

    private void refreshTable() {
        refreshTable(students);
    }

    private void refreshTable(List<Student> studentList) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Number", "Name", "Subject", "Grade", "Average Grade", "Grade Symbol"});
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            List<String> subjects = student.getSubjects();
            List<Integer> grades = student.getGrades();
            for (int j = 0; j < subjects.size(); j++) {
                String[] rowData = new String[6];
                rowData[0] = String.valueOf(i + 1);
                rowData[1] = (j == 0) ? student.getName() : ""; // Name only in the first row of each student
                rowData[2] = subjects.get(j);
                rowData[3] = String.valueOf(grades.get(j));
                rowData[4] = (j == 0) ? String.valueOf(student.getAverageGrade()) : ""; // Average grade only in the first row of each student
                rowData[5] = (j == 0) ? student.getAverageGradeSymbol() : ""; // Grade symbol only in the first row of each student
                model.addRow(rowData);
            }
        }
        JTable studentTable = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        getContentPane().remove(getComponent(BorderLayout.CENTER));
        getContentPane().add(tableScrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        Manager mng = new Manager();
        mng.setVisible(true);
    }
}
