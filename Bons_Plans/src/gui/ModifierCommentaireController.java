/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXTextArea;
import entities.Commentaire;
import entities.Etablissement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.implementation.CommentaireService;
import services.implementation.EtablissementService;
import techniques.DataSource;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class ModifierCommentaireController implements Initializable {

    

    @FXML
    public JFXTextArea comment;
    @FXML
    public Button modif;
    int id;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private Pane pane;
    @FXML
    private ImageView img4;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
    public void ModifCommentaire(int Id,int id_etab)
    {   
        EtablissementVBoxController EVC = new EtablissementVBoxController();
        CommentaireService CS =new CommentaireService();
        Commentaire C = CS.findById(Id);
        comment.setText(C.getComment());
        modif.setOnAction(new EventHandler<ActionEvent>() 
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
             CommentaireService CS =new CommentaireService();

        CS.update(
                Id,
                comment.getText());
        EVC.afficherComm();

        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
        });}
    
    public int recupererCom(int id){
        return id; 
    }
      

    @FXML
    private void Enregistrer(ActionEvent event) {
        
        

    }
    
}
