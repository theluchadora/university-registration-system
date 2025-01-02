package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.sql.*;
import java.util.*;

public class viewStudents {

    private Connection conn;

    public viewStudents() {
        JFrame frame = new JFrame("View Students");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        
        conn = dbConnection.connection();

        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        JTable table = new JTable(fetchStudentData(), new String[]{"First Name", "Last Name", "Username", "Department", "Phone Number", "Bank Account"});
        JScrollPane scrollPane = new JScrollPane(table);
        JButton backButton = new JButton("Back");

        
        scrollPane.setBounds(50, 50, 500, 300); 
        backButton.setBounds(50, 370, 100, 30); 

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
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

        
        backButton.addActionListener(e -> {
            new adminDashboard(); 
            frame.setVisible(false);
        });

        
        panel.add(scrollPane);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(600, 500); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private Object[][] fetchStudentData() {
        List<Object[]> studentData = new ArrayList<>();
        String query = "SELECT * FROM student";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                studentData.add(new Object[]{
                    
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("department"),
                        rs.getString("phone_number"),
                        rs.getString("bankacc")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentData.toArray(new Object[0][0]);
    }
}
