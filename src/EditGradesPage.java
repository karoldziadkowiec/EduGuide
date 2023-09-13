import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditGradesPage {
    JFrame frame;
    JPanel leftPanel, upperPanel, mainPanel;
    JLabel infoLabel, descLabel;
    JButton menuButton, addStudentButton, editStudentButton, removeStudentButton, studentListButton, gradesButton, editGradesButton, exitButton;
    JButton addButton, editButton, removeButton, closeButton;
    JTextField gradeTextField;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 22);
    JTable gradesTable;
    JScrollPane tableScrollPane;
    EditGradesPage() {
        initializeFrame();
        addComponents();
        displayData();
        addTableSelectionListener();
        openNewWindow();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("EduGuide");
        frame.setLayout(null);
        frame.setSize(900,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0,70,200,480);
        leftPanel.setBackground(new Color(0, 151, 254));
        frame.add(leftPanel);

        upperPanel = new JPanel();
        upperPanel.setLayout(null);
        upperPanel.setBounds(200,0,700,70);
        upperPanel.setBackground(new Color(1, 56, 128));
        frame.add(upperPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(200,70,900,550);
        frame.add(mainPanel);

        menuButton = new JButton("EduGuide");
        menuButton.setLayout(null);
        menuButton.setBounds(0, 0, 200, 70);
        menuButton.setBackground(new Color(0, 20, 46));
        menuButton.setForeground(Color.WHITE);
        Font menuFont = new Font("Comic Sans MS", Font.BOLD, 34);
        menuButton.setFont(menuFont);
        frame.add(menuButton);

        studentListButton = new JButton("Student list");
        studentListButton.setLayout(null);
        studentListButton.setBounds(0, 0, 200, 70);
        studentListButton.setBackground(new Color(0, 93, 215));
        studentListButton.setForeground(Color.WHITE);
        studentListButton.setFont(appFont);
        leftPanel.add(studentListButton);

        addStudentButton = new JButton("Add student");
        addStudentButton.setLayout(null);
        addStudentButton.setBounds(0, 70, 200, 70);
        addStudentButton.setBackground(new Color(0, 93, 215));
        addStudentButton.setForeground(Color.WHITE);
        addStudentButton.setFont(appFont);
        leftPanel.add(addStudentButton);

        editStudentButton = new JButton("Edit student");
        editStudentButton.setLayout(null);
        editStudentButton.setBounds(0, 140, 200, 70);
        editStudentButton.setBackground(new Color(0, 93, 215));
        editStudentButton.setForeground(Color.WHITE);
        editStudentButton.setFont(appFont);
        leftPanel.add(editStudentButton);

        removeStudentButton = new JButton("Remove student");
        removeStudentButton.setLayout(null);
        removeStudentButton.setBounds(0, 210, 200, 70);
        removeStudentButton.setBackground(new Color(0, 93, 215));
        removeStudentButton.setForeground(Color.WHITE);
        removeStudentButton.setFont(appFont);
        leftPanel.add(removeStudentButton);

        exitButton = new JButton("Exit");
        exitButton.setLayout(null);
        exitButton.setBounds(0, 380, 200, 70);
        exitButton.setBackground(new Color(0, 93, 215));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(appFont);
        leftPanel.add(exitButton);

        gradesButton = new JButton("G r a d e s");
        gradesButton.setLayout(null);
        gradesButton.setBounds(0, 0, 350, 70);
        gradesButton.setBackground(new Color(1, 56, 128));
        gradesButton.setForeground(Color.WHITE);
        gradesButton.setFont(appFont);
        upperPanel.add(gradesButton);

        editGradesButton = new JButton("E d i t   g r a d e s");
        editGradesButton.setLayout(null);
        editGradesButton.setBounds(350, 0, 350, 70);
        editGradesButton.setBackground(new Color(0, 32, 74));
        editGradesButton.setForeground(Color.WHITE);
        editGradesButton.setFont(appFont);
        upperPanel.add(editGradesButton);
    }

    private void addComponents() {
        infoLabel = new JLabel("Edit grades");
        infoLabel.setBounds(40, 20, 200, 50);
        infoLabel.setForeground(new Color(1, 56, 128));
        Font infoFont = new Font("Comic Sans MS", Font.BOLD, 30);
        infoLabel.setFont(infoFont);
        mainPanel.add(infoLabel);

        addButton = new JButton("Add grade");
        addButton.setLayout(null);
        addButton.setBounds(450, 20, 200, 50);
        addButton.setBackground(new Color(1, 56, 128));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(appFont);
        mainPanel.add(addButton);

        gradesTable = new JTable();
        tableScrollPane = new JScrollPane(gradesTable);
        tableScrollPane.setBounds(30, 80, 630, 190);
        mainPanel.add(tableScrollPane);

        descLabel = new JLabel("Grade:");
        descLabel.setBounds(150, 280, 70, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        gradeTextField = new JTextField();
        gradeTextField.setBounds(220, 285, 200, 40);
        gradeTextField.setForeground(Color.BLACK);
        gradeTextField.setFont(appFont);
        mainPanel.add(gradeTextField);

        editButton = new JButton("Edit grade");
        editButton.setLayout(null);
        editButton.setBounds(450, 275, 200, 50);
        editButton.setBackground(new Color(1, 56, 128));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(appFont);
        mainPanel.add(editButton);

        removeButton = new JButton("Remove grade");
        removeButton.setLayout(null);
        removeButton.setBounds(450, 330, 200, 50);
        removeButton.setBackground(new Color(1, 56, 128));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(appFont);
        mainPanel.add(removeButton);

        closeButton = new JButton("Close");
        closeButton.setLayout(null);
        closeButton.setBounds(450, 385, 200, 50);
        closeButton.setBackground(new Color(1, 56, 128));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(appFont);
        mainPanel.add(closeButton);
    }

    private void displayData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String query = "SELECT students.groupNumber, students.surname, students.name, grades.grade, grades.description FROM grades INNER JOIN students ON students.id = grades.student ORDER BY students.surname, grades.description";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Group");
            model.addColumn("Student Name");
            model.addColumn("Grade");
            model.addColumn("Description");

            while (resultSet.next()) {
                String group = resultSet.getString("groupNumber");
                String studentName = resultSet.getString("name") + " " + resultSet.getString("surname");
                String grade = resultSet.getString("grade");
                String description = resultSet.getString("description");

                model.addRow(new Object[]{group, studentName, grade, description});
            }

            gradesTable.setModel(model);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void addTableSelectionListener() {
        gradesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = gradesTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        Object indexNumberObj = gradesTable.getValueAt(selectedRow, 2);
                        if (indexNumberObj != null) {
                            String indexNumber = indexNumberObj.toString();
                            gradeTextField.setText(indexNumber);
                        }
                    }
                }
            }
        });
    }

    private void openNewWindow() {
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPage();
            }
        });

        studentListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StudentListPage();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddStudentPage();
            }
        });

        editStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditStudentPage();
            }
        });

        removeStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RemoveStudentPage();
            }
        });

        gradesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GradesPage();
            }
        });

        editGradesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditGradesPage();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AddGradePage();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String grade = gradeTextField.getText();

                if (grade.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Grade field is empty");
                    return;
                }

                try {
                    int id = getSelectedGradeId();
                    if (id != -1) {
                        frame.dispose();
                        new EditSelectedGradePage(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid grade or no grade selected.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid grade value");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String gradeToRemove = gradeTextField.getText();

                if (gradeToRemove.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select the grade from the table to remove.");
                    return;
                }

                int selectedRow = gradesTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a grade from the table.");
                    return;
                }

                int confirmed = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to remove the selected grade: " + gradeToRemove + "?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmed == JOptionPane.YES_OPTION) {
                    String studentName = (String) gradesTable.getValueAt(selectedRow, 1);
                    String description = (String) gradesTable.getValueAt(selectedRow, 3);

                    removeGrade(studentName, description);
                    displayData();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPage();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void removeGrade(String studentName, String description) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String deleteQuery = "DELETE FROM grades WHERE student = (SELECT id FROM students WHERE CONCAT(name, ' ', surname) = ?) AND description = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, description);

            int deletedRows = preparedStatement.executeUpdate();

            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(null, "Grade removed successfully.");
                frame.dispose();
                new GradesPage();
            } else {
                JOptionPane.showMessageDialog(null, "No grade found.");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private int getSelectedGradeId() {
        int selectedRow = gradesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String studentName = (String) gradesTable.getValueAt(selectedRow, 1);
            String description = (String) gradesTable.getValueAt(selectedRow, 3);

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
                String query = "SELECT id FROM grades WHERE student = (SELECT id FROM students WHERE CONCAT(name, ' ', surname) = ?) AND description = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, studentName);
                preparedStatement.setString(2, description);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int gradeId = resultSet.getInt("id");
                    preparedStatement.close();
                    connection.close();
                    return gradeId;
                }

                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
