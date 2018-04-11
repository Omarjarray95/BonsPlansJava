/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Session;
import gui.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DADOU
 */
public class AboutController implements Initializable {

    @FXML
    private Label nomlabel;
    @FXML
    private MaterialDesignIconView iconClose;
    @FXML
    private Label codeemail;
    @FXML
    private JFXTextField code;
    @FXML
    private JFXButton idact;
    @FXML
    private Hyperlink pasaccees;
    @FXML
    private AnchorPane Pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                // TODO
       try{
            Session ss =new Session();
              nomlabel.setText(ss.Vnom);
        }finally{
        }  

    }    
    public void setcodeemail(String code) {
    this.codeemail.setText(code);
    }

    @FXML
    private void deconnexion(ActionEvent event) {
 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
            
            Parent root = (Parent) loader.load();
            
           LoginController logi = loader.getController();
 
           Pane.getChildren().setAll(root);
          
        } catch (IOException ex) {
            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeStage(MouseEvent event) {
    }

    @FXML
    private void activecompte(ActionEvent event) {
     if(codeemail.getText().equals(code.getText())) {
            Al("Comte Active");
        }else{
       Al("Code invalide !");
       
    }
    }
    
     public void Al(String txt){
     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(txt);
        alert.showAndWait();
    }

    
}
