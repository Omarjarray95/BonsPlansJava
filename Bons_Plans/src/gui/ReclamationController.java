/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Etablissement;
import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import services.implementation.ServiceReclamation;
import entities.Session;
import entities.User;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.implementation.EtablissementService;


/**
 * FXML Controller class
 *
 * @author DADOU
 */
public class ReclamationController implements Initializable {

    @FXML
    private JFXButton envoyer;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXComboBox<Etablissement> comboetablissement;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbprenom;
    @FXML
    private MaterialDesignIconView iconClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      Session ss =new Session();
              lbnom.setText(ss.user.nom);
        EtablissementService s =new EtablissementService();
      
        for (Etablissement etab : s.afficher()) {
           
            comboetablissement.getItems().add(etab);
            
        }
    }
    @FXML
    private void envoyerec(ActionEvent event) throws SQLException {
        ServiceReclamation rc=new ServiceReclamation();
        User u=new User();
        Session ses=new Session();
        Reclamation r=new Reclamation();
        
        r.setId_user(ses.user.id);
        r.setDescription(description.getText());
        r.setId_etablissement(0);
      r.setId_etablissement(comboetablissement.getSelectionModel().getSelectedItem().getId());
       
          rc.addReclamation(r) ;     
        
    }

    @FXML
    private void closeStage(MouseEvent event) {
         lbnom.getScene().getWindow().hide();
    }
    
    
}
