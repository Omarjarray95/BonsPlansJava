/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;
import entities.Etablissement;
import entities.Offre;
import services.interfaces.IOffreService;
import entities.Evenement;
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
public class OffreService{
     private Connection connection;

    public OffreService() {
        connection = DataSource.getInstance().getConnection();
    }
   
        public void add(Offre offre) {
        try {
            String req = "insert into offre(id_etablissement,"
                    + "date_debut,"
                    + "date_fin,"
                    + "description,"
                    + "offre,code_promo,pourcentage)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, offre.getId_etablissement());
            ps.setDate(2, offre.getDate_debut());
            ps.setDate(3, offre.getDate_fin());
            ps.setString(4,offre.getDescription());
            ps.setString(5,offre.getOffre());
            ps.setString(6,offre.getCode());
            ps.setDouble(7, offre.getPourcentage());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
    public void update(Offre offre,Offre o ) {

        try {
            String req = "update offre set offre = ? , description = ?, date_debut = ? , date_fin = ?, pourcentage=?, code_promo=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, o.getOffre());
            ps.setString(2, o.getDescription());
            ps.setDate(3, o.getDate_debut());
            ps.setDate(4, o.getDate_fin());
            ps.setInt(7, offre.getId());
            ps.setDouble(5, o.getPourcentage());
            ps.setString (6,o.getCode());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
    public void delete(Offre offre) {
        
           try {
            String req = "delete from offre where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,offre.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
        public List<Offre> getAll() {
        List<Offre> offres = new ArrayList<>();
        try {

            String req = "select * from offre";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
                offres.add(offer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }
                public List<Offre> getAllMotCle(String mot) {
        
        if (!mot.equals("")){
                        ArrayList<Offre> offres = new ArrayList<>();
        try {

            String req = "select * from offre where description LIKE ? OR offre LIKE ? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "%" + mot+ "%");
            ps.setString(2, "%" + mot+ "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
                offres.add(offer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;}
        else{
                        return getAll();
                    }
    }
                public List<Offre> getAllByEtab(int id_etab) {
        List<Offre> offres = new ArrayList<>();
        try {

            String req = "select * from offre where id_etablissement= ? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id_etab);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
                offres.add(offer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }
                        public List<Offre> getAllByEtabMot(int id_etab,String mot) {
        if (!mot.equals("")){
                            List<Offre> offres = new ArrayList<>();
        try {

            String req = "select * from offre where id_etablissement= ? and (description LIKE ? OR offre LIKE ?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id_etab);
            ps.setString(2, "%" + mot+ "%");
            ps.setString(3, "%" + mot+ "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
                offres.add(offer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;}
        else{
                        return getAllByEtab(id_etab);
                    }
    }
       
   
    public Offre findById(Integer id) {
        Offre offer = null;
        try {
            String req = "select * from offre where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offer;  
    }
     public Offre findByName(String id) {
        Offre offer = null;
        try {
            String req = "select * from offre where offre =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 offer = new Offre( rs.getInt(1),rs.getInt(2), rs.getDate(5), rs.getDate(6),rs.getString(4), rs.getString(3),rs.getString(7),rs.getDouble(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offer;  
    }
}
