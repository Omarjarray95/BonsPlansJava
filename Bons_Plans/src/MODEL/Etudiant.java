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
public class Etudiant extends Utilisateur{

    private String Classe;

    

    
    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public Etudiant(String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Sexe, String Classe,String Matricule,  Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Sexe, Matricule, Block);
        this.Classe = Classe;
    }

    public Etudiant( int ID, String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Matricule, String Classe,String Role, Boolean Block) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Role, Block);
        this.Classe = Classe;
    }

    public Etudiant(String Classe) {
        this.Classe = Classe;
    }

    public Etudiant( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe, String Classe,String Matricule, Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Matricule, Block);
        this.Classe = Classe;
    }

    public Etudiant( int ID, String Email, String username, String Password, String Nom, String Prenom,
            String Telephone, String Photo, String Sexe, Date Date_Creation, String Classe,String Matricule,
            String Role, int ID_CLUB, Boolean Block) {
        super(ID, Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, Date_Creation, Matricule, Role, ID_CLUB, Block);
        this.Classe = Classe;
    }

    public Etudiant( String Email, String username, String Password, String Nom, String Prenom, String Telephone, String Photo, String Sexe,String Classe, int ID_CLUB, Boolean Block) {
        super(Email, username, Password, Nom, Prenom, Telephone, Photo, Sexe, ID_CLUB, Block);
        this.Classe = Classe;
    }

    public Etudiant() {
    }

    @Override
    public String toString() {
        return super.toString()+"Classe "+Classe; //To change body of generated methods, choose Tools | Templates.
    }

  
   

   
}
