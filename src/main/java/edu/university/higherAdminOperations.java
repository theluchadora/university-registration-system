package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class higherAdminOperations {

    private Connection conn;

    public higherAdminOperations() {

        JFrame frame = new JFrame("Higher Admin Operations");
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

        JLabel firstnameLabel = new JLabel("First Name:");
        JTextField firstnameField = new JTextField();
        JLabel lastnameLabel = new JLabel("Last Name:");
        JTextField lastnameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        
        usernameLabel.setBounds(50, 50, 100, 30);
        usernameField.setBounds(150, 50, 150, 30);
        searchButton.setBounds(70, 250, 100, 30);
        updateButton.setBounds(180, 250, 100, 30);
        removeButton.setBounds(180, 300, 100, 30);
        backButton.setBounds(70, 300, 100, 30);

       
        removeButton.setBackground(Color.RED); 
        removeButton.setForeground(Color.WHITE); 
        removeButton.setOpaque(true); 
        removeButton.setBorderPainted(false); 

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        firstnameLabel.setBounds(50, 100, 100, 30);
        firstnameField.setBounds(150, 100, 150, 30);
        lastnameLabel.setBounds(50, 150, 100, 30);
        lastnameField.setBounds(150, 150, 150, 30);
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
        
        
    
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if(!username.isEmpty()){
                searchAdmin(username, firstnameField, lastnameField, passwordField);
                }else {JOptionPane.showMessageDialog(null, "please enter the username");}
            }
        });

        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String firstName = firstnameField.getText();
                String lastName = lastnameField.getText();
                String password = new String(passwordField.getPassword());

                if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
                    updateAdminInformation(username, firstName, lastName, password);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                }
            }
        });

        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                if(!username.isEmpty()){
                int response = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to remove this admin?", 
                "Confirm Removal", 
                JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                removeAdmin(username);
            } else {
                JOptionPane.showMessageDialog(null, "Removal cancelled.");
            }

                
            }else {JOptionPane.showMessageDialog(null, "please enter the username");}
        }
        });

        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new higherAdminDashboard();
                frame.dispose(); 
            }
        });

       
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(removeButton);
        panel.add(backButton);
        panel.add(firstnameLabel);
        panel.add(firstnameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        
        frame.add(panel);

        
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    

    
    private void searchAdmin(String username, JTextField firstnameField, JTextField lastnameField, JPasswordField passwordField) {
        String query = "SELECT * FROM admin WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                firstnameField.setText(rs.getString("fname"));
                lastnameField.setText(rs.getString("lname"));
                passwordField.setText(rs.getString("password"));
            } else {
                JOptionPane.showMessageDialog(null, "Admin not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    
    private void updateAdminInformation(String username, String firstName, String lastName, String password) {

        if (!usernameExists(username)) {
            JOptionPane.showMessageDialog(null, "The username does not exist in the database.");
            return; 
            }
            if (!isValidName(firstName)) {
                JOptionPane.showMessageDialog(null, "First Name must contain only alphabets.");
                return;
               }
               if (!isValidName(lastName)) {
                JOptionPane.showMessageDialog(null, "Last Name must contain only alphabets.");
                return;
               }
    
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password must contain exactly 4 digits.");
                return;
               }

        String query = "UPDATE admin SET fname = ?, lname = ?, password = ? WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, password);
            stmt.setString(4, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Admin information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update admin information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void removeAdmin(String username) {
        String query = "DELETE FROM admin WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Admin removed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Admin not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
