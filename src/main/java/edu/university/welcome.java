package edu.university;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class welcome {
    public welcome() {
        JFrame frame = new JFrame("Welcome Page");
        
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null); 

        
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/university.png")); 
        Image img = originalIcon.getImage(); 
        Image scaledImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(scaledImg); 

        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setBounds(150, 20, 70, 70); 

       
        JLabel welcomeLabel = new JLabel("Welcome to Sebastopol University");
        welcomeLabel.setBounds(70, 80, 240, 30); 
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); 

        JButton loginButton = new JButton("Login");
        JButton aboutUsButton = new JButton("About Us");

           
    loginButton.setBackground(Color.GREEN); 
    loginButton.setForeground(Color.WHITE); 
    loginButton.setOpaque(true); 
    loginButton.setBorderPainted(false); 

       
       aboutUsButton.setBackground(Color.black); 
       aboutUsButton.setForeground(Color.WHITE); 
       aboutUsButton.setOpaque(true); 
       aboutUsButton.setBorderPainted(false); 

        
        loginButton.setBounds(90, 120, 200, 30); 
        aboutUsButton.setBounds(90, 170, 200, 30);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                frame.setVisible(false);
            }
        });

        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aboutUs();
                frame.setVisible(false);
            }
        });

        
        panel.add(iconLabel);
        panel.add(welcomeLabel);
        panel.add(loginButton);
        panel.add(aboutUsButton);
        
        
        frame.add(panel);
        
        
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false);
        frame.setVisible(true);
    }

    
}