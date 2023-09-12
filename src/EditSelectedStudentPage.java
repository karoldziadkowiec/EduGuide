import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditSelectedStudentPage {
    JFrame frame;
    JPanel leftPanel, upperPanel, mainPanel;
    JLabel infoLabel, descLabel;
    JButton menuButton, addStudentButton, editStudentButton, removeStudentButton, studentListButton, gradesButton, editGradesButton, exitButton;
    JButton saveButton, closeButton;
    JTextField nameTextField, surnameTextField, indexTextField, groupTextField, emailTextField;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 22);

    int index;

    EditSelectedStudentPage(int selectedIndex) {
        index = selectedIndex;

        initializeFrame();
        addComponents();
        populateFieldsWithStudentData(index);
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
        editStudentButton.setBackground(new Color(0, 32, 74));
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
        editGradesButton.setBackground(new Color(1, 56, 128));
        editGradesButton.setForeground(Color.WHITE);
        editGradesButton.setFont(appFont);
        upperPanel.add(editGradesButton);
    }

    private void addComponents() {
        infoLabel = new JLabel("Edit student");
        infoLabel.setBounds(40, 20, 200, 50);
        infoLabel.setForeground(new Color(1, 56, 128));
        Font infoFont = new Font("Comic Sans MS", Font.BOLD, 30);
        infoLabel.setFont(infoFont);
        mainPanel.add(infoLabel);

        descLabel = new JLabel("Name:");
        descLabel.setBounds(150, 80, 200, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(220, 85, 200, 40);
        nameTextField.setForeground(Color.BLACK);
        nameTextField.setFont(appFont);
        mainPanel.add(nameTextField);

        descLabel = new JLabel("Surname:");
        descLabel.setBounds(150, 130, 200, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(250, 135, 200, 40);
        surnameTextField.setForeground(Color.BLACK);
        surnameTextField.setFont(appFont);
        mainPanel.add(surnameTextField);

        descLabel = new JLabel("Index number:");
        descLabel.setBounds(150, 180, 200, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        indexTextField = new JTextField();
        indexTextField.setBounds(300, 185, 200, 40);
        indexTextField.setForeground(Color.BLACK);
        indexTextField.setFont(appFont);
        mainPanel.add(indexTextField);

        descLabel = new JLabel("Group:");
        descLabel.setBounds(150, 230, 200, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        groupTextField = new JTextField();
        groupTextField.setBounds(225, 235, 200, 40);
        groupTextField.setForeground(Color.BLACK);
        groupTextField.setFont(appFont);
        mainPanel.add(groupTextField);

        descLabel = new JLabel("E-mail:");
        descLabel.setBounds(150, 280, 200, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(225, 285, 200, 40);
        emailTextField.setForeground(Color.BLACK);
        emailTextField.setFont(appFont);
        mainPanel.add(emailTextField);

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

    private void populateFieldsWithStudentData(int index) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String query = "SELECT name, surname, indexNumber, groupNumber, email FROM students WHERE indexNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String indexNumber = resultSet.getString("indexNumber");
                int groupNumber = resultSet.getInt("groupNumber");
                String email = resultSet.getString("email");

                nameTextField.setText(name);
                surnameTextField.setText(surname);
                indexTextField.setText(indexNumber);
                groupTextField.setText(String.valueOf(groupNumber));
                emailTextField.setText(email);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException ex) {
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
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String indexNumberStr = indexTextField.getText();
                String groupStr = groupTextField.getText();
                String email = emailTextField.getText();

                if (name.isEmpty() || surname.isEmpty() || indexNumberStr.isEmpty() || groupStr.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out.");
                    return;
                }

                int indexNumber, groupNumber;
                try {
                    indexNumber = Integer.parseInt(indexNumberStr);
                    groupNumber = Integer.parseInt(groupStr);
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid index or group number.");
                    return;
                }
                if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(null, "Invalid email format. Please enter a valid email address.");
                    return;
                }

                Connection connection = null;
                PreparedStatement preparedStatement = null;

                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
                    String updateQuery = "UPDATE students SET name = ?, surname = ?, indexNumber = ?, groupNumber = ?, email = ? WHERE indexNumber = ?";
                    preparedStatement = connection.prepareStatement(updateQuery);

                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setInt(3, indexNumber);
                    preparedStatement.setInt(4, groupNumber);
                    preparedStatement.setString(5, email);
                    preparedStatement.setInt(6, index);

                    int updatedRows = preparedStatement.executeUpdate();
                    if (updatedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Student information updated successfully.");
                        frame.dispose();
                        new StudentListPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "No rows were updated.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                } finally {
                    try {
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
                new EditStudentPage();
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

