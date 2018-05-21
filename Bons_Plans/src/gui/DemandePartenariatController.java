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
import entities.Etablissement;
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
import services.implementation.DemandePartenariatService;
import services.implementation.EtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class DemandePartenariatController implements Initializable {
    private int id;
    @FXML
    private AnchorPane MainPane;

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
    private JFXButton submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
                VBox box = FXMLLoader.load(getClass().getResource("Homepanel.fxml"));
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
    }
    @FXML
    private void Demander(ActionEvent event) {
        DemandePartenariatService service=new DemandePartenariatService();
        EtablissementService service2 =new EtablissementService();
        Etablissement etab=service2.findById(id);
        DemandePartenariat demande=new DemandePartenariat(id,etab.getResponsable(),des.getText());
        service.add(demande);
         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
