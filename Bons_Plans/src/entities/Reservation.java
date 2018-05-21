/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import techniques.DataSource;

/**
 *
 * @author HP
 */
public class Reservation {
    private int id;
    private int id_etablissement;
    private int id_user;
    private Date date;
    private String nom;
    private String prenom;
    private String num_tel;
    private int nbr_personnes;
    
   
    
    


    private Etablissement etablissement;
    private User user;
    //Les attributs DB
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formater1 = new SimpleDateFormat ("HH:mm:ss");
    Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
    private Statement stmt;

    public Reservation(int id, int id_etablissement, int id_user, Date date, String nom, String prenom, String num_tel, int nbr_personnes) {
        this.id = id;
        this.id_etablissement = id_etablissement;
        this.id_user = id_user;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.nbr_personnes = nbr_personnes;
        
        
    }

    public Reservation(int id_etablissement, int id_user, Date date, String nom, String prenom, String num_tel, int nbr_personnes) {
        this.id_etablissement = id_etablissement;
        this.id_user = id_user;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.nbr_personnes = nbr_personnes;
        
    }
    
    
    public Reservation()
    {
        
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNbr_personnes() {
        return nbr_personnes;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personnes = nbr_personnes;
    }
    public void setEtablissement(Etablissement etablissement) {
		this.etablissement=etablissement;
	}
	public Etablissement getEtablissement() {
		return etablissement;
	}

   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", id_etablissement=" + id_etablissement + ", id_user=" + id_user + ", date=" + date + ", nom=" + nom + ", prenom=" + prenom + ", num_tel=" + num_tel + ", nbr_personnes=" + nbr_personnes + '}';
    }
    

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_etablissement != other.id_etablissement) {
            return false;
        }
        return true;
    }
    
    public int ajouterReservation() {
        /**
         * Creation de Statement**
         */
        try {

            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /**
         * Creation de la Requete**
         *
         */
        String date = formater.format(this.date);
       
        
            
        String req = "INSERT INTO `reservation`(`id`, `id_etablissement`, `id_user`,"
                + " `date_de_reservation`,`nom`, "
                + "`prenom`, `numero`,`nbre_personnes`)"
                + " VALUES (" + this.id + "," + this.id_etablissement + ",'" + this.id_user + "','" 
                + date + "','" + this.nom + "','" + this.prenom + "','" 
                + this.num_tel + "'," + this.nbr_personnes + ")";
                System.out.println(req);
        try {
            /**
             * execution de la requette**
             */
            stmt.executeUpdate(req);
            return 1;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return 0;

    }
    
    public int modifierReservation() {
        /**
         * Creation de Statement**
         */
        try {

            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /**
         * Creation de la Requette**
         */
        String date = formater.format(this.date);
        
        
        
        String req = "UPDATE `reservation` SET `id_etablissement`='" + this.id_etablissement + "',`id_user`='" + this.id_user + "',`date`='" + date + "',`nom`='" + this.nom + "',`prenom`='" + this.prenom + "',`num_tel`='" + this.num_tel + "',`nbr_personnes`='" + this.nbr_personnes + "' WHERE `id`=" + this.id + ";";
        System.out.println(req);
        try {
            /*
             * execution de la requette**
             */
            stmt.executeUpdate(req);
            System.out.println("Updaaaaate jawou behi");
            return 1;
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            System.out.println("Irèère");
        }
        return 0;

    }
    
    public int rechercheReservation(int id) {

        Reservation Res = new Reservation();

        String req = "Select * From reservation where id=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, this.id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Res.id = rs.getInt(1);
                Res.id_etablissement = rs.getInt(2);
                Res.id_user = rs.getInt(3);
                Res.date = rs.getDate(4);
                Res.nom = rs.getString(5);
                Res.prenom = rs.getString(6);
                Res.num_tel = rs.getString(7);
                Res.nbr_personnes = rs.getInt(8);
                
                
            }
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;

    }
    
    public ArrayList<Reservation> displayReservation() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String req = "Select * From reservation  " ;/*+ where id_user =IHM_loginController.user.getId();*/
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservation Res = new Reservation();
                Res.id = rs.getInt("id");
                Res.id_etablissement = rs.getInt("id_etablissement");
                Res.id_user = rs.getInt("id_user");
                Res.date = rs.getDate("date");
                Res.nom = rs.getString("nom");
                Res.prenom = rs.getString("prenom");
                Res.num_tel = rs.getString("num_tel");
                Res.nbr_personnes = rs.getInt("nbr_personnes");
                
                reservations.add(Res);
            }
            return reservations;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreuuur d'execution req");
        }
        return reservations;

    }
    
    public int supprimerReservation() {
        try {
            String req = "DELETE FROM `reservation`"
                + " WHERE id = " + this.id
                + ";";
            ps = conn.prepareStatement(req);
            ps.setInt(1, this.id);
            ps.execute();

            return 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;

    }

    public Reservation getReservation(int id) {
            
     Reservation Res=new Reservation();
        String req = "Select * From reservation  where id=" + id + ";" ;/*+ where id_user =IHM_loginController.user.getId();*/
        System.out.println(req);
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
               
                Res.id = rs.getInt("id");
                Res.id_etablissement = rs.getInt("id_etablissement");
                Res.id_user = rs.getInt("id_user");
                Res.date = rs.getDate("date");
                Res.nom = rs.getString("nom");
                Res.prenom = rs.getString("prenom");
                Res.num_tel = rs.getString("num_tel");
                Res.nbr_personnes = rs.getInt("nbr_personnes");
               
               
            }
            return Res;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erreuuur d'execution req");
        }
        return Res;
    }

    public void modifierTest(String nom, String prenom, int nbr_adultes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
