/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.DemandePartenariat;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import services.implementation.DemandePartenariatService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendProfileDemandePController implements Initializable {

     private int id;
    @FXML
    private AnchorPane main;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private JFXTextArea des;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXButton accept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
                VBox box = FXMLLoader.load(getClass().getResource("HomePanelBackend.fxml"));
                drawer.setSidePane(box);
                } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                //Hamburger
                
                HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(Hamburger);
                burgerTask.setRate(-1);
                
                Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->
                {
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                    
                    if(drawer.isShown()) drawer.close();
                    else drawer.open();
                });
       
    }    
    public void loadData(int id){
    setId(id);
      DemandePartenariatService service =new DemandePartenariatService();
        DemandePartenariat demande=service.findById(id);
        des.setText(demande.getDescription());
                
    }
    @FXML
    private void delete(ActionEvent event) {
        DemandePartenariatService service =new DemandePartenariatService();
        DemandePartenariat demande=service.findById(id);
        service.delete(demande);
                        try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendDemandesPartenaires.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendDemandesPartenairesController EVC = FL.getController();
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

    @FXML
    private void accepter(ActionEvent event) {
        DemandePartenariatService service =new DemandePartenariatService();
        DemandePartenariat demande=service.findById(id);
        service.accept(demande);
           try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendDemandesPartenaires.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendDemandesPartenairesController EVC = FL.getController();
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    
}
