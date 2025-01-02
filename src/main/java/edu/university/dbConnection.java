package edu.university;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class dbConnection {
    
    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    final static String DB_URL ="jdbc:mysql://localhost:3306/universityregistration";
    
    final static String USER = "root";
    final static String PASS ="";
    
    public static Connection connection(){
        
        try{
           Class.forName(JDBC_DRIVER);
           
           Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
           
           return conn;
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e);
            return null;
            
        }
        
  
   }

    public static Connection getConnection() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
    }
}

