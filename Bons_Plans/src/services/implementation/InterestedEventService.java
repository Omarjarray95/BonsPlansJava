/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Evenement;
import entities.InterestedEvent;
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
public class InterestedEventService {
      private Connection connection;

    public InterestedEventService() {
        connection = DataSource.getInstance().getConnection();
    }
  
        public void add(int user,int event) {
            
        try {
            String req = "insert into interest(user_id,"
                    + "event_id)"
                    + " values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user);
            ps.setInt(2,event);
            ps.executeUpdate();
            EvenementService eventService=new EvenementService();
            eventService.increment_interest(eventService.findById(event));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
    }
        
      
        public void delete(int user,int event) {
           
           try {
            String req = "delete from interest where user_id = ? and event_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,user);
            ps.setInt(2,event);
            ps.executeUpdate();
            EvenementService service= new EvenementService();
            service.decrement_interest(service.findById(event));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public List<InterestedEvent> getAll() {
        List<InterestedEvent> interests = new ArrayList<>();
        try {

            String req = "select * from going_event";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InterestedEvent interest = new InterestedEvent(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                interests.add(interest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interests;
    }
        

    public InterestedEvent findById(Integer id) {
        InterestedEvent interest = null;
        try {
            String req = "select * from interest where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 interest = new InterestedEvent(rs.getInt(1),rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return interest;  
    }
              public int count(int user,int event){
        int res=0;
        try {
            String req = "select * from interest where user_id =? and event_id=?";
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
