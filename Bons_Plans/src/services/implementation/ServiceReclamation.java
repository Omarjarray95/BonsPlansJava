/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import techniques.DataSource;
import entities.Reclamation;
import entities.Etablissement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DADOU
 */
public class ServiceReclamation {
     static DataSource ds = new DataSource ();
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    Etablissement E=new Etablissement();
    EtablissementService so=new EtablissementService();
    
    public ServiceReclamation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void addReclamation(Reclamation r) throws SQLException {
 
       
     String req = "INSERT INTO `reclamation` (`id_rec`,`description` ,`id_user` ,`id_etab`) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement pre ;
            try {
            pre=con.prepareStatement(req);
            pre.setInt(1, r.getId_rec());
            pre.setString(2, r.getDescription());
            pre.setInt(3, r.getId_user());
            pre.setInt(4, r.getId_etablissement());   
           
            if (get(r.getId_etablissement()) >= 5){
                   //Delete 
                   //while (get(r.getId_etablissement()) >=5 )  {
                       so.DeleteEtablissement(r.getId_etablissement()); 
                  // }
                 
                   
             }else{
   pre.executeUpdate(); 
   }
                      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }  
    public int  get(int id) throws SQLException{
          //SELECT COUNT (id_user) FROM reclamation
          //SELECT count( * ) as id_user FROM reclamation WHERE id_offre  = 'Name'
        ResultSet rs3 = ste.executeQuery("SELECT count( * ) as id_user FROM reclamation WHERE id_etab  = '"+id+"'");
    while(rs3.next()){
    int count = rs3.getInt("id_user");
    return count;   
    }
         return 0;
            }
       
            
    
    
        
     
           
     public List<Reclamation> afficher() {
         
         
             List<Reclamation> reclamation = new ArrayList<Reclamation>();
             try {
             String sql = "SELECT * FROM  reclamation ";
             
             PreparedStatement src = con.prepareStatement(sql);
             
             ResultSet res = src.executeQuery(sql);
             while (res.next()) { 
                Reclamation r = new Reclamation(
                         res.getString("description"),
                         res.getInt("id_etab"));
                 
                 reclamation.add(r);
             } } catch (Exception e) {
            System.out.println("Erreur " + e);
        }    
       return reclamation;
    }
     }