/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class HomePanelBackendController implements Initializable {

    @FXML
    private JFXButton dash;
    @FXML
    private JFXButton Etabs;
    @FXML
    private JFXButton Events;
    @FXML
    private JFXButton Offres;
    @FXML
    private JFXButton demandes;
    @FXML
    private JFXButton mail;
    @FXML
    private JFXButton Reclamations;
    @FXML
    private JFXButton demandePartenariat;
    @FXML
    private JFXButton partenaires;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SetDash(ActionEvent event) throws IOException  {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendAdminAcceuil.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void SetEtabs(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendEtabs.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void SetEvents(ActionEvent event) throws IOException  {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendEvents.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }


    @FXML
    private void SetOffres(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendOffres.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void SetMail(ActionEvent event) throws IOException {
    }

    @FXML
    private void SetReclamations(ActionEvent event) throws IOException {
    }

    @FXML
    private void SetDemandes(ActionEvent event) {
    }

    @FXML
    private void AfficherDemandesPartenariat(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendDemandesPartenaires.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void showPartners(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("BackendPartenaires.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }
    
}
