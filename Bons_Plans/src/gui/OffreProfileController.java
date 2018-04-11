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
import facebook4j.Facebook;
import entities.Offre;
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
    private JFXButton fb;

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
    private TextArea des;
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
            

        
    }    
    public void loadData(int id){
   
          OffreService service=new OffreService();
          Offre e=service.findById(id);
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

    @FXML
    private void Share(ActionEvent event) {
             PostUpdate post = null;


        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAppId("2100000006903508")
                .setOAuthAppSecret("06094bbee10e79f657d5600f739c8c3e")
                .setOAuthAccessToken("EAACEdEose0cBAHShinyZALOEr5VxWtsZCwvcrBUmfMPTtzegPOcL0YdNVIjCsm8Ko8jUAOXyB3doJiHIRfa9g7iBIJZB8XJEgvvSGGUiQnx8vZCWty0F16ZAAYOtFllAeWdNvErhZA7fhPFFcrQG2154gVB8h4ogRZAfi8zNYRrEzw3kxRu6bfHhMBdwyZCPvRohAMosUE56bAZDZD")
                .setOAuthPermissions("publish_actions,manage_pages,publish_pages...");
        FacebookFactory ff = new FacebookFactory(cb.build());

        Facebook facebook = ff.getInstance();

        try {
            post = new PostUpdate(new URL("http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/offreUser/"+id))
                    .picture(new URL("http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/offreUser/"+id))
                    .name("Facebook4J - A Java library for the Facebook Graph API")
                    .caption("facebook4j.org")
                    .description("Facebook4J is a Java library for the Facebook Graph API.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {

            facebook.postFeed(post);
        } catch (FacebookException e) {
            e.printStackTrace();
       
         System.out.println("klj")   ; }
    }
}
   
    

