/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import techniques.DataSource;

/**
 *
 * @author Maissa
 */
public class RatingService 
{
       private Connection connection;

        public RatingService() 
        {
        connection = DataSource.getInstance().getConnection();
        }
        
        public void addRating(float rate, int id_etab,int id_user)
        {
            Session S = new Session();
            int I = S.user.id;
            try
            {
                String req = "Select * From Rating Where id_user=?";
                PreparedStatement PS1 = connection.prepareStatement(req);
                PS1.setInt(1, I);
                ResultSet RS = PS1.executeQuery();
                if(RS.getInt("id_user") != I)
                {
                    try 
                    {
            String req1 = "insert into rating(rate,id_etab,id_user) values (?,?,?)";
            PreparedStatement ps2 = connection.prepareStatement(req1);
            ps2.setFloat(1, rate);
            ps2.setInt(2,id_etab);
            ps2.setInt(3,id_user);
            ps2.executeUpdate();
            System.out.println("A");
                    } 
            catch (SQLException ex) 
            {
            System.out.println(ex);
            }   
                }
                else
                {
                    try 
                    {
            String req2 = "Update Rating Set Rate=?, Id_Etab=? Where Id_User=?";
            PreparedStatement ps3 = connection.prepareStatement(req2);
            ps3.setFloat(1, rate);
            ps3.setInt(2,id_etab);
            ps3.setInt(3,id_user);
            ps3.executeUpdate();
            System.out.println("B");
                    } 
            catch (SQLException ex) 
            {
            System.out.println(ex);
            }
                }
            }
            catch (Exception E)
            {
                
            }  
        }
}
