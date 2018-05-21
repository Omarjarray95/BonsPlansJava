
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import techniques.DataSource;
/**
 *
 * @author Ons Ben Othmen
 */
public class UserService{
    private Connection connection;
        private PreparedStatement PS;

    
    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }
   
        public void add(String nom, String prenom) {
        try
        {
        PreparedStatement PS = connection.prepareStatement("Insert Into Personne(nom,prenom) VALUES (?,?)");
        PS.setString(1, nom);
        PS.setString(2, prenom);
        PS.executeUpdate();
        System.out.println("Insertion réussi  ");
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }

    public  void Affiche(int id, String nom, String prenom)
    {
        
        try
        {
        PreparedStatement PS = connection.prepareStatement("SELECT * FROM Personne");
        PS.setInt(1,id);
        PS.setString(2, nom);
        PS.setString(3, prenom);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
//    public User findbynum(String num) {
//        String req = "select * from user where tel =?";
//        User u = null;
//
//        try {
//            
//            PS = connection.prepareStatement(req);
//            PS.setString(1, num);
//            ResultSet rs = PS.executeQuery();
//            while (rs.next()) {
//                u = new User (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8));
//                u.setDTYPE(rs.getString("Role"));
//                u.setID(rs.getInt("ID"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return u;
//    }
    
    }

