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
import facebook4j.Facebook;
import entities.Offre;
import entities.Session;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
import facebook4j.conf.ConfigurationBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;
import services.implementation.OffreService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class OffreProfileController implements Initializable {

    private int id;
    @FXML
    private Text labelCode;
    @FXML
    private Label code;
    @FXML
    private Text labelPouc;
    @FXML
    private Label off;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    private Text nom;
    @FXML
    private Label deb;
    @FXML
    private Label fin;
    @FXML
    private Label des;
    @FXML
    private JFXButton modifer;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private AnchorPane MainPane;

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
                EtablissementService serviceEtab=new EtablissementService();
                     OffreService service=new OffreService();
                    Offre e=service.findById(id);
                     if (serviceEtab.checkPartner(e.getId_etablissement())==0){
                                code.setVisible(false);
                                off.setVisible(false);
                                labelCode.setVisible(false);
                                labelPouc.setVisible(false);
            }
            

        
    }    
    public void loadData(int id){
        OffreService service=new OffreService();
        Offre e=service.findById(id);
        Session ss= new Session ();
        int id_user=ss.user.id;
        EtablissementService serviceEtab=new EtablissementService();
        
        Etablissement E=serviceEtab.findById(e.getId_etablissement());
        if (id_user!=E.getResponsable()){
        modifer.setVisible(false);
        supp.setVisible(false);
        }
   
          nom.setText(e.getOffre());
          deb.setText(e.getDate_debut().toString());
          fin.setText(e.getDate_fin().toString());
          des.setText(e.getDescription()); 
          setId(id);
}

    @FXML
    private void modify(ActionEvent event) {
           try {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateOffre.fxml"));
                    Parent root = (Parent) FL.load();
                    UpdateOffreController EVC = FL.getController();
                    EVC.LoadData(id);
                    MainPane.getChildren().setAll(root);
                        
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

    @FXML
    private void delete(ActionEvent event) {
         OffreService service=new OffreService();
                        service.delete(service.findById(id));
                         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("ListOffers.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Les offres courants");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

}
   
    

