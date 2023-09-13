import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddGradePage {
    JFrame frame;
    JPanel leftPanel, upperPanel, mainPanel;
    JLabel infoLabel, descLabel;
    JButton menuButton, addStudentButton, editStudentButton, removeStudentButton, studentListButton, gradesButton, editGradesButton, exitButton;
    JButton addButton, closeButton;
    JTextField indexTextField, gradeTextField, descriptionTextField;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 22);
    JTable studentTable;
    JScrollPane tableScrollPane;

    AddGradePage() {
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
        infoLabel = new JLabel("Add grade");
        infoLabel.setBounds(40, 20, 200, 50);
        infoLabel.setForeground(new Color(1, 56, 128));
        Font infoFont = new Font("Comic Sans MS", Font.BOLD, 30);
        infoLabel.setFont(infoFont);
        mainPanel.add(infoLabel);

        studentTable = new JTable();
        tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBounds(30, 80, 630, 200);
        mainPanel.add(tableScrollPane);

        descLabel = new JLabel("Index number:");
        descLabel.setBounds(50, 280, 150, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        indexTextField = new JTextField();
        indexTextField.setBounds(200, 285, 200, 40);
        indexTextField.setForeground(Color.BLACK);
        indexTextField.setFont(appFont);
        mainPanel.add(indexTextField);

        descLabel = new JLabel("Grade:");
        descLabel.setBounds(50, 325, 70, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        gradeTextField = new JTextField();
        gradeTextField.setBounds(120, 330, 200, 40);
        gradeTextField.setForeground(Color.BLACK);
        gradeTextField.setFont(appFont);
        mainPanel.add(gradeTextField);

        descLabel = new JLabel("Description:");
        descLabel.setBounds(50, 370, 120, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(170, 375, 200, 40);
        descriptionTextField.setForeground(Color.BLACK);
        descriptionTextField.setFont(appFont);
        mainPanel.add(descriptionTextField);

        addButton = new JButton("Add");
        addButton.setLayout(null);
        addButton.setBounds(450, 300, 200, 50);
        addButton.setBackground(new Color(1, 56, 128));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(appFont);
        mainPanel.add(addButton);

        closeButton = new JButton("Close");
        closeButton.setLayout(null);
        closeButton.setBounds(450, 360, 200, 50);
        closeButton.setBackground(new Color(1, 56, 128));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(appFont);
        mainPanel.add(closeButton);
    }

    private void displayData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String query = "SELECT groupNumber, surname, name, indexNumber FROM students ORDER BY groupNumber, surname";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Group");
            model.addColumn("Surname");
            model.addColumn("Name");
            model.addColumn("Index Number");

            while (resultSet.next()) {
                int groupNumber = resultSet.getInt("groupNumber");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                int indexNumber = resultSet.getInt("indexNumber");

                model.addRow(new Object[]{groupNumber, surname, name, indexNumber});
            }

            studentTable.setModel(model);

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
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = studentTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        Object indexNumberObj = studentTable.getValueAt(selectedRow, 3);
                        if (indexNumberObj != null) {
                            String indexNumber = indexNumberObj.toString();
                            indexTextField.setText(indexNumber);
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
                String index = indexTextField.getText();
                String grade = gradeTextField.getText();
                String description = descriptionTextField.getText();

                if (index.isEmpty() || grade.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }

                int indexNumber;
                try {
                    indexNumber = Integer.parseInt(index);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for index number. Please enter a valid integer.");
                    return;
                }

                if (!grade.matches("\\d+\\.\\d+")) {
                    JOptionPane.showMessageDialog(null, "Invalid grade format (X.X).");
                    return;
                }

                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");

                    // Retrieve the student's ID based on the index number
                    String selectQuery = "SELECT id FROM students WHERE indexNumber = ?";
                    preparedStatement = connection.prepareStatement(selectQuery);
                    preparedStatement.setInt(1, indexNumber);
                    resultSet = preparedStatement.executeQuery();

                    int studentId = -1; // Default value
                    if (resultSet.next()) {
                        studentId = resultSet.getInt("id");
                    } else {
                        JOptionPane.showMessageDialog(null, "Student with index number " + indexNumber + " not found.");
                        return;
                    }

                    // Insert the grade into the grades table
                    String insertQuery = "INSERT INTO grades (student, grade, description) VALUES (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, studentId);
                    preparedStatement.setString(2, grade);
                    preparedStatement.setString(3, description);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Grade added successfully!");
                        frame.dispose();
                        new GradesPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add grade.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new EditGradesPage();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        AddGradePage app = new AddGradePage();
    }
}
