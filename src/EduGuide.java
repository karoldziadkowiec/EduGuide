import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EduGuide {
    JFrame frame;
    JPanel mainPanel;
    JLabel logoLabel;
    JButton openButton, exitButton;
    Font appFont = new Font("Arial", Font.TRUETYPE_FONT, 26);

    EduGuide() {
        initializeFrame();
        openNewWindow();
        frame.setVisible(true);
    }

    private void initializeFrame() {
        frame = new JFrame("EduGuide");
        frame.setLayout(null);
        frame.setSize(500,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,900,550);
        frame.add(mainPanel);

        logoLabel = new JLabel("EduGuide");
        logoLabel.setBounds(125, 40, 350, 70);
        logoLabel.setForeground(new Color(0, 93, 215));
        Font logoFont = new Font("Comic Sans MS", Font.BOLD, 60);
        logoLabel.setFont(logoFont);
        mainPanel.add(logoLabel);

        ImageIcon logoIcon = new ImageIcon("icon.png");
        JLabel logoImageLabel = new JLabel(logoIcon);
        logoImageLabel.setBounds(150, 120, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        mainPanel.add(logoImageLabel);

        openButton = new JButton("OPEN");
        openButton.setLayout(null);
        openButton.setBounds(160, 350, 180, 55);
        openButton.setBackground(new Color(0, 93, 215));
        openButton.setForeground(Color.WHITE);
        openButton.setFont(appFont);
        mainPanel.add(openButton);

        exitButton = new JButton("EXIT");
        exitButton.setLayout(null);
        exitButton.setBounds(160, 420, 180, 55);
        exitButton.setBackground(new Color(0, 93, 215));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(appFont);
        mainPanel.add(exitButton);
    }

    private void openNewWindow() {
        openButton.addActionListener(new ActionListener() {
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

    public static void main(String[] args) {
        EduGuide app = new EduGuide();
    }
}
