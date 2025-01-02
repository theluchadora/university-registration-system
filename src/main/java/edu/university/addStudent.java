package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addStudent {

    private Connection conn;

    public addStudent() {
        JFrame frame = new JFrame("Add Student");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        JLabel firstnameLabel = new JLabel("First Name:");
        JTextField firstnameField = new JTextField();
        JLabel lastnameLabel = new JLabel("Last Name:");
        JTextField lastnameField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JLabel departmentLabel = new JLabel("Department:");
        JComboBox<String> departmentComboBox = new JComboBox<>(new String[]{"computer science ", "Mechanical Engineering", "Eletrical Engineering","Biology","Mathematics"});;
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
    

        JButton insertButton = new JButton("Insert");
        JButton backButton = new JButton("Back");
        

        insertButton.setBounds(200, 400, 100, 30);
        backButton.setBounds(50, 400, 100, 30);
        
       
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        conn = dbConnection.connection();

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

        
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(e -> {
            new adminDashboard(); 
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

        
        insertButton.addActionListener(e -> {
            String firstname = firstnameField.getText();
            String lastname = lastnameField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String department = (String) departmentComboBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            

            
            insertStudent(firstname, lastname, gender, department, username, password );
        });

        
        backButton.addActionListener(e -> {
            new adminDashboard(); 
            frame.setVisible(false);
        });

        
        firstnameLabel.setBounds(50, 50, 100, 30);
        firstnameField.setBounds(150, 50, 150, 30);
        lastnameLabel.setBounds(50, 100, 100, 30);
        lastnameField.setBounds(150, 100, 150, 30);
        genderLabel.setBounds(50, 150, 100, 30);
        genderComboBox.setBounds(150, 150, 150, 30);
        departmentLabel.setBounds(50, 200, 100, 30);
        departmentComboBox.setBounds(150, 200, 150, 30);
        usernameLabel.setBounds(50, 250, 100, 30);
        usernameField.setBounds(150, 250, 150, 30);
        passwordLabel.setBounds(50, 300, 100, 30);
        passwordField.setBounds(150, 300, 150, 30);
    

        
        panel.add(firstnameLabel);
        panel.add(firstnameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(departmentLabel);
        panel.add(departmentComboBox);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(insertButton);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }


    private boolean usernameExists(String username) {
    String query = "SELECT COUNT(*) FROM student WHERE username = ?";
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

    private void insertStudent(String firstname, String lastname, String gender, String department,
                               String username, String password ) {

        
       if (usernameExists(username)) {
           JOptionPane.showMessageDialog(null, "Username already exists! Please choose a different one.");
           return; 
        }
        if (!username.startsWith("UGR/")) {
            JOptionPane.showMessageDialog(null, "Username must start with 'UGR/'.");
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

        String query = "INSERT INTO student (firstname, lastname, gender, department, username, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, gender);
            stmt.setString(4, department);
            stmt.setString(5, username);
            stmt.setString(6, password);
            

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding student!");
        }
    }
}
