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
import entities.DemandePartenariat;
import entities.Etablissement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import services.implementation.DemandePartenariatService;
import services.implementation.EtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendDemandesPartenairesController implements Initializable {

    private List<DemandePartenariat> demandes;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private VBox mainPane;
    @FXML
    private AnchorPane etabPane;
    @FXML
    private JFXButton supp;
    @FXML
    private Hyperlink demande;
    @FXML
    private AnchorPane main;
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
                try {
                 getList();
                 
                 // TODO
             } catch (IOException ex) {
                 Logger.getLogger(ListEventsController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }    
       public void getList() throws IOException{
        DemandePartenariatService service =new DemandePartenariatService();
        demandes=service.getAll();
        etabPane.setVisible(false);
        if(!demandes.isEmpty()){

                for (DemandePartenariat e : demandes) {

       
        AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(etabPane.getStyle());
        newAnnoncesAnchorPane.setEffect(etabPane.getEffect());
        EtablissementService service2=new EtablissementService();
        Etablissement etab=service2.findById(e.getEtab());
       
        //fromtolabel      
        Hyperlink fromToLabel2 = new Hyperlink(etab.getNom());
        fromToLabel2.setStyle(demande.getStyle());
        fromToLabel2.setText(etab.getNom());
        fromToLabel2.setLayoutX(demande.getLayoutX());
        fromToLabel2.setLayoutY(demande.getLayoutY());
        fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                try 
                {
                    int Id = e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendProfileDemandeP.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendProfileDemandePController EVC = FL.getController();
                    EVC.loadData(Id);
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2);
        mainPane.getChildren().add(newAnnoncesAnchorPane);
        
                }}}

  
    
}
