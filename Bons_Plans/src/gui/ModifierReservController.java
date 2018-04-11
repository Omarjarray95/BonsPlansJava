/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.implementation.ReservationService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierReservController implements Initializable {

    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    ReservationService rs=new ReservationService();
    @FXML
    private Button modifier;
    @FXML
    private Label Date_de_reservation;
    @FXML
    private Label NombrePersonnes;
    @FXML
    private Label DateArrivée;
    @FXML
    private Label DateDépart;
    @FXML
    private Label NumTelephone;
    @FXML
    private TextField Num_tel;
    @FXML
    private TextField NbrP;
    @FXML
    private DatePicker DateReserv;
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date datedeb;
           
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        System.out.println(Reservation_AfficheController.idZbotrech);
         Reservation m = rs.getReservation(Reservation_AfficheController.idZbotrech);
         System.out.println(m);
         nom.setText(m.getNom());
         prenom.setText(m.getPrenom());
         Num_tel.setText(""+m.getNum_tel());
         NbrP.setText(""+m.getNbr_personnes());
         
         LocalDate ld = LocalDate.parse(m.getDate().toString());
         DateReserv .setValue(ld);     
         
         
        
  
         
    }    

    @FXML
    private void modi(ActionEvent event) {
        
        try {
            datedeb = format.parse(DateReserv.getValue().toString());
            
            
           
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
        
//         rs.modifierReservation(nom.getText(),prenom.getText(),Integer.parseInt(nbrA.getText()));
         rs.modifierReservation(Reservation_AfficheController.idZbotrech,1,1, datedeb, nom.getText(), prenom.getText(), Num_tel.getText(), Integer.parseInt(NbrP.getText()));
    }
    
}
