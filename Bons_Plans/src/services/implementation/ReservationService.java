/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

import entities.Reservation;
import java.util.Date;
import java.util.ArrayList;


public class ReservationService {
   

     public int ajouterReservation(int id_etablissement, int id_user, Date date, String nom, String prenom, String num_tel, int nbr_personnes) {
        Reservation reserv = new Reservation(id_etablissement, id_user , date , nom, prenom , num_tel , nbr_personnes);
        return reserv.ajouterReservation();
        
    }
      public int modifierReservation(int id, int id_etablissement, int id_user,Date date, String nom, String prenom, String num_tel, int nbr_personnes) {
         Reservation reserv = new Reservation(id ,id_etablissement, id_user , date , nom, prenom , num_tel , nbr_personnes);
        return reserv.modifierReservation();
    }
      public ArrayList AfficherReservation() {
        Reservation reserv = new Reservation();
        return reserv.displayReservation();
    }
       public int supprimmerReservation(Reservation rs) {
        Reservation reserv = new Reservation(rs.getId_etablissement(), rs.getId_user(), rs.getDate(), 
        rs.getNom(), rs.getPrenom(), rs.getNum_tel(), rs.getNbr_personnes());
        return reserv.supprimerReservation();
    }

    public Reservation getReservation(int x) {
      Reservation reserv = new Reservation();
        System.out.println(x+"ahahaahah");
    return reserv.getReservation(x);
    }

    public int modifiertest(String nom, String prenom, int nbr_adultes) {
            Reservation reserv = new Reservation();
            return reserv.modifierReservation();
    }
          
     
       
    
}

