/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Commentaire;
import entities.Commentaire;
import entities.Etablissement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import techniques.DataSource;

/**
 *
 * @author Maissa
 */
public class CommentaireService {
    private Connection connection;
        private Statement ste;


    public CommentaireService() {
        try{
        connection = DataSource.getInstance().getConnection();
        ste = connection.createStatement();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public void add(String Comment,int id_etab ,String dat,String img,int id_user,String nom) {
        try {
            String req = "insert into commentaire1(comment,id_etab,created,photo,id_user,nom) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,Comment);
            ps.setInt(2,id_etab);
             ps.setString(3,dat);
             ps.setString(4, img);
             ps.setInt(5,id_user);
             ps.setString(6,nom);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public void update(int id, String comment) {
         try
        {
        PreparedStatement PS1 = connection.prepareStatement("update commentaire1 set comment = ? where id = ?");
        PS1.setString(1, comment);
        PS1.setInt(2, id);
        PS1.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
     
     public void delete(int id) {
       
           try {
            String req = "delete from commentaire1 where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public void Signaler(int idS, String signal,int id_user){
          try {
            String req = "insert into table_indications(id_commentaire,indication,user_id) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,idS);
            ps.setString(2,signal);
            ps.setInt(3,id_user);
            ps.executeUpdate();
            
            
            if ((this.nbIndications(idS)) >= 5){
                       this.delete(idS); 
                   
             }else{
            ps.executeUpdate(); 
            }
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }  
     }
     
      public int nbIndications(int idComment) throws SQLException{
          int count=0;
          ResultSet rs = ste.executeQuery("SELECT count( * ) as nbr FROM table_indications WHERE id_commentaire  = '"+idComment+"'");
         while(rs.next()){
         count = rs.getInt("nbr");
         return count ; 
         } return 0;
         }
    
    
        public ArrayList<Commentaire> Affiche()
    {
        ArrayList<Commentaire> AL = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From commentaire1");
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
            {
                int s1 = Res.getInt("id");
                String s2 = Res.getString("comment");
                int s3 = Res.getInt("id_etab");
                String s4= Res.getString("created");
                String s5= Res.getString("photo");
                int s6 =Res.getInt("id_user");
                String s7 = Res.getString("nom");
                Commentaire C = new Commentaire(s1,s2,s3,s4,s5,s6,s7);
                AL.add(C);
            }
            Res.close();
            PS.close();
        }
        catch(SQLException E)
        {
        System.out.println(E);     
        }
        return AL;
    }
        
        public Commentaire findById(int id) 
    {
        Commentaire C = null;
         try 
         {
             PreparedStatement PS = connection.prepareStatement("Select * From commentaire1 Where id=? LIMIT 1");
             PS.setInt(1, id);
             ResultSet Res = PS.executeQuery();
             while(Res.next())
             {
             int s1 = Res.getInt("id");
             String s2 = Res.getString("comment");
             int s3 = Res.getInt("id_etab");
                String s4= Res.getString("created");
             Commentaire C1 = new Commentaire(s1,s2,s3,s4);
             C = C1;
             }
         } 
         catch (SQLException ex) 
         {
             Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return C;
    }
    

}
