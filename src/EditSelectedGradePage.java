import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditSelectedGradePage {
    JFrame frame;
    JPanel leftPanel, upperPanel, mainPanel;
    JLabel infoLabel, descLabel, indexLabel;
    JButton menuButton, addStudentButton, editStudentButton, removeStudentButton, studentListButton, gradesButton, editGradesButton, exitButton;
    JButton saveButton, closeButton;
    JTextField gradeTextField, descriptionTextField;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 22);

    int id;

    EditSelectedGradePage(int selectedId) {
        id = selectedId;

        initializeFrame();
        addComponents();
        populateFieldsWithStudentData(id);
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

        descLabel = new JLabel("Index:");
        descLabel.setBounds(150, 80, 70, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        indexLabel = new JLabel();
        indexLabel.setBounds(230, 80, 200, 50);
        indexLabel.setForeground(new Color(0, 32, 74));
        indexLabel.setFont(appFont);
        mainPanel.add(indexLabel);

        descLabel = new JLabel("Grade:");
        descLabel.setBounds(150, 130, 100, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        gradeTextField = new JTextField();
        gradeTextField.setBounds(250, 135, 200, 40);
        gradeTextField.setForeground(Color.BLACK);
        gradeTextField.setFont(appFont);
        mainPanel.add(gradeTextField);

        descLabel = new JLabel("Description:");
        descLabel.setBounds(150, 180, 150, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(300, 185, 200, 40);
        descriptionTextField.setForeground(Color.BLACK);
        descriptionTextField.setFont(appFont);
        mainPanel.add(descriptionTextField);

        saveButton = new JButton("Save");
        saveButton.setLayout(null);
        saveButton.setBounds(450, 300, 200, 50);
        saveButton.setBackground(new Color(1, 56, 128));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(appFont);
        mainPanel.add(saveButton);

        closeButton = new JButton("Close");
        closeButton.setLayout(null);
        closeButton.setBounds(450, 365, 200, 50);
        closeButton.setBackground(new Color(1, 56, 128));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(appFont);
        mainPanel.add(closeButton);
    }

    private void populateFieldsWithStudentData(int idNumber) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String query = "SELECT students.indexNumber, grades.grade, grades.description FROM students JOIN grades ON students.id = grades.student WHERE grades.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String indexNumber = resultSet.getString("indexNumber");
                String grade = resultSet.getString("grade");
                String description = resultSet.getString("description");

                indexLabel.setText(indexNumber);
                gradeTextField.setText(grade);
                descriptionTextField.setText(description);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
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

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String updatedGrade = gradeTextField.getText();
                String updatedDescription = descriptionTextField.getText();

                if (updatedGrade.isEmpty() || updatedDescription.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Grade and description fields must not be empty.");
                    return;
                }

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
                    String updateQuery = "UPDATE grades SET grade = ?, description = ? WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                    preparedStatement.setString(1, updatedGrade);
                    preparedStatement.setString(2, updatedDescription);
                    preparedStatement.setInt(3, id);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Grade updated successfully.");
                        frame.dispose();
                        new GradesPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update grade.");
                    }

                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
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
    }
}
