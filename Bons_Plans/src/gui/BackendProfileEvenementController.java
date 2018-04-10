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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendProfileEvenementController implements Initializable {
    public int Id;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    @FXML
    private Text nom;
    @FXML
    private Text interests;
    @FXML
    private Text goings;
    @FXML
    private TextArea des;
    @FXML
    private Label date;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private Hyperlink adresse;
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
    }    
    
    public void loadData(int in){
        
  
          EvenementService service=new EvenementService();
          Evenement e=service.findById(in);
          System.out.println(in);
          EtablissementService etabService=new EtablissementService();
          Etablissement etab=etabService.findById(e.getId_etablissement());
          System.out.println(etab.getNom());
                  adresse.setText(etab.getNom());
                    adresse.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id_etab = etab.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id_etab);
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
          nom.setText(e.getNom());
          date.setText(e.getDate().toLocalDate().toString());
          des.setText(e.getDescription()); 
          goings.setText(e.getNbr_personnes()+" personnes partantes");
          interests.setText(e.getInteresses()+" personnes intéreéssées");
          setId(in);
    }


    @FXML
    private void delete(ActionEvent event) {
                        EvenementService service=new EvenementService();
                        service.delete(service.findById(Id));
                         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("BackendEvents.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Admin - Les évenements courants");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }        
    }
    
}
