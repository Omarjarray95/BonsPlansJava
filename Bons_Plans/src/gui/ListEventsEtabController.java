/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;
import services.implementation.OffreService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class ListEventsEtabController  implements Initializable {

    private  int id;
    @FXML
    private JFXTextField searchText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private List<Evenement> events;
    @FXML
    private Label labelEvent;
    @FXML
    private Hyperlink event;
    @FXML
    private Label date;
    @FXML
    private Separator sep;
    @FXML
    private VBox VboxEvent;
    @FXML
    private AnchorPane EventsAnchorPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private AnchorPane MainPane;

    /**
     * Initializes the controller class.
     */
   

    public void getList(int id_etab,String mot) throws IOException{
         EtablissementService etabService=new EtablissementService();
         Etablissement e=etabService.findById(id_etab);
        labelEvent.setText("Les évenements actuels de "+e.getNom());
        EvenementService service =new EvenementService();
        EventsAnchorPane.setVisible(false);
        events=service.getAllByEtabMot(id_etab,mot);
        if(!events.isEmpty()){

                for (Evenement eve : events) {

       
        AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(EventsAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(EventsAnchorPane.getEffect());

       
        //fromtolabel      
        Hyperlink fromToLabel2 = new Hyperlink(eve.getNom());
        fromToLabel2.setFont(event.getFont());
        fromToLabel2.setLayoutX(event.getLayoutX());
        fromToLabel2.setLayoutY(event.getLayoutY());
        fromToLabel2.setText(eve.getNom());
                    fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = eve.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EvenementProfile.fxml"));
                    Parent root = (Parent) FL.load();
                    EvenementProfileController EVC = FL.getController();
                    EVC.loadData(Id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        Label fromToLabel3 = new Label();
        fromToLabel3.setFont(date.getFont());
        fromToLabel3.setLayoutX(date.getLayoutX());
        fromToLabel3.setLayoutY(date.getLayoutY());
        fromToLabel3.setText(eve.getDate().toLocalDate().toString());
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,fromToLabel3);
        VboxEvent.getChildren().add(newAnnoncesAnchorPane);
        setId(id_etab);
                }}}
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
    public static void main(String[] args) 
    {
        launch(args);
        
    }

    @FXML
    private void ChangeList(KeyEvent event) throws IOException {
                
        VboxEvent.getChildren().clear();
        getList(id,searchText.getText());
                
    }
    
    
}
