package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.sql.*;
import java.util.*;

public class viewAdmins {

    private Connection conn;

    public viewAdmins() {
        JFrame frame = new JFrame("View Admins");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        
        conn = dbConnection.connection();

        if (conn == null) {
            JOptionPane.showMessageDialog(frame, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        JTable table = new JTable(fetchAdminData(), new String[]{"First Name","Last Name", "Username"});
        JScrollPane scrollPane = new JScrollPane(table);
        JButton backButton = new JButton("Back");

        
        backButton.setBackground(Color.black); 
        backButton.setForeground(Color.WHITE); 
        backButton.setOpaque(true); 
        backButton.setBorderPainted(false); 

        
        scrollPane.setBounds(50, 50, 500, 300); 
        backButton.setBounds(50, 370, 100, 30); 

        
        backButton.addActionListener(e -> {
            new higherAdminDashboard(); 
            frame.setVisible(false);
        });

        
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

        
        panel.add(scrollPane);
        panel.add(backButton);

        
        frame.add(panel);

        
        frame.setSize(600, 500); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private Object[][] fetchAdminData() {
        List<Object[]> adminData = new ArrayList<>();
        String query = "SELECT * FROM admin";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                adminData.add(new Object[]{
                        
                    
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("username")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminData.toArray(new Object[0][0]);
    }
}
