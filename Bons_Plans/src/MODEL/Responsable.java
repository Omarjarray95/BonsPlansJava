/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author OrbitG
 */
public class Responsable extends Utilisateur {

    private String Nom_Club;
    private String Photo_Club;

    public String getNom_Club() {
        return Nom_Club;
    }

    public void setNom_Club(String Nom_Club) {
        this.Nom_Club = Nom_Club;
    }

    public String getPhoto_Club() {
        return Photo_Club;
    }

    public void setPhoto_Club(String Photo_Club) {
        this.Photo_Club = Photo_Club;
    }

    public Responsable() {
    }

    public Responsable( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe, String Matricule, String Nom_Club, String Photo_Club,Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe, Matricule, Block);
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

    public Responsable( int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Role,String Nom_Club, String Photo_Club, Boolean Block) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Role, Block);
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

    public Responsable(String Nom_Club, String Photo_Club) {
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

    public Responsable( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule,String Nom_Club, String Photo_Club, Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Block);
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

    public Responsable( int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, Date Date_Creation,String Nom_Club, String Photo_Club, String Matricule, String Role, int ID_CLUB, Boolean Block) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Date_Creation, Matricule, Role, ID_CLUB, Block);
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

    public Responsable(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe,String Nom_Club, String Photo_Club, int ID_CLUB, Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, ID_CLUB, Block);
        this.Nom_Club = Nom_Club;
        this.Photo_Club = Photo_Club;
    }

   
}
