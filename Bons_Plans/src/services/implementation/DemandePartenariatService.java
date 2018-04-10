/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.DemandePartenariat;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techniques.DataSource;

/**
 *
 * @author Ons Ben Othmen
 */
public class DemandePartenariatService {

    public DemandePartenariatService() {
        connection = DataSource.getInstance().getConnection();
    }
    private Connection connection;
        public void add(DemandePartenariat demande) {
        try {
            String req = "insert into demande_partenariat(favoris_id,"
                    + "user_id,"
                    + "Description)"
                    + " values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, demande.getEtab());
            ps.setInt(2, demande.getUser());
            ps.setString(3,demande.getDescription());
            ps.executeUpdate();
            System.out.println("Insertion réussi  ");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Insertion failed  ");
        }
    }
          public void delete(DemandePartenariat demande) {
          
           try {
            String req = "delete from demande_partenariat where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,demande.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
           
    }
                    public void accept(DemandePartenariat demande) {
          
           try {
               
            String req1 = "delete from demande_partenariat where id = ?";
             String req2 = "insert into partenariat(favoris_id,"
                    + "user_id,"
                    + "Description)"
                    + " values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            PreparedStatement ps2 = connection.prepareStatement(req2);
            ps.setInt(1,demande.getId());
            ps2.setInt(1, demande.getEtab());
            ps2.setInt(2, demande.getUser());
            ps2.setString(3,demande.getDescription());
            ps2.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
           
    }
        public ArrayList<DemandePartenariat> getAll() {
        ArrayList<DemandePartenariat> demandes = new ArrayList<>();
        try {

            String req = "select * from demande_partenariat";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DemandePartenariat demande = new DemandePartenariat(rs.getInt(1),rs.getInt(2),rs.getInt(3), rs.getString(4));
                demandes.add(demande);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demandes;
    }
              public int check(int etablissement){
        int res=0;
        try {
            String req = "select * from demande_partenariat where favoris_id=?";
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
              public DemandePartenariat findById(Integer id) {
        DemandePartenariat demande = null;
        try {
            String req = "select * from demande_partenariat where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 demande = new DemandePartenariat(rs.getInt(1),rs.getInt(2),rs.getInt(3), rs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return demande;  
    }
        
}
