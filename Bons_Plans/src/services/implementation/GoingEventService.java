/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;


import entities.Etablissement;
import services.interfaces.IEvenementService;
import entities.Evenement;
import entities.GoingEvent;
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
public class GoingEventService {
    private Connection connection;

    public GoingEventService() {
        connection = DataSource.getInstance().getConnection();
    }
  
        public void add(int user,int event) {
            
        try {
            String req = "insert into going_event(user_id,"
                    + "event_id)"
                    + " values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2,event);
            ps.executeUpdate();
            EvenementService service= new EvenementService();
            service.increment_going(service.findById(event));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
      
    public void delete(int user,int event) {
          
           try {
            String req = "delete from going_event where user_id = ? and event_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,user);
            ps.setInt(2,event);
            ps.executeUpdate();
            EvenementService service= new EvenementService();
            service.decrement_going(service.findById(event));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void deleteAllByIdEvent(int event) {
          
           try {
            String req = "delete from going_event where event_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,event);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        public List<GoingEvent> getAll() {
        List<GoingEvent> goings = new ArrayList<>();
        try {

            String req = "select * from going_event";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GoingEvent going = new GoingEvent(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                goings.add(going);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return goings;
    }
        

    public GoingEvent findById(Integer id) {
        GoingEvent going = null;
        try {
            String req = "select * from going_event where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 going = new GoingEvent(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return going;  
    }
      public int count(int user,int event){
        int res=0;
        try {
            String req = "select * from going_event where user_id =? and event_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2, event);
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
