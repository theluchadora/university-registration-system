package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class viewProfile {

    private Connection conn;

    public viewProfile(String username) {
        JFrame frame = new JFrame("View Profile");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        JLabel fullNameLabel = new JLabel("Full Name:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel phoneLabel = new JLabel("Phone Number:");

        JLabel fullNameValue = new JLabel();
        JLabel usernameValue = new JLabel(username); 
        JLabel departmentValue = new JLabel();
        JLabel phoneValue = new JLabel();

        JButton backButton = new JButton("Back");

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        fullNameLabel.setBounds(50, 50, 100, 30);
        usernameLabel.setBounds(50, 100, 100, 30);
        departmentLabel.setBounds(50, 150, 100, 30);
        phoneLabel.setBounds(50, 200, 100, 30);

        fullNameValue.setBounds(150, 50, 200, 30);
        usernameValue.setBounds(150, 100, 200, 30);
        departmentValue.setBounds(150, 150, 200, 30);
        phoneValue.setBounds(150, 200, 200, 30);

        backButton.setBounds(150, 250, 100, 30);

        
        conn = dbConnection.connection();

        
        fetchProfile(username, fullNameValue, departmentValue, phoneValue);

        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new studentDashboard(username);
                frame.setVisible(false);
            }
        });

        
        panel.add(fullNameLabel);
        panel.add(usernameLabel);
        panel.add(departmentLabel);
        panel.add(phoneLabel);
        panel.add(fullNameValue);
        panel.add(usernameValue);
        panel.add(departmentValue);
        panel.add(phoneValue);
        panel.add(backButton);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

        
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new studentDashboard(username);
                frame.setVisible(false);
            }
        });

        
        JMenuItem aboutUsMenuItem = new JMenuItem("About Us");
        aboutUsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aboutUs(username); 
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

       
        menu.add(homeMenuItem);
        menu.add(aboutUsMenuItem);
        menu.add(logoutMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar); 

       
        frame.add(panel);

        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private void fetchProfile(String username, JLabel fullNameValue, JLabel departmentValue, JLabel phoneValue) {
        String query = "SELECT firstname, lastname, department, phone_number FROM student WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("firstname") + " " + rs.getString("lastname");
                fullNameValue.setText(fullName);
                departmentValue.setText(rs.getString("department"));
                phoneValue.setText(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
