/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.scene.text.Text;
import services.implementation.EtablissementService;
import services.implementation.LikedEtablissementService;
import services.implementation.VisitedEtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendProfileEtablissementController implements Initializable {
    private int id;
    @FXML
    private AnchorPane mainPane;

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
    private Text nom;
    @FXML
    private Text visits;
    @FXML
    private Text likes;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton offres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                         try {
                VBox box = FXMLLoader.load(getClass().getResource("HomepanelBackend.fxml"));
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


    @FXML
    private void AllEvents(ActionEvent event) {
                         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendListEventsEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendListEventsEtabController EVC = FL.getController();
                    System.out.println(id);
                    EVC.getList(id);
                    mainPane.getChildren().setAll(root);
                    
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AllOffers(ActionEvent event) {
                 try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendListOffersEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendListOffersEtabController EVC = FL.getController();
                    EVC.getList(id);
                    mainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
        public void loadData(int id){
            
    LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
    
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(id);
    nom.setText(e.getNom());
    likes.setText("Aimé par "+service3.countLikes(id)+" personne(e)");
    visits.setText("Visité par "+service3.countVisited(id)+" personne(e)");

    setId(id);
    }
    
}
