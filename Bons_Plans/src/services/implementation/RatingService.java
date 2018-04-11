/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import techniques.DataSource;

/**
 *
 * @author Maissa
 */
public class RatingService {
       private Connection connection;

        public RatingService() {
        connection = DataSource.getInstance().getConnection();
    }
        
        public void addRating(float rate, int id_etab){
            try {
            String req = "insert into rating(rate,id_etab) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setFloat(1, rate);
            ps.setInt(2,id_etab);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        } }
    
    
}
