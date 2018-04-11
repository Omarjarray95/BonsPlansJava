/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;
import entities.Etablissement;
import services.interfaces.IEvenementService;
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
public class EvenementService {
    private Connection connection;

    public EvenementService() {
        connection = DataSource.getInstance().getConnection();
    }
        public void add(Evenement event) {
        try {
            String req = "insert into evenement(id_etablissement,"
                    + "date,"
                    + "description,"
                    + "nom,"
                    + "interesses,"
                    + "nbr_personnes)"
                    + " values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, event.getId_etablissement());
            ps.setDate(2, event.getDate());
            ps.setString(3,event.getDescription());
            ps.setString(4,event.getNom());
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.executeUpdate();
            System.out.println("Insertion réussi  ");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Insertion failed  ");
        }
    }
         
    public void update(int id,Evenement e) {

        try {
            String req = "update evenement set nom = ? , description = ?, date = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setDate(3, e.getDate());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
        public void increment_going(Evenement event) {

        try {
            String req = "update evenement set nbr_personnes=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, event.getNbr_personnes()+1);
            ps.setInt(2, event.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        public void increment_interest(Evenement event) {

        try {
            String req = "update evenement set interesses=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, event.getInteresses()+1);
            ps.setInt(2, event.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        public void decrement_going(Evenement event) {

        try {
            String req = "update evenement set nbr_personnes=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, event.getNbr_personnes()-1);
            ps.setInt(2, event.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        public void decrement_interest(Evenement event) {

        try {
            String req = "update evenement set interesses=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, event.getInteresses()-1);
            ps.setInt(2, event.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
        
    public void delete(Evenement event) {
           GoingEventService service1=new GoingEventService();
           service1.deleteAllByIdEvent(event.getId());
           try {
            String req = "delete from evenement where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,event.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
           
    }
    
        public ArrayList<Evenement> getAll() {
        ArrayList<Evenement> events = new ArrayList<>();
        try {

            String req = "select * from evenement";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Evenement event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
                events.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }
                public ArrayList<Evenement> getAllMotCle(String mot) {
                    if (!mot.equals("")){
                        ArrayList<Evenement> events = new ArrayList<>();
                        try {
                            
                            String req = "select * from evenement where description LIKE ? OR nom LIKE ? ";
                            PreparedStatement ps = connection.prepareStatement(req);
                            ps.setString(1, "%" + mot+ "%");
                            ps.setString(2, "%" + mot+ "%");
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                Evenement event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
                                events.add(event);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        return events;}
                    else{
                        return getAll();
                    }
    }
        public ArrayList<Evenement> getAllByEtab(int id_etab) {
        ArrayList<Evenement> events = new ArrayList<>();
        try {

            String req = "select * from evenement where id_etablissement=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id_etab);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Evenement event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
                events.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }
                public ArrayList<Evenement> getAllByEtabMot(int id_etab,String mot) {
        if (!mot.equals("")){
                    ArrayList<Evenement> events = new ArrayList<>();
        try {

            String req = "select * from evenement where id_etablissement=? and (description LIKE ? OR nom LIKE ?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id_etab);
            ps.setString(2, "%" + mot+ "%");
            ps.setString(3, "%" + mot+ "%");            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Evenement event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
                events.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;}
        else {
        return getAllByEtab(id_etab);
        }
    }
       
    
    public Evenement findById(Integer id) {
        Evenement event = null;
        try {
            String req = "select * from evenement where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return event;  
    }
        public Evenement findByName(String id) {
        Evenement event = null;
        try {
            String req = "select * from evenement where nom =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 event = new Evenement(rs.getInt(1),rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getInt(7), rs.getInt(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return event;  
    }

}
