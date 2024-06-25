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
