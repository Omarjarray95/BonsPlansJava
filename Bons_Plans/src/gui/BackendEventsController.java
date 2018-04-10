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
import entities.Evenement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendEventsController implements Initializable {
    private List<Evenement> events;
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
    private Hyperlink nom;
    @FXML
    private AnchorPane main;

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
        EvenementService service =new EvenementService();
        events=service.getAll();
        etabPane.setVisible(false);
        if(!events.isEmpty()){

                for (Evenement e : events) {

       
        AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(etabPane.getStyle());
        newAnnoncesAnchorPane.setEffect(etabPane.getEffect());
        
       
        //fromtolabel      
        Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
        fromToLabel2.setStyle(nom.getStyle());
        fromToLabel2.setText(e.getNom());
        fromToLabel2.setLayoutX(nom.getLayoutX());
        fromToLabel2.setLayoutY(nom.getLayoutY());
        fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                try 
                {
                    int Id = e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendProfileEvenement.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendProfileEvenementController EVC = FL.getController();
                    EVC.loadData(Id);
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
        JFXButton suppButton=new JFXButton();
        suppButton.setStyle(supp.getStyle());
        suppButton.setText(supp.getText());
        suppButton.setTextFill(supp.getTextFill());
        suppButton.setLayoutX(supp.getLayoutX());
        suppButton.setLayoutY(supp.getLayoutY());
        suppButton.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                
                    EvenementService service=new EvenementService();
                    service.delete(service.findById(e.getId()));
                         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("BackendEvents.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) action.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Admin - Les évenements courants");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
            }
            });
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,suppButton);
        mainPane.getChildren().add(newAnnoncesAnchorPane);
        
                }}
    }    

    
    
}
