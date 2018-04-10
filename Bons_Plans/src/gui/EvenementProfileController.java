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
import java.sql.Date;
import java.time.LocalDate;
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
import services.implementation.GoingEventService;
import services.implementation.InterestedEventService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class EvenementProfileController implements Initializable {
    public int Id;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Hyperlink adresse;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    @FXML
    private ToggleButton interest;
    @FXML
    private ToggleButton going;
    InterestedEventService service1=new InterestedEventService();
    GoingEventService service2=new GoingEventService();
    @FXML
    private Label date;
    @FXML
    private Text nom;
    @FXML
    private TextArea des;
    @FXML
    private Text interests;
    @FXML
    private Text goings;
    @FXML
    private JFXButton modifer;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
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

    @FXML
    private void SetInterest(ActionEvent event) {
        if (interest.getText().equals("Intéressé(e)")){
        service1.delete(3, Id);
        interest.setText("Marquer intéressé(e)");
        }
        else {service1.add(3,Id);
        if (going.getText().equals("Partant(e)")){
        service2.delete(3, Id);
        going.setText("Marquer partant(e)");
        }
        interest.setText("Intéressé(e)");
        }
        EvenementService service=new EvenementService();
        Evenement e=service.findById(Id);
        goings.setText(e.getNbr_personnes()+" personnes partantes");
        interests.setText(e.getInteresses()+" personnes intéreéssées");
    }

    @FXML
    private void SetGoing(ActionEvent event) {
        if (going.getText().equals("Partant(e)")){
        service2.delete(3,Id);
        going.setText("Marquer partant(e)");}
        else {
        service2.add(3,Id);
        if (interest.getText().equals("Intéressé(e)")){
        service1.delete(3,Id );
        interest.setText("Marquer intéressé(e)");
        }
        going.setText("Partant(e)");
        }
        EvenementService service=new EvenementService();
        Evenement e=service.findById(Id);
        goings.setText(e.getNbr_personnes()+" personnes partantes");
        interests.setText(e.getInteresses()+" personnes intéreéssées");
    }
    public void loadData(int in){
        
    if (service1.count(3, in)==0){
    interest.setText("Marquer intéressé(e)");}
    else interest.setText("Intéressé(e)");
    if (service2.count(3, in)==0){
    going.setText("Marquer partant(e)");}
    else going.setText("Partant(e)");
    
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
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    ProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id_etab);
                    MainPane.getChildren().setAll(root);
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
    private void modify(ActionEvent event) {
 try {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateEvent.fxml"));
                    Parent root = (Parent) FL.load();
                    UpdateEventController EVC = FL.getController();
                    System.out.println(Id);
                    EVC.LoadData(Id);
                    MainPane.getChildren().setAll(root);
                        
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

    @FXML
    private void delete(ActionEvent event) {
                        EvenementService service=new EvenementService();
                        service.delete(service.findById(Id));
                         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("ListEvents.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Les évenements courants");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }
    
}
