package services.implementation;
import entities.Etablissement;
import entities.LikedEtablissement;
import entities.Session;
import entities.Tag;
import entities.User;
import entities.VisitedEtablissement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import techniques.DataSource;

public class EtablissementService 
{
     private Connection connection;

    public EtablissementService() 
    {
        connection = DataSource.getInstance().getConnection();
    }
    
    public void Ajout(String nom, String type, String adresse, String description, String horaire_ouverture, String horaire_fermeture, int numtel, String url, int budgetmoyen, String type1, String image)
    {
        Session S = new Session();
        int ID = S.user.id;
        try
        {
        PreparedStatement PS = connection.prepareStatement("Insert Into Etablissement(nom,type,adresse,description,horaire_ouverture,horaire_fermeture,numero,url,budget_moyen,type_loisirs,type_resto,type_shops,nbrStars,image_principale,id_representant) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
    
    public ArrayList<Etablissement> Affiche()
    {
        ArrayList<Etablissement> AL = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Etablissement");
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
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
                int resp=Res.getInt("representant_id");
                Etablissement E = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,resp);
                AL.add(E);
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
    
    public Etablissement findById(int id) 
    {
        Etablissement E = null;
         try 
         {
             PreparedStatement PS = connection.prepareStatement("Select * From Etablissement Where id=? LIMIT 1");
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
             int resp=Res.getInt("representant_id");
             Etablissement E1 = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,resp);
             E = E1;
             }
         } 
         catch (SQLException ex) 
         {
             Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return E;
    }
    
    public void Modif(int id, String nom, String type, String type1, String adresse, String description, String horaire_ouverture, String horaire_fermeture, int numero, String url, int budgetmoyen, String image)
    {
        try
        {
        PreparedStatement PS1 = connection.prepareStatement("Update Etablissement Set nom=?, type=?, type_resto=?, type_shops=?, type_loisirs=?, nbrStars=?, adresse=?, description=?, horaire_ouverture=?, horaire_fermeture=?, numero=?, url=?, budget_moyen=?, image_principale=? Where id=?");
        PS1.setString(1, nom);
        PS1.setString(2, type);
        PS1.setString(3, null);
        PS1.setString(4, null);
        PS1.setString(5, null);
        PS1.setString(6, null);
        if ("Restaurants/Cafés".equals(type))
        {
            PS1.setString(3, type1);
        }
        if ("Boutiques".equals(type))
        {
            PS1.setString(4, type1);
        }
        if ("Hotels".equals(type))
        {
            PS1.setString(6, type1);
        }
        if ("Autres".equals(type))
        {
            PS1.setString(5, type1);
        }
        PS1.setString(7, adresse);
        PS1.setString(8, description);
        PS1.setString(9, horaire_ouverture);
        PS1.setString(10, horaire_fermeture);
        PS1.setInt(11, numero);
        PS1.setString(12, url);
        PS1.setInt(13, budgetmoyen);
        PS1.setString(14, image);
        PS1.setInt(15, id);
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
        PreparedStatement PS = connection.prepareStatement("Delete From Etablissement Where id=?");
        PS.setInt(1, id);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public void AjoutTag(int ide, int idt)
    {
        try
        {
        PreparedStatement PS = connection.prepareStatement("Insert Into Etablissement_Tag(etablissement_id,tag_id) Select ?,? Where Not Exists(Select * From Etablissement_Tag Where etablissement_id=? And tag_id=?);");
        PS.setInt(1, ide);
        PS.setInt(2, idt);
        PS.setInt(3, ide);
        PS.setInt(4, idt);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public void SuppTag(int ide, int idt)
    {
        try
        {
        PreparedStatement PS = connection.prepareStatement("Delete From Etablissement_tag Where Etablissement_id=? And Tag_id=?");
        PS.setInt(1, ide);
        PS.setInt(2, idt);
        PS.executeUpdate();
        }
        catch(SQLException E)
        {
        System.out.println(E);
        }
    }
    
    public ArrayList<Etablissement> FindByName(String nom) 
    {
        ArrayList<Etablissement> AL = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Etablissement Where Nom Like ?");
        PS.setString(1, '%' + nom + '%');
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
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
                int resp=Res.getInt("representant_id");
                Etablissement E = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,resp);
                AL.add(E);
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
    
    public ArrayList<Etablissement> FindByParameters(String criteria, int MaV, int MiV) 
    {
        ArrayList<Etablissement> AL = new ArrayList<>();
        String Req = "Select * From Etablissement Where budget_moyen Between " + Integer.toString(MiV) + " And " + Integer.toString(MaV) + " Order By nom;";
        if (criteria.equals("HOASC"))
        {
            Req = "Select * From Etablissement Where budget_moyen Between " + Integer.toString(MiV) + " And " + Integer.toString(MaV) + " Order By horaire_ouverture ASC;";
        }
        if (criteria.equals("HFDESC"))
        {
            Req = "Select * From Etablissement Where budget_moyen Between " + Integer.toString(MiV) + " And " + Integer.toString(MaV) + " Order By horaire_fermeture DESC;";
        }
        if (criteria.equals("BMASC"))
        {
            Req = "Select * From Etablissement Where budget_moyen Between " + Integer.toString(MiV) + " And " + Integer.toString(MaV) + " Order By budget_moyen ASC;";
        }
        if (criteria.equals("BMDESC"))
        {
            Req = "Select * From Etablissement Where budget_moyen Between " + Integer.toString(MiV) + " And " + Integer.toString(MaV) + " Order By budget_moyen DESC;";
        }
        try
        {
        PreparedStatement PS = connection.prepareStatement(Req);
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
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
                int resp=Res.getInt("representant_id");
                 Etablissement E = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,resp);
                AL.add(E);
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
        public int checkPartner(int id)
        {
            int resultat=0;
        Etablissement etab = null;
    try {
            String req = "select * from etablissement where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                
                 etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(20),rs.getInt(19));
                 resultat=etab.getPartenaire();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(resultat + " Hedha");
        return resultat;
    
    }
    

    public ArrayList<Etablissement> FindByTag(String T)
    {
        ArrayList<Etablissement> AL = new ArrayList<>();
        TagService TS = new TagService();
        Tag T1 = TS.findByName(T);
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Etablissement Inner Join Etablissement_Tag On Etablissement.id=Etablissement_Tag.Etablissement_Id Where Etablissement_Tag.Tag_Id=?");
        if (T1 == null)
        {
           PS.setInt(1, 0); 
        }
        else
        {
            PS.setInt(1, T1.getId());
        }
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
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
                int resp=Res.getInt("representant_id");
                Etablissement E = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,resp);
                AL.add(E);
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
    
    public ArrayList<Etablissement> FindByType(String nom)
    {
        ArrayList<Etablissement> AL = new ArrayList<>();
        try
        {
        PreparedStatement PS = connection.prepareStatement("Select * From Etablissement Where Type=?");
        PS.setString(1, nom);
        ResultSet Res = PS.executeQuery();
        while (Res.next()) 
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
                Etablissement E = new Etablissement(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15);
                AL.add(E);
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
        
        public int countVisited(int etablissement)
        {

        int res=0;
        try {
            String req = "select * from visited where favoris_id=?";
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
        
    public int countAll(){
        int res=0;
        try {
            String req = "select * from etablissement";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    public float Pourcentage(String type){
        float res=0;
        float total=0;
        try {
            String req = "select * from etablissement where Type=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
                try {
            String req2 = "select * from etablissement ";
            PreparedStatement ps2 = connection.prepareStatement(req2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                 total++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                System.out.println(res/total);   
        return res/total;
    }
    
    public int countLikes(int etablissement){
        int res=0;
        try {
            String req = "select * from wishliste where favoris_id=?";
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
    
    public ArrayList<Etablissement> getAll() {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }  
    
    public ArrayList<Etablissement> getMine(int id) {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement where id_representant=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }  
        public ArrayList<Etablissement> getFavorits(int id) {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement inner join wishliste on etablissement.id=wishliste.favoris_id where user_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }  
            public ArrayList<Etablissement> getVisits(int id) {
        ArrayList<Etablissement> etabs = new ArrayList<>();
        try {

            String req = "select * from etablissement inner join visited on etablissement.id=visited.favoris_id where user_id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etablissement etab = new Etablissement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(19));
                etabs.add(etab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etabs;
    }  
    
    public int countUsers(){
        int res=0;
        try {
            String req = "select * from user1";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
         public int countEvents(){
        int res=0;
        try {
            String req = "select * from evenement ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
         public int countOffers(){
        int res=0;
        try {
            String req = "select * from offre ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 res++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }        

    void DeleteEtablissement(int id_etablissement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Etablissement> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
                                
                
                

    } 

