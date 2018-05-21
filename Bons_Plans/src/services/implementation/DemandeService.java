package services.implementation;

import entities.Demande;
import entities.Etablissement;
import entities.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import techniques.DataSource;

public class DemandeService 
{
    private Connection connection;
    
    public DemandeService() 
    {
        connection = DataSource.getInstance().getConnection();
    }
    
    public void Ajout(String nom, String type, String adresse, String description, String horaire_ouverture, String horaire_fermeture, int numtel, String url, int budgetmoyen, String type1, String image)
    {
        Session S = new Session();
        int ID = S.user.id;
        try
        {
        PreparedStatement PS = connection.prepareStatement("Insert Into Demande_Ajout(nom,type,adresse,description,horaire_ouverture,horaire_fermeture,numero,url,budget_moyen,type_loisirs,type_resto,type_shops,nbrStars,image_principale,id_user) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        PS.setString(1, nom);
        PS.setString(2, type);
        PS.setString(3, adresse);
        PS.setString(4, description);
        PS.setString(5, horaire_ouverture);
        PS.setString(6, horaire_fermeture);
        PS.setInt(7, numtel);
        PS.setString(8, url);
        PS.setInt(9, budgetmoyen);
        PS.setString(10, null);
        PS.setString(11, null);
        PS.setString(12, null);
        PS.setString(13, null);
        PS.setString(14, image);
        if ("Restaurants/Cafés".equals(type))
        {
            PS.setString(11, type1);
        }
        if ("Boutiques".equals(type))
        {
            PS.setString(12, type1);
        }
        if ("Hotels".equals(type))
        {
            PS.setString(13, type1);
        }
        if ("Autres".equals(type))
        {
            PS.setString(10, type1);
        }
        PS.setInt(15, ID);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public ArrayList<Demande> GetAllDemands()
    {
        ArrayList<Demande> AL = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Demande_Ajout");
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
            {
                int s1 = Res.getInt("id");
                int s2 = Res.getInt("id_user");
                String s3 = Res.getString("nom");
                String s4 = Res.getString("type");
                String s5 = Res.getString("adresse");
                String s6 = Res.getString("description");
                String s7 = Res.getString("horaire_ouverture");
                String s16 = Res.getString("horaire_ouverture");
                int s8 = Res.getInt("numero");
                String s9 = Res.getString("url");
                int s10 = Res.getInt("budget_moyen");
                String s11 = Res.getString("image_principale");
                String s12 = Res.getString("type_resto");
                String s13 = Res.getString("type_loisirs");
                String s14 = Res.getString("type_shops");
                String s15 = Res.getString("nbrStars");
                Demande D = new Demande(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16);
                AL.add(D);
            }
            Res.close();
            PS.close();
        }
        catch(SQLException E)
        {
        System.out.println(E);     
        }
        return AL;
    }
    
    public Demande findById(int id) 
    {
        Demande D = null;
         try 
         {
             PreparedStatement PS = connection.prepareStatement("Select * From Demande_Ajout Where id=? LIMIT 1");
             PS.setInt(1, id);
             ResultSet Res = PS.executeQuery();
             while(Res.next())
             {
             int s1 = Res.getInt("id");
             String s2 = Res.getString("nom");
             String s3 = Res.getString("type");
             String s4 = Res.getString("adresse");
             String s5 = Res.getString("description");
             String s6 = Res.getString("horaire_ouverture");
             String s7 = Res.getString("horaire_fermeture");
             int s8 = Res.getInt("numero");
             String s9 = Res.getString("url");
             int s10 = Res.getInt("budget_moyen");
             String s11 = Res.getString("image_principale");
             String s12 = Res.getString("type_resto");
             String s13 = Res.getString("type_loisirs");
             String s14 = Res.getString("type_shops");
             String s15 = Res.getString("nbrStars");
             int resp=Res.getInt("id_user");
             Demande D1 = new Demande(s1, resp, s2, s3, s4, s5, s6, s8, s9, s10, s11, s12, s13, s14, s15, s7);
             D = D1;
             }
         } 
         catch (SQLException ex) 
         {
             Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return D;
    }
}
