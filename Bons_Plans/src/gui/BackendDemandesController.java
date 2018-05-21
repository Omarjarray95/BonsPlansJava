/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Demande;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.implementation.DemandeService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendDemandesController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private VBox VBDemandes;
    @FXML
    private AnchorPane Pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            VBox box = FXMLLoader.load(getClass().getResource("HomePanelBackend.fxml"));
            drawer.setSidePane(box);
            } 
        catch (IOException ex) 
        {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(Hamburger);
            burgerTask.setRate(-1);
                
            Hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e ->
                {
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                    if(drawer.isShown()) drawer.close();
                    else drawer.open();
                });
        
        DemandeService DS = new DemandeService();
        ArrayList<Demande> ALD = DS.GetAllDemands();
        for(Demande D:ALD)
        {
            Hyperlink HLD = new Hyperlink(D.getNom());
            VBDemandes.getChildren().add(HLD);
            HLD.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                FXMLLoader FL = new FXMLLoader(getClass().getResource("Etablissement1.fxml"));
                try 
                {
                Parent root = (Parent) FL.load();
                Pane.getChildren().setAll(root);
                EtablissementController EC = FL.getController();
                EC.ModifDEtablissement(D.getId());
                } 
                catch (IOException ex) 
                {
                Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
            
        }
    }    
    
}
