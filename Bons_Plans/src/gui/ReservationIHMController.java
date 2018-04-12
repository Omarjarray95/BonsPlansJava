/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Etablissement;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.implementation.ControlleurChamps;
import services.implementation.EtablissementService;
import services.implementation.ReservationService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReservationIHMController implements Initializable {

    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private TextField prenom;
    @FXML
    private Label Date_de_reservation;
   
    
    @FXML
    private Label NombrePersonnes;

    @FXML
    private Label NumTelephone;

   
    
    @FXML
    private TextField Num_tel;
    
    
    @FXML
    private DatePicker DateReserv;
    @FXML
    private TextField nom;
    @FXML
    private Button ajouter;
            Date date;
    @FXML
    private TextField NbrP;
    @FXML
    private Label erreurnom;
    @FXML
    private Label erreurprenom;
    @FXML
    private Label erreurdate;
    @FXML
    private Label erreurnum;
    @FXML
    private Label erreurnbrp;
    
    Etablissement E = null;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    private boolean verif() {
        ControlleurChamps cc = new ControlleurChamps();
        if (nom.getText().isEmpty()) {
            erreurnom.setText("Champs nom Vide");
        } else if (NbrP.getText().isEmpty()) {
            erreurnom.setText("");
            erreurnbrp.setText("Champs nombre de personne Vide");
        } else if (!cc.isNumber(NbrP.getText())) {
            erreurnom.setText("");
            erreurnbrp.setText("Nombre de personne non Valide");
        } else if (Num_tel.getText().isEmpty()) {
            erreurnom.setText("");
            erreurnbrp.setText("");
            erreurnum.setText("champs numéro de telephone Vide");
        } else if (!cc.isNum(Num_tel.getText())) {
            erreurnom.setText("");
            erreurnbrp.setText("");
            erreurnum.setText("champs numéro de telephone non Valide");
        } else if (prenom.getText().isEmpty()) {
            erreurnom.setText("");
            erreurnbrp.setText("");
            erreurnum.setText("");
            erreurprenom.setText("champs prenom Vide");
        } else if (DateReserv.getValue().toString().isEmpty()) {
            erreurnom.setText("");
            erreurnbrp.setText("");
            erreurnum.setText("");
            erreurprenom.setText("");
            erreurdate.setText("Date de reservation vide");
        
        } else {
            erreurnom.setText("");
            erreurnbrp.setText("");
            erreurnum.setText("");
            erreurprenom.setText("");
            erreurdate.setText("");
            return true;
        }
        return false;

    }
   


    @FXML
    private void ajouterReserv(ActionEvent event) 
    {
        int ID = E.getId();       
        if (verif()){
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//          SimpleDateFormat formater1 = new SimpleDateFormat ("HH:mm:ss");        
        try {
           
            date = format.parse(DateReserv.getValue().toString());
            
        } catch (ParseException ex) {

            System.out.println(ex.toString());
        }
        ReservationService rs=new ReservationService();
        rs.ajouterReservation(ID,1, date, nom.getText(), prenom.getText(), Num_tel.getText(),  Integer.parseInt(NbrP.getText()));
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("fiche de reservation ajoutée");
            alert.showAndWait();
    }
    }
//    @Override
//            public void handle(ActionEvent e)
//            {
//                try 
//                {
//                    int Id = E.getId();
//                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
//                    Parent root = (Parent) FL.load();
//                    EtablissementVBoxController EVC = FL.getController();
//                    EVC.ShowEtablissement(Id);
//                    Pane.getChildren().setAll(root);
//                } 
//                catch (IOException ex) 
//                {
//                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            });
//            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
//            VB.setAlignment(Pos.BASELINE_CENTER);
//            VB.getChildren().addAll(IV,Nom1);
//            i++;
//            if (i == 4)
//                                   {
//                break;
//            }
//            Grid.add(VB, i, j);
//        }
//                return Grid;
//            }
//        });
//    }   
    
//    @FXML
//    private void modi(ActionEvent event) throws ParseException {
//        int id = listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex()).getId_f_Dressage();
//        int id_anim = ConsulterFicheDeDressageController.iddd;//listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex()).getId_animal();
//        System.out.println(id);
//        System.out.println(id_anim);
//        if (verif()) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date datedeb = format.parse(datedep.getValue().toString());
//            Date datFin = format.parse(datef.getValue().toString());
//            Float notet = Float.parseFloat(displinetext.getText()) + Float.parseFloat(obeissancetext.getText()) + Float.parseFloat(accompagnementtext.getText()) + Float.parseFloat(interceptiontext.getText()) / 4;
//            cfdd.modifierFicheDeDressage(id, 1, specialitetext.getText(), Float.parseFloat(displinetext.getText()), Float.parseFloat(obeissancetext.getText()), Float.parseFloat(accompagnementtext.getText()), Float.parseFloat(interceptiontext.getText()), notet, datedeb, datFin, id_anim, 1);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("fiche de dressage modifier");
//            alert.showAndWait();
//            ref();
//
//        }
//    }
    
    
    public void GetE(int ID)
    {
        EtablissementService ES = new EtablissementService();
        E = ES.findById(ID);
        System.out.println(E.getNom());
    }
}