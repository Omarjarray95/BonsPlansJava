/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Etablissement;
import entities.LikedEtablissement;
import entities.User;
import entities.VisitedEtablissement;
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
public class VisitedEtablissementService {
       private Connection connection;

    public VisitedEtablissementService() {
        connection = DataSource.getInstance().getConnection();
    }
  
        public void add(int etab,int user) {
        try {
            String req = "insert into visited(user_id,"
                    + "favoris_id)"
                    + " values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2, etab);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
      
    public void delete(int etab,int user) {
        
           try {
            String req = "delete from visited where user_id = ? and favoris_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2, etab);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public List<VisitedEtablissement> getAll() {
        List<VisitedEtablissement> visits = new ArrayList<>();
        try {

            String req = "select * from visited";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VisitedEtablissement visit = new VisitedEtablissement(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                visits.add(visit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return visits;
    }
        

    public VisitedEtablissement findById(Integer id) {
        VisitedEtablissement visit = null;
        try {
            String req = "select * from visited where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 visit = new VisitedEtablissement(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return visit;  
    }
      public int count(int user,int etablissement){
        int res=0;
        try {
            String req = "select * from visited where user_id =? and favoris_id=?";
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
      
       public double countVisits(String type){
        double res=0;
        try {
            String req = "select * from visited inner join etablissement on visited.favoris_id=etablissement.id where etablissement.Type =? ";
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
            String req = "select * from visited ";
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
