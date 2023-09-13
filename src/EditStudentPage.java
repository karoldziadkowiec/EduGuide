import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditStudentPage {
    JFrame frame;
    JPanel leftPanel, upperPanel, mainPanel;
    JLabel infoLabel, descLabel;
    JButton menuButton, addStudentButton, editStudentButton, removeStudentButton, studentListButton, gradesButton, editGradesButton, exitButton;
    JButton editButton, closeButton;
    JTextField indexTextField;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 22);
    JTable studentTable;
    JScrollPane tableScrollPane;

    EditStudentPage() {
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

        studentTable = new JTable();
        tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setBounds(30, 80, 630, 200);
        mainPanel.add(tableScrollPane);

        descLabel = new JLabel("Index:");
        descLabel.setBounds(130, 300, 70, 50);
        descLabel.setForeground(new Color(1, 56, 128));
        descLabel.setFont(appFont);
        mainPanel.add(descLabel);

        indexTextField = new JTextField();
        indexTextField.setBounds(195, 305, 200, 40);
        indexTextField.setForeground(Color.BLACK);
        indexTextField.setFont(appFont);
        mainPanel.add(indexTextField);

        editButton = new JButton("Edit student");
        editButton.setLayout(null);
        editButton.setBounds(450, 300, 200, 50);
        editButton.setBackground(new Color(1, 56, 128));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(appFont);
        mainPanel.add(editButton);

        closeButton = new JButton("Close");
        closeButton.setLayout(null);
        closeButton.setBounds(450, 365, 200, 50);
        closeButton.setBackground(new Color(1, 56, 128));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(appFont);
        mainPanel.add(closeButton);
    }

    private void displayData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduguide", "root", "");
            String query = "SELECT groupNumber, surname, name, indexNumber, email FROM students ORDER BY groupNumber, surname";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Group");
            model.addColumn("Surname");
            model.addColumn("Name");
            model.addColumn("Index Number");
            model.addColumn("Email");

            while (resultSet.next()) {
                int groupNumber = resultSet.getInt("groupNumber");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                int indexNumber = resultSet.getInt("indexNumber");
                String email = resultSet.getString("email");

                model.addRow(new Object[]{groupNumber, surname, name, indexNumber, email});
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

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String indexText = indexTextField.getText();

                if (indexText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Index field is empty");
                    return;
                }

                int index;

                try {
                    index = Integer.parseInt(indexText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid index number");
                    return;
                }

                if (!isIndexExists(index)) {
                    JOptionPane.showMessageDialog(null, "Index does not exist in the table");
                    return;
                }

                frame.dispose();
                new EditSelectedStudentPage(index);
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

    private boolean isIndexExists(int index) {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            int rowIndex = (int) model.getValueAt(row, 3);
            if (rowIndex == index) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
