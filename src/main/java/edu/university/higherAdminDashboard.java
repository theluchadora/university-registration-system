package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;


public class higherAdminDashboard {

    

    public higherAdminDashboard() {
        JFrame frame = new JFrame("Higher Admin Dashboard");

        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

           
    ImageIcon originalIcon1 = new ImageIcon(getClass().getClassLoader().getResource("icons/add-admin.png")); 
    Image img1 = originalIcon1.getImage(); 
    Image scaledImg1 = img1.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
    ImageIcon resizedIcon1 = new ImageIcon(scaledImg1); 

    JLabel iconLabel1 = new JLabel(resizedIcon1);
    iconLabel1.setBounds(70, 30, 100, 50); 
    

    ImageIcon originalIcon2 = new ImageIcon(getClass().getClassLoader().getResource("icons/people.png")); 
    Image img2 = originalIcon2.getImage(); 
    Image scaledImg2 = img2.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
    ImageIcon resizedIcon2 = new ImageIcon(scaledImg2); 

    JLabel iconLabel2 = new JLabel(resizedIcon2);
    iconLabel2.setBounds(270, 30, 100, 50); 
    

    ImageIcon originalIcon3 = new ImageIcon(getClass().getClassLoader().getResource("icons/operational-system.png")); 
    Image img3 = originalIcon3.getImage(); 
    Image scaledImg3 = img3.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
    ImageIcon resizedIcon3 = new ImageIcon(scaledImg3); 

    JLabel iconLabel3 = new JLabel(resizedIcon3);
    iconLabel3.setBounds(470, 30, 100, 50); 

        JButton addAdminButton = new JButton("Add Admin");
        JButton viewAdminsButton = new JButton("View Admins");
        JButton operationsButton = new JButton("Operations");
        JButton logoutButton = new JButton("Logout");

        addAdminButton.setBounds(50, 90, 150, 30);
        viewAdminsButton.setBounds(250, 90, 150, 30);
        operationsButton.setBounds(450, 90, 150, 30);
        logoutButton.setBounds(230, 190, 200, 30);

        

        addAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addAdmin();
                frame.setVisible(false);
            }
        });

        viewAdminsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewAdmins();
                frame.setVisible(false);
            }
        });

        operationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new higherAdminOperations();
                frame.setVisible(false);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcome();
                frame.setVisible(false);
            }
        });

        
        panel.add(iconLabel1);
        panel.add(iconLabel2);
        panel.add(iconLabel3);
        panel.add(addAdminButton);
        panel.add(viewAdminsButton);
        panel.add(operationsButton);
        panel.add(logoutButton);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu(" ☰ "); 

       
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new welcome();
                frame.setVisible(false);
            }
        });

        menu.add(logoutMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        
        frame.add(panel);

        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}