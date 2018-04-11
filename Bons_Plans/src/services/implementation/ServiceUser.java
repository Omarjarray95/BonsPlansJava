/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import techniques.DataSource;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.CompteController;
import gui.LoginController;



/**
 *
 * @author DADOU
 */
public class ServiceUser {
    static DataSource ds = new DataSource ();
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    public ServiceUser() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void addUser(User u)  {
       
        
        
        
            String req = "INSERT INTO `user1` (username,email,password,roles,nom,datedenaissance,phone,sex,valid,test)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pre ;
            
            try {
            pre=con.prepareStatement(req);
            pre.setString(1, u.getLogin());
            pre.setString(2, u.getEmail());
            pre.setString(3, u.getPassword());
            pre.setString(4, u.getDTYPE());
            pre.setString(5, u.getNom());
            pre.setDate(6, u.getBirthDate());
            pre.setInt(7, u.getTel());
            pre.setString(8, u.getSex());
            pre.setString(9, u.getValid());
             pre.setString(10, u.getTest());
            
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int deleteUser (User u){
    int status =0;
        try {
            String sql = "DELETE FROM `user1` WHERE id=?";

            PreparedStatement src = ds.getConnection().prepareStatement(sql);

            src.setInt(1, u.getId());
            src.executeUpdate();
            System.out.println("user supprimé");
            status =1;
        } catch (Exception ex) {
            System.out.println("Erreur " + ex);
        }
        return status;}
    
//          public void Loginn(String usr, String pass) throws SQLException{
//              String sql=("SELECT `id`, `Email`, `password`, `test` ,`nom` ,`prenom` FROM `user1` WHERE `email` = ? AND `password` = ? ");
//              PreparedStatement ps = ds.getConnection().prepareStatement(sql);
//            
//            ps.setString(1, String.valueOf(usr));
//
//            ps.setString(2, String.valueOf(pass));
//
//            ResultSet result = ps.executeQuery();
//
//            if (result.next()) {
//
//                user = new User();
//                user.setId(result.getInt("id"));
//                user.setNom(result.getString("nom"));
//                user.setPassword(result.getString("password"));
//                user.setEmail(result.getString("Email"));
//                user.setPrenom(result.getString("prenom"));
//    }
//    
//    
//          }
    
    
    
    
    
    
    
    
    
    
    
    
    
//      public static void deleteUser (int id){
//          
//        try {
//            Statement ste = ds.getConnection().createStatement();
//            
//             String req = "delete FROM user "
//                     +" WHERE id="+id;
//                
//            ste.executeUpdate(req);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
      
      //int id, String Email, String adress, int age, Date birthDate, String login, String nom, String password, String prenom, String profession, String sex, int tel) {
       public List<User> afficher() {
            List<User> user = new ArrayList<User>();
        try {
            String sql = "SELECT * FROM user1";

            PreparedStatement src = con.prepareStatement(sql);
              
            ResultSet res = src.executeQuery(sql);
            while (res.next()) {
                User u = new User(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("roles"),
                        res.getString("nom"),
                        res.getDate("datedenaissance"),
                        res.getInt("phone"),
                        res.getString("sex"));
                
                user.add(u);
               /* User e = new User();
                e.setId(res.getInt(2));
                e.setDTYPE(res.getString(1));
                e.setNom(res.getString(8));
                e.setPrenom(res.getString(10));
                e.setEmail(res.getString(3));
                e.setPassword(res.getString(9));
                e.setBirthDate(res.getDate(6));
               

                s.add(e);*/
            }
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }    
       return user;
    }
      
      
     public static void updateUser(String phone,String nom,String username1,String username,String mail) throws ClassNotFoundException, SQLException{     //Statement
//          CompteController ser=new CompteController();


  try
    {
      // create a java mysql database connection
//      String myDriver = "org.gjt.mm.mysql.Driver";
//      String myUrl = "jdbc:mysql://localhost:3306/bonplandb";
//      Class.forName(myDriver);
//      Connection conn = DriverManager.getConnection(myUrl, "root", "");
    
      // create the java mysql update preparedstatement
      String query = "update user1 set username = ? ,nom = ? ,email = ?,phone = ? where `username` = ?";
//      PreparedStatement preparedStmt = con.prepareStatement(query);
//PreparedStatement preparedStmt ;
            
           
             PreparedStatement preparedStmt = ds.getConnection().prepareStatement(query);
      preparedStmt.setString(1, username1);
      preparedStmt.setString(2, nom);
      preparedStmt.setString(3, mail);
      preparedStmt.setString(4, phone);
      preparedStmt.setString(5, username);

      // execute the java preparedstatement
      preparedStmt.executeUpdate();
     System.out.println("Done :) <--------------");
//      conn.close();
    }
    catch (Exception e)
    {
      
        System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }

    
          
    
    

      }
     
     public boolean notExistUser(String email,String pas) throws ClassNotFoundException, SQLException {
         
             PreparedStatement ps;            
             ps = con.prepareStatement("SELECT `email` ,`password` FROM `user` WHERE `email` = ? AND `password` = ?");
             ps.setString(1, String.valueOf(email));
             ps.setString(2, String.valueOf(pas));
             ResultSet result = ps.executeQuery();
        return result.next();

      
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
//      public List<User> rechercherUser(int id) {
//    List<User> listu = new ArrayList();
//          User u = new User();
//    String requete= "SELECT * FROM user WHERE id = "+id+" ;"; 
//    try {
//        PreparedStatement ps; 
//        ResultSet result;
//            ps = con.prepareStatement(requete);
//            result =ps.executeQuery(requete); 
//            while (result.next()) {               
//                ps.setString(1, u.getDTYPE());
//            
//            ps.setString(2, u.getEmail());
//            ps.setString(3, u.getAdress());
//            ps.setInt(4, u.getAge());
//            ps.setDate(5, u.getBirthDate());
//            ps.setString(6, u.getLogin());
//            ps.setString(7, u.getNom());
//            ps.setString(8, u.getPassword());
//            ps.setString(9, u.getPrenom());
//            ps.setString(10, u.getProfession());
//            ps.setString(11, u.getSex());
//            ps.setInt(12, u.getTel());
//            ps.setString(13, u.getValid());
//             ps.setString(14, u.getTest());
//             listu.add(u);
//                }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    return listu;
//    }
    
} 
