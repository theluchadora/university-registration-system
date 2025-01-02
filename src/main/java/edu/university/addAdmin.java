package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addAdmin {

    private Connection conn;

    public addAdmin() {
        JFrame frame = new JFrame("Add Admin");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        JLabel firstnameLabel = new JLabel("First Name:");
        JTextField firstnameField = new JTextField();
        JLabel lastnameLabel = new JLabel("Last Name:");
        JTextField lastnameField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton insertButton = new JButton("Insert");
        JButton backButton = new JButton("Back");

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

       
        insertButton.setBounds(50, 250, 100, 30);
        backButton.setBounds(160, 250, 100, 30);

       
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = firstnameField.getText().trim();
                String lastname = lastnameField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                
                if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    insertAdmin(firstname, lastname, username, password);
                }
            }
        });

        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new higherAdminDashboard(); 
                frame.setVisible(false);
            }
        });

        
        firstnameLabel.setBounds(50, 50, 100, 30);
        firstnameField.setBounds(150, 50, 150, 30);
        lastnameLabel.setBounds(50, 100, 100, 30);
        lastnameField.setBounds(150, 100, 150, 30);
        usernameLabel.setBounds(50, 150, 100, 30);
        usernameField.setBounds(150, 150, 150, 30);
        passwordLabel.setBounds(50, 200, 100, 30);
        passwordField.setBounds(150, 200, 150, 30);

        
        conn = dbConnection.connection();

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

        
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(e -> {
            new higherAdminDashboard(); 
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

        
        panel.add(firstnameLabel);
        panel.add(firstnameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(insertButton);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }


    private boolean usernameExists(String username) {
    String query = "SELECT COUNT(*) FROM admin WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; 
    }

    private boolean isValidName(String input) {
        return input != null && input.matches("[a-zA-Z ]+"); 
    }
    private boolean isValidPassword(String password) {
        return password != null && password.matches("\\d{4}"); 
    }

    private void insertAdmin(String firstname, String lastname, String username, String password) {

        if (usernameExists(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists! Please choose a different one.");
            return; 
         }
         if (!username.startsWith("A/")) {
            JOptionPane.showMessageDialog(null, "Username must start with 'A/'.");
            return; 
        }
        if (!isValidName(firstname)) {
            JOptionPane.showMessageDialog(null, "First Name must contain only alphabets.");
            return;
           }
           if (!isValidName(lastname)) {
            JOptionPane.showMessageDialog(null, "Last Name must contain only alphabets.");
            return;
           }

        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must contain exactly 4 digits.");
            return;
           }
        String query = "INSERT INTO admin (fname, lname, username, password) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, username);
            stmt.setString(4, password);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Admin added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding admin!");
        }
    }
}