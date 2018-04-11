/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import services.implementation.CommentaireService;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class SignalerCommentaireController implements Initializable {

    @FXML
    private Button Envoyer;
    @FXML
    private ToggleGroup TG;
    @FXML
    private RadioButton Grossier;
    @FXML
    private RadioButton Spam;
    @FXML
    private RadioButton PlusPertinent;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Grossier.setUserData("Grossier");
        Spam.setUserData("Spam");
        PlusPertinent.setUserData("PlusPertinent");
        Grossier.setToggleGroup(TG);
        Spam.setToggleGroup(TG);
        PlusPertinent.setToggleGroup(TG);
    }    
    
    
    public void SignalerCommentaire(int Id, int id_etab)
    {
        Envoyer.setOnAction(new EventHandler<ActionEvent>() 
        {
        @Override 
        public void handle(ActionEvent e) 
        {
            
             
        FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
        try 
        {
            Parent root = (Parent) FL.load();
            EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(id_etab);
            pane.getChildren().setAll(root);
              String S=""; 
        CommentaireService CS = new CommentaireService();
        if (TG.getSelectedToggle() != null )
        {
        S = TG.getSelectedToggle().getUserData().toString();
        }
               CS.Signaler(Id,S);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }         
        }
        });
    
    }

    @FXML
    private void Envoyer(ActionEvent event) {
       
    }
    
}
