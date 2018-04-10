package services.implementation;

import entities.Etablissement;
import entities.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import techniques.DataSource;

public class TagService 
{
    private Connection connection;

    public TagService() 
    {
        connection = DataSource.getInstance().getConnection();
    }
    
    public void Ajout(String nom)
    {
        Tag T = this.findByName(nom);
        if (T == null)
        {
        try
        {
        PreparedStatement PS = connection.prepareStatement("Insert Into Tag(Name) VALUES (?)");
        PS.setString(1, nom);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
        }
    }
    
    public ArrayList<Tag> AfficheTE(int id)
    {
        ArrayList<Tag> TE = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Etablissement_Tag Where Etablissement_Id=?");
        PS.setInt(1, id);
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
        {
                int s1 = Res.getInt("tag_id");
                try
                {
                PreparedStatement PS1 = connection.prepareStatement("Select * From Tag Where id=?");
                PS1.setInt(1, s1);
                ResultSet Res1 = PS1.executeQuery();
                while (Res1.next())
                {
                    int i = Res1.getInt("id");
                    String s = Res1.getString("Name");
                    Tag T = new Tag(i,s);
                    TE.add(T);
                }
                }
                catch (SQLException E)
                {
                System.out.println(E);
                }
        }
            Res.close();
            PS.close();
        }
        catch(SQLException E)
        {
        System.out.println(E);     
        }
        return TE;
    }
    
    public ArrayList<Tag> AfficheTNE(int id)
    {
        ArrayList<Tag> TE = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select id From Tag Where id Not In(Select tag_id from etablissement_tag Where etablissement_id=?)");
        PS.setInt(1, id);
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
        {
                int s1 = Res.getInt("id");
                try
                {
                PreparedStatement PS1 = connection.prepareStatement("Select * From Tag Where id=?");
                PS1.setInt(1, s1);
                ResultSet Res1 = PS1.executeQuery();
                while (Res1.next())
                {
                    int i = Res1.getInt("id");
                    String s = Res1.getString("Name");
                    Tag T = new Tag(i,s);
                    TE.add(T);
                }
                }
                catch (SQLException E)
                {
                System.out.println(E);
                }
        }
            Res.close();
            PS.close();
        }
        catch(SQLException E)
        {
        System.out.println(E);     
        }
        return TE;
    }
    
    public void Modif(int id,String nom)
    {
        try
        {
        PreparedStatement PS1 = connection.prepareStatement("Update Tag Set nom=? Where id=?");
        PS1.setString(1, nom);
        PS1.setInt(2, id);
        PS1.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public void Supp(int id)
    {
        try
        {
        PreparedStatement PS = connection.prepareStatement("Delete From Tag Where id=?");
        PS.setInt(1, id);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public Tag findByName(String nom) 
    {
        Tag T = null;
         try 
         {
             PreparedStatement PS = connection.prepareStatement("Select * From Tag Where Name=? LIMIT 1");
             PS.setString(1, nom);
             ResultSet Res = PS.executeQuery();
             while(Res.next())
             {
             int s1 = Res.getInt("id");
             String s2 = Res.getString("Name");
             Tag T1 = new Tag(s1,s2);
             T = T1;
             }
         } 
         catch (SQLException ex) 
         {
             Logger.getLogger(TagService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return T;
    }
    
}
