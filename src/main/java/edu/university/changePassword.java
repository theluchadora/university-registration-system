package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class changePassword {

    private Connection conn;

    public changePassword(String username) {
        JFrame frame = new JFrame("Change Password");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JLabel newPasswordLabel = new JLabel("New Password:");
        JLabel repeatPasswordLabel = new JLabel("Repeat New Password:");

        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField repeatPasswordField = new JPasswordField();

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back");

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        oldPasswordLabel.setBounds(50, 50, 100, 30);
        newPasswordLabel.setBounds(50, 100, 100, 30);
        repeatPasswordLabel.setBounds(50, 150, 150, 30);
        oldPasswordField.setBounds(200, 50, 200, 30);
        newPasswordField.setBounds(200, 100, 200, 30);
        repeatPasswordField.setBounds(200, 150, 200, 30);
        updateButton.setBounds(90, 200, 100, 30);
        backButton.setBounds(200, 200, 100, 30);

        
        conn = dbConnection.connection();

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

        
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(e -> {
            new studentDashboard(username); 
            frame.setVisible(false);
        });

        
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(e -> {
            new welcome(); 
            frame.setVisible(false);
        });

        
        menu.add(homeMenuItem);
        menu.add(logoutMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar); 

        
        updateButton.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword()).trim();
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String repeatPassword = new String(repeatPasswordField.getPassword()).trim();

            
            if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!newPassword.equals(repeatPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!");
            } else if (!isValidPassword(newPassword)) {
                JOptionPane.showMessageDialog(frame, "New password must be exactly 4 digits!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                changePassword(username, oldPassword, newPassword);
            }
        });

        
        backButton.addActionListener(e -> {
            new studentDashboard(username); 
            frame.setVisible(false);
        });

        
        panel.add(oldPasswordLabel);
        panel.add(newPasswordLabel);
        panel.add(repeatPasswordLabel);
        panel.add(oldPasswordField);
        panel.add(newPasswordField);
        panel.add(repeatPasswordField);
        panel.add(updateButton);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private void changePassword(String username, String oldPassword, String newPassword) {
        String verifyQuery = "SELECT password FROM student WHERE username = ?";

        try (PreparedStatement verifyStmt = conn.prepareStatement(verifyQuery)) {
            verifyStmt.setString(1, username);
            ResultSet rs = verifyStmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                
                if (storedPassword.equals(oldPassword)) {
                    
                    String updateQuery = "UPDATE student SET password = ? WHERE username = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, newPassword);
                        updateStmt.setString(2, username);
                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "Password changed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to change password.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Old password is incorrect!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private boolean isValidPassword(String password) {
        return password.matches("\\d{4}"); 
    }
}