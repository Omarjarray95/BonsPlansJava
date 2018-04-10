/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.DemandePartenariat;
import entities.Partenariat;
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
public class PartenariatService {
    
    private Connection connection;

    public PartenariatService() {
        connection = DataSource.getInstance().getConnection();
    }
    
      public ArrayList<Partenariat> getAll() {
        ArrayList<Partenariat> partenaires = new ArrayList<>();
        try {

            String req = "select * from partenariat";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partenariat p = new Partenariat(rs.getInt(1),rs.getInt(2),rs.getInt(3), rs.getString(4));
                partenaires.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return partenaires;
    }
        public int check(int etablissement){
         int res=0;
        try {
            String req = "select * from partenariat where favoris_id=?";
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
        
        
                    
}
