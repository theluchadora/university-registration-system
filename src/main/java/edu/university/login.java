package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Image;

public class login {
    private Connection conn;

    public login() {
        JFrame frame = new JFrame("Login Page");
        
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/user.png")); 
        Image img = originalIcon.getImage(); 
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(scaledImg); 

        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setBounds(160, 10, 50, 50); 

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        
    loginButton.setBackground(Color.GREEN); 
    loginButton.setForeground(Color.WHITE); 
    loginButton.setOpaque(true); 
    loginButton.setBorderPainted(false); 


    backButton.setBackground(Color.black); 
    backButton.setForeground(Color.WHITE); 
    backButton.setOpaque(true); 
    backButton.setBorderPainted(false); 



        
        usernameLabel.setBounds(60, 70, 100, 30);
        usernameField.setBounds(160, 70, 150, 30);
        passwordLabel.setBounds(60, 120, 100, 30);
        passwordField.setBounds(160, 120, 150, 30);
        loginButton.setBounds(60, 170, 100, 30);
        backButton.setBounds(210, 170, 100, 30);

        
        conn = dbConnection.connection();

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

               
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in both fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return; 
                }

                
                try {
                    if (isValidUser(username, password)) {
                        if (username.startsWith("UGR")) {
                            new studentDashboard(username);
                        } else if (username.startsWith("A")) {
                            new adminDashboard();
                        } else if (username.startsWith("HA")) {
                            new higherAdminDashboard();
                        }
                        frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
                    }
                } catch (HeadlessException | SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcome();
                frame.setVisible(false);
            }
        });

        
        panel.add(iconLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);
        
        
        frame.add(panel);
        
        
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private boolean isValidUser(String username, String password) throws SQLException {
        String query = "";
        if (username.startsWith("UGR")) {
            query = "SELECT * FROM student WHERE username = ? AND password = ?";
        } else if (username.startsWith("A")) {
            query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        } else if (username.startsWith("HA")) {
            query = "SELECT * FROM higheradmin WHERE username = ? AND password = ?";
        } else {
            return false; 
        }

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password); 
            
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); 
        }
    }

    
}