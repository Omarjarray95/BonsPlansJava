package services.implementation;

import entities.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
