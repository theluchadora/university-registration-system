package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateInformation {

    private Connection conn;

    public updateInformation(String username) {
        JFrame frame = new JFrame("Update Information");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel bankAccountLabel = new JLabel("Bank Account:");

        JTextField phoneField = new JTextField();
        JTextField bankAccountField = new JTextField();

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back");

        
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        phoneLabel.setBounds(50, 50, 100, 30);
        bankAccountLabel.setBounds(50, 100, 100, 30);
        phoneField.setBounds(150, 50, 200, 30);
        bankAccountField.setBounds(150, 100, 200, 30);
        updateButton.setBounds(70, 150, 100, 30);
        backButton.setBounds(190, 150, 100, 30);

       
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
            String phone = phoneField.getText().trim();
            String bankAccount = bankAccountField.getText().trim();

            
            if (phone.isEmpty() || bankAccount.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(frame, "Invalid phone number! It must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updateInformation(username, phone, bankAccount);
            }
        });

        
        backButton.addActionListener(e -> {
            new studentDashboard(username); 
            frame.setVisible(false);
        });

        
        panel.add(phoneLabel);
        panel.add(bankAccountLabel);
        panel.add(phoneField);
        panel.add(bankAccountField);
        panel.add(updateButton);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(400, 260);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private void updateInformation(String username, String phone, String bankAccount) {
        String query = "UPDATE student SET phone_number = ?, bankacc = ? WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phone);
            stmt.setString(2, bankAccount);
            stmt.setString(3, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}"); 
    }
}