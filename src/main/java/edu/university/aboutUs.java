package edu.university;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class aboutUs {
    
    public aboutUs() {
        createAboutUsFrame(null);
    }

    
    public aboutUs(String username) {
        createAboutUsFrame(username);
    }

    
    private void createAboutUsFrame(String username) {
        JFrame frame = new JFrame("About Us");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

         
         ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/university.png")); 
         Image img = originalIcon.getImage(); 
         Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
         ImageIcon resizedIcon = new ImageIcon(scaledImg); 
 
         JLabel iconLabel = new JLabel(resizedIcon);
         iconLabel.setBounds(145, 10, 80, 80);

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setText("Sebastopol University is one of the prestigious \n university in Ethiopia. \n\n\n sebastopol@gmail.com\n+251 11 678 9834");
        aboutTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        scrollPane.setBounds(50, 110, 300, 200);

        JButton backButton = new JButton("Back To Home");
        backButton.setBounds(120, 350, 150, 30);

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true);
        backButton.setBorderPainted(false); 

        backButton.addActionListener(e -> {
            if (username == null) {
                new welcome(); 
            } else {
                new studentDashboard(username);
            }
            frame.setVisible(false);
        });

        
        panel.add(iconLabel);
        panel.add(scrollPane);
        panel.add(backButton);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

        
        if (username == null) {
            JMenuItem backItem = new JMenuItem("Back");
            backItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new welcome();
                    frame.setVisible(false);
                }
            });
            menu.add(backItem);
        } else {
            JMenuItem homeItem = new JMenuItem("Home");
            homeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new studentDashboard(username);
                    frame.setVisible(false);
                }
            });

            JMenuItem logoutMenuItem = new JMenuItem("Logout");
            logoutMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new welcome();
                    frame.setVisible(false);
                }
            });

            menu.add(homeItem);
            menu.add(logoutMenuItem);
        }

        menuBar.add(menu);
        frame.setJMenuBar(menuBar); 

        
        frame.add(panel);

        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
