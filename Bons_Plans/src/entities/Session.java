/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import gui.LoginController;

/**
 *
 * @author DADOU
 */
 
public class Session 
{
    private StackPane rootPane;
    public static String Vnom, Vprenom;
    public static User user;
    
    @FXML
    public static String username;
    
    public static String password;
    private String nom;

    public Session() 
    {

    }

//    public String getname() {
//        return nom;
//    }

    @FXML
    public void Al(String txt) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information Dialog");
        alert.setContentText(txt);
        alert.showAndWait();
    }

    public void Loginn(String usr, String pass) throws ClassNotFoundException, SQLException, InstantiationException 
    {
        try 
        {
            Connection connection;
            PreparedStatement ps;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bonsplans", "root", "");
            ps = connection.prepareStatement("SELECT `id`, `Email`, `test` ,`nom` , `username` FROM `user1` WHERE `email` = ? AND `password` = ? ");
            ps.setString(1, String.valueOf(usr));
            ps = connection.prepareStatement("SELECT `id`, `Email`, `Roles` ,`nom` , `username`, phone FROM `user1` WHERE `email` = ? AND `password` = ? ");
            ps.setString(1, String.valueOf(usr));
            ps.setString(2, String.valueOf(pass));
            ResultSet result = ps.executeQuery();
            if (result.next()) 
            {
                user = new User();
                user.setId(result.getInt("id"));
                user.setNom(result.getString("nom"));
                user.setEmail(result.getString("email"));
                user.setLogin(result.getString("username"));
                user.setTel(result.getInt("phone"));
                LoginController in = new LoginController();
                String mr = result.getString("Roles");
                switch (mr) 
                { 
                    case "Admin":
                        in.setStage("BackendAdminAcceuil.fxml");
                        Al("Hello Admin!");
                        break;   
                        
                    case "Membre":
                        in.setStage("Profile.fxml");
                        Al("Hello Membre!");
                        break;
                        
                    case "Membre professionnel":
                        break;
                }
            } 
            else 
            {
                //   jLabel_Message.setText("Invalide Username Or Password");
                Al("Failed :(");
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}   
    
         




