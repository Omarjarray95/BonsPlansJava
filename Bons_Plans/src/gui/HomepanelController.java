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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HomepanelController implements Initializable {

    @FXML
    private JFXButton acceuil;
    @FXML
    private JFXButton actu;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton para;
    @FXML
    private JFXButton contact;
    @FXML
    private JFXButton deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SetAcceuil(ActionEvent event) throws IOException 
    {
         Parent homePage = FXMLLoader.load(getClass().getResource("Home.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void SetAct(ActionEvent event) throws IOException {
         Parent homePage = FXMLLoader.load(getClass().getResource("Actualites.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void GoProfile(ActionEvent event) throws IOException {
         Parent homePage = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void SetPara(ActionEvent event) throws IOException {
         Parent homePage = FXMLLoader.load(getClass().getResource("Parametres.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void GoContact(ActionEvent event) throws IOException {
         Parent homePage = FXMLLoader.load(getClass().getResource("Contact.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
    }

    @FXML
    private void deconnect(ActionEvent event) throws IOException  {
        Parent homePage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene homePage_scene=new Scene(homePage);
        
        Stage app_stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(homePage_scene);
        
        app_stage.show();
        
  
    }
    
}
