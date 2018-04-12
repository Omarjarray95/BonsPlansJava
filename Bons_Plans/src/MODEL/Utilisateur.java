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
public class Utilisateur {

    private int ID;
    private String Email;
    private String username;
    private String Password;
    private String Nom;
    private String Prenom;
    private String Telephone;
    private String Photo;
    private String Sexe;
    private Date Date_Creation;
    private String Matricule;
    private String Role;
    private int ID_CLUB;
    private boolean Block;

    public Utilisateur(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe, String Matricule, Boolean Block) {
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Sexe = Sexe;

        this.Matricule = Matricule;
        this.Block = Block;

    }

    public Utilisateur(int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Role, Boolean Block) {
        this.ID = ID;
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Photo = Photo;
        this.Sexe = Sexe;
        this.Matricule = Matricule;
        this.Role = Role;
        this.Block = Block;
    }

    public Utilisateur(int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Role) {
        this.ID = ID;
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Photo = Photo;
        this.Sexe = Sexe;
        this.Matricule = Matricule;
        this.Role = Role;

    }

    public Utilisateur() {

    }

    public Utilisateur(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, Boolean Block) {
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Photo = Photo;
        this.Sexe = Sexe;
        this.Matricule = Matricule;
        this.Block = Block;
    }

    public Utilisateur(int ID, String Email, String username, String Telephone, String Photo) {
        this.ID = ID;
        this.Email = Email;
        this.username = username;
        this.Telephone = Telephone;
        this.Photo = Photo;
    }

    public Utilisateur(int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, Date Date_Creation, String Matricule, String Role, int ID_CLUB, Boolean Block) {
        this.ID = ID;
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Photo = Photo;
        this.Sexe = Sexe;
        this.Date_Creation = Date_Creation;
        this.Matricule = Matricule;
        this.Role = Role;
        this.ID_CLUB = ID_CLUB;
        this.Block = Block;

    }

    public Utilisateur(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, int ID_CLUB, Boolean Block) {
        this.Email = Email;
        this.username = username;
        this.Password = Password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Telephone = Telephone;
        this.Photo = Photo;
        this.Sexe = Sexe;
        this.ID_CLUB = ID_CLUB;
        this.Block = Block;
    }

    public boolean isBlock() {
        return Block;
    }

    public void setBlock(boolean Block) {
        this.Block = Block;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public Date getDate_Creation() {
        return Date_Creation;
    }

    public void setDate_Creation(Date Date_Creation) {
        this.Date_Creation = Date_Creation;
    }

    public String getRole() {
        System.out.println(Role);
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    

    public int getID_CLUB() {
        return ID_CLUB;
    }

    public void setID_CLUB(int ID_CLUB) {
        this.ID_CLUB = ID_CLUB;

    }

    @Override
    public String toString() {
        return "Utilisateur{" + "ID=" + ID + ", Email=" + Email + ", username=" + username + ", Password=" + Password + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Telephone=" + Telephone + ", Photo=" + Photo + ", Sexe=" + Sexe + ", Date_Creation=" + Date_Creation + ", Matricule=" + Matricule + ", Role=" + Role + ", ID_CLUB=" + ID_CLUB + ", Block=" + Block + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Utilisateur other = (Utilisateur) obj;
        return this.ID == other.ID;
    }

}
