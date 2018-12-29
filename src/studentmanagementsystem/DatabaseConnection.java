/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADSI
 */
public class DatabaseConnection {
    private Connection getConnection(){
        Connection con=null;
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean addStudent(Student student){
        Connection con=getConnection();
        PreparedStatement ps=null;
        String Quary="INSERT INTO studentdetails(Fname,Lname,Age,Address,Gender,Faculty,Department,RegYear) values(?,?,?,?,?,?,?,?)";
        try {
            ps=con.prepareStatement(Quary);
            ps.setString(1,student.getFname());
            ps.setString(2,student.getLname());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getAddress());
            ps.setString(5,student.getGender());
            ps.setString(6,student.getFaculty());
            ps.setString(7,student.getDepartment());
            ps.setString(8,student.getRegYear());
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public ArrayList<Student> getData(){
        ArrayList<Student> list=new ArrayList<>();
        Connection con=getConnection();
        String Query="SELECT * FROM studentdetails";
        Statement st;
        ResultSet rs;
        try {
            st=con.createStatement();
            rs=st.executeQuery(Query);
            while(rs.next()){
                Student s=new Student();
                s.setID(rs.getInt(1));
                s.setFname(rs.getString(2));
                s.setLname(rs.getString(3));
                s.setAge(rs.getInt(4));
                s.setAddress(rs.getString(5));
                s.setGender(rs.getString(6));
                s.setFaculty(rs.getString(7));
                s.setDepartment(rs.getString(8));
                s.setRegYear(rs.getString(9));
                list.add(s);
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean DeleteStudent(Student student){
        Connection con=getConnection();
        String DeleteQuery="DELETE FROM studentdetails WHERE Id=?";
        PreparedStatement ps=null;
        try {
            ps=con.prepareStatement(DeleteQuery);
            ps.setInt(1, student.getID());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return  false;
        }
    }
    
}
