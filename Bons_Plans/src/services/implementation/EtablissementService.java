/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;
import entities.Etablissement;
import entities.Evenement;
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
public class EtablissementService {
     private Connection connection;

    public EtablissementService() {
        connection = DataSource.getInstance().getConnection();
    }
    public Etablissement findById(Integer id) {
        Etablissement etab = null;
        try {
            String req = "select * from etablissement where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etab;  
    }
        public int checkPartner(int id)
        {
            int resultat=0;
        Etablissement etab = null;
    try {
            String req = "select * from etablissement where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                
                 etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(20),rs.getInt(19));
                 resultat=etab.getPartenaire();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(resultat + " Hedha");
        return resultat;
    
    }
    public int countVisited(int etablissement){
        int res=0;
        try {
            String req = "select * from visited where favoris_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, etablissement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
        public int countAll(){
        int res=0;
        try {
            String req = "select * from etablissement";
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
        public float Pourcentage(String type){
        float res=0;
        float total=0;
        try {
            String req = "select * from etablissement where Type=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
                try {
            String req2 = "select * from etablissement ";
            PreparedStatement ps2 = connection.prepareStatement(req2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                 total++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                System.out.println(res/total);   
        return res/total;
    }
        public int countLikes(int etablissement){
        int res=0;
        try {
            String req = "select * from wishliste where favoris_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, etablissement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
                public ArrayList<Etablissement> getAll() {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }                public ArrayList<Etablissement> getMine(int id) {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement where representant_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }                
        public int countUsers(){
        int res=0;
        try {
            String req = "select * from user";
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
         public int countEvents(){
        int res=0;
        try {
            String req = "select * from evenement ";
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
         public int countOffers(){
        int res=0;
        try {
            String req = "select * from offre ";
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
