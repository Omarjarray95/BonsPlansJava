/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Etablissement;
import entities.InterestedEvent;
import entities.LikedEtablissement;
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
public class LikedEtablissementService {
      private Connection connection;

    public LikedEtablissementService() {
        connection = DataSource.getInstance().getConnection();
    }
  
        public void add(int etab,int user) {
        try {
            String req = "insert into wishliste(user_id,"
                    + "favoris_id)"
                    + " values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2,etab);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
      
    public void delete(int etab,int user) {
        
           try {
            String req = "delete from wishliste where user_id = ? and favoris_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2,etab);;
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public List<LikedEtablissement> getAll() {
        List<LikedEtablissement> likes = new ArrayList<>();
        try {

            String req = "select * from wishliste";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LikedEtablissement like = new LikedEtablissement(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                likes.add(like);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return likes;
    }
        

    public LikedEtablissement findById(Integer id) {
        LikedEtablissement like = null;
        try {
            String req = "select * from wishliste where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 like = new LikedEtablissement(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return like;  
    }
      public int count(int user,int etablissement){
        int res=0;
        try {
            String req = "select * from wishliste where user_id =? and favoris_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2, etablissement);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
      public double countLikes(String type){
        double res=0;
        try {
            String req = "select * from wishliste inner join etablissement on wishliste.favoris_id=etablissement.id where etablissement.Type =? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res/countAll();
    }
              public double countAll(){
        double res=0;
        try {
            String req = "select * from wishliste ";
            PreparedStatement ps = connection.prepareStatement(req);
           
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
