package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.*;

public class adminOperations {

    private Connection conn;

    public adminOperations() {
        JFrame frame = new JFrame("Admin Operations");

       
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel firstnameLabel = new JLabel("First Name:");
        JTextField firstnameField = new JTextField();
        
        JLabel lastnameLabel = new JLabel("Last Name:");
        JTextField lastnameField = new JTextField();
        
        JLabel departmentLabel = new JLabel("Department:");
        String[] dep = {"computer science ", "Mechanical Engineering", "Eletrical Engineering","Biology","Mathematics"};
        JComboBox<String> departmentComboBox = new JComboBox<>(dep);
        
        JLabel genderLabel = new JLabel("Gender:");
        String[] genders = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        
        JLabel bankAccountLabel = new JLabel("Bank Account:");
        JTextField bankAccountField = new JTextField();

        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

        
        removeButton.setBackground(Color.RED); 
        removeButton.setForeground(Color.WHITE); 
        removeButton.setOpaque(true); 
        removeButton.setBorderPainted(false); 

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        usernameLabel.setBounds(50, 20, 100, 30);
        usernameField.setBounds(150, 20, 150, 30);
        
        passwordLabel.setBounds(50, 60, 100, 30);
        passwordField.setBounds(150, 60, 150, 30);
        

        firstnameLabel.setBounds(50, 100, 100, 30);
        firstnameField.setBounds(150, 100, 150, 30);
        
        lastnameLabel.setBounds(50, 140, 100, 30);
        lastnameField.setBounds(150, 140, 150, 30);
        
        departmentLabel.setBounds(50, 180, 100, 30);
        departmentComboBox.setBounds(150, 180, 150, 30);
        
        genderLabel.setBounds(50, 220, 100, 30);
        genderComboBox.setBounds(150, 220, 150, 30);
        
        phoneLabel.setBounds(50, 260, 100, 30);
        phoneField.setBounds(150, 260, 150, 30);
        
        bankAccountLabel.setBounds(50, 300, 100, 30);
        bankAccountField.setBounds(150, 300, 150, 30);

        searchButton.setBounds(70, 340, 100, 30);
        updateButton.setBounds(180, 340, 100, 30);
        removeButton.setBounds(180, 380, 100, 30);
        backButton.setBounds(70, 380, 100, 30);

        
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

        
        searchButton.addActionListener(e -> {
            String username = usernameField.getText().trim();

            if(!username.isEmpty()){
            searchStudent(username, firstnameField, lastnameField, departmentComboBox, genderComboBox, phoneField, bankAccountField);
            }else {JOptionPane.showMessageDialog(null, "please enter the username");}
        });

        
        updateButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String firstName = firstnameField.getText().trim();
            String lastName = lastnameField.getText().trim();
            String department = (String) departmentComboBox.getSelectedItem();
            String gender = (String) genderComboBox.getSelectedItem();
            String phone = phoneField.getText().trim();
            String bankAccount = bankAccountField.getText().trim();

            
            if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || 
                department.isEmpty() || phone.isEmpty() || bankAccount.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                updateStudentInfo(username, password, firstName, lastName, department, gender, phone, bankAccount);
            }
        });

        
        removeButton.addActionListener(e -> {

            String username = usernameField.getText().trim();


            if(!username.isEmpty()){
            int response = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to remove this student?", 
            "Confirm Removal", 
            JOptionPane.YES_NO_OPTION);
        
          if (response == JOptionPane.YES_OPTION) {
            removeStudent(username);
          } else {
            JOptionPane.showMessageDialog(null, "Removal cancelled.");
          }
          
        }else {JOptionPane.showMessageDialog(null, "please enter the username");}

        });

        
        backButton.addActionListener(e -> {
            new adminDashboard(); 
            frame.setVisible(false);
        });

        
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(removeButton);
        panel.add(backButton);
        panel.add(firstnameLabel);
        panel.add(firstnameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(departmentLabel);
        panel.add(departmentComboBox);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(bankAccountLabel);
        panel.add(bankAccountField);

        
        frame.add(panel);

        
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    
    private void searchStudent(String username, JTextField firstnameField, JTextField lastnameField, 
    JComboBox<String>  departmentComboBox, JComboBox<String> genderComboBox, 
                               JTextField phoneField, JTextField bankAccountField) {
        String query = "SELECT * FROM student WHERE username = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String department = rs.getString("department");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone_number");
                String bankAccount = rs.getString("bankacc");

                firstnameField.setText(firstname);
                lastnameField.setText(lastname);
                departmentComboBox.setSelectedItem(department);
                genderComboBox.setSelectedItem(gender);
                phoneField.setText(phone);
                bankAccountField.setText(bankAccount);

                JOptionPane.showMessageDialog(null, "Student Found: " + firstname + " " + lastname + " - " + department);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
    private boolean isValidPhoneNumber(String phone) {
    return phone != null && phone.matches("\\d{10}"); 
    }

    
    private boolean isValidBankAccount(String bankAccount) {
    return bankAccount != null && bankAccount.matches("\\d+"); 
    }

    
    private void updateStudentInfo(String username, String password, String firstName, String lastName, 
                                    String department, String gender, String phone, String bankAccount) {

        
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
       
       
       if (!isValidPhoneNumber(phone)) {
        JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits.");
        return; 
       }
    
     
       if (!isValidBankAccount(bankAccount)) {
        JOptionPane.showMessageDialog(null, "Bank account number must contain only digits.");
        return; 
       }
    
        String query = "UPDATE student SET password = ?, firstname = ?, lastname = ?, department = ?, gender = ?, phone_number = ?, bankacc = ? WHERE username = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, department);
            stmt.setString(5, gender);
            stmt.setString(6, phone);
            stmt.setString(7, bankAccount);
            stmt.setString(8, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Student information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update student information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void removeStudent(String username) {
        String query = "DELETE FROM student WHERE username = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Student removed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}