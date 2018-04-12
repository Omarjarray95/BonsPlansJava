/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import entities.Session;
import java.io.File;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.implementation.EtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class HomeController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private AnchorPane AnchorResto;
    @FXML
    private HBox HBResto;
    @FXML
    private AnchorPane Pane;
    @FXML
    private TitledPane TPResto;
    @FXML
    private HBox HBShops;
    @FXML
    private HBox HBHotel;
    @FXML
    private HBox HBOther;
    @FXML
    private TitledPane TPShops;
    @FXML
    private TitledPane TPHotel;
    @FXML
    private TitledPane TPOther;
    @FXML
    private Button Ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
                try 
                {
                VBox box = FXMLLoader.load(getClass().getResource("Homepanel.fxml"));
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
                
                EtablissementService ES = new EtablissementService();
                ArrayList<Etablissement> ALE = ES.FindByType("Restaurants/Cafés");
                ArrayList<Etablissement> ALE1 = ES.FindByType("Boutiques");
                ArrayList<Etablissement> ALE2 = ES.FindByType("Hotels");
                ArrayList<Etablissement> ALE3 = ES.FindByType("Autres");
                for(int i = 0; i < 3; i++)
                {
                    VBox VB = new VBox();
                    final Etablissement E = ALE.get(i);
                    System.out.println(ALE.get(i).getId());
                    VB.setPadding(new Insets(0,10,0,10));
                    File F = new File(ALE.get(i).getImage());
                    Image I = new Image(F.toURI().toString());
                    ImageView IV = new ImageView();
                    IV.setImage(I);
                    IV.setFitHeight(130);
                    IV.setFitWidth(130);
                    Hyperlink Nom1 = new Hyperlink(ALE.get(i).getNom());
                    Nom1.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            HBResto.getChildren().add(VB);
            }
            Hyperlink AfficherTout = new Hyperlink("Afficher Tout");
                    AfficherTout.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    AffichagePaneController APC = FL.getController();
                    APC.setType("Restaurants");
                    Parent root = (Parent) FL.load();
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
                    
                HBResto.getChildren().add(AfficherTout);
                if (ALE1.size() > 0)
                {
                for(int i = 0; i < 3; i++)
                {
                    VBox VB = new VBox();
                    final Etablissement E = ALE1.get(i);
                    VB.setPadding(new Insets(0,10,0,10));
                    File F = new File(ALE1.get(i).getImage());
                    Image I = new Image(F.toURI().toString());
                    ImageView IV = new ImageView();
                    IV.setImage(I);
                    IV.setFitHeight(100);
                    IV.setFitWidth(100);
                    Hyperlink Nom1 = new Hyperlink(ALE1.get(i).getNom());
                    Nom1.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            HBShops.getChildren().add(VB);
            }
                }
                Hyperlink AfficherTout1 = new Hyperlink("Afficher Tout");
                    AfficherTout.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    Parent root = (Parent) FL.load();
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
                    
                HBShops.getChildren().add(AfficherTout1);
                if (ALE2.size() > 0)
                {
                for(int i = 0; i < 3; i++)
                {
                    VBox VB = new VBox();
                    final Etablissement E = ALE2.get(i);
                    VB.setPadding(new Insets(0,10,0,10));
                    File F = new File(ALE2.get(i).getImage());
                    Image I = new Image(F.toURI().toString());
                    ImageView IV = new ImageView();
                    IV.setImage(I);
                    IV.setFitHeight(100);
                    IV.setFitWidth(100);
                    Hyperlink Nom1 = new Hyperlink(ALE2.get(i).getNom());
                    Nom1.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            HBHotel.getChildren().add(VB);
            }
                }
                Hyperlink AfficherTout2 = new Hyperlink("Afficher Tout");
                    AfficherTout.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    Parent root = (Parent) FL.load();
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
                    
                HBHotel.getChildren().add(AfficherTout2);
                if (ALE3.size() > 0)
                {
                for(int i = 0; i < 3; i++)
                {
                    VBox VB = new VBox();
                    final Etablissement E = ALE2.get(i);
                    VB.setPadding(new Insets(0,10,0,10));
                    File F = new File(ALE3.get(i).getImage());
                    Image I = new Image(F.toURI().toString());
                    ImageView IV = new ImageView();
                    IV.setImage(I);
                    IV.setFitHeight(100);
                    IV.setFitWidth(100);
                    Hyperlink Nom1 = new Hyperlink(ALE3.get(i).getNom());
                    Nom1.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            HBOther.getChildren().add(VB);
            }
                
                }
                Hyperlink AfficherTout3 = new Hyperlink("Afficher Tout");
                    AfficherTout.setOnAction(new EventHandler<ActionEvent>() 
                    {
                    @Override
                    public void handle(ActionEvent e)
                    {
                    try 
                    {
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    Parent root = (Parent) FL.load();
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
                    
                HBOther.getChildren().add(AfficherTout3);
            TPShops.setContent(HBShops);
            TPResto.setContent(HBResto);
            TPHotel.setContent(HBHotel);
            TPOther.setContent(HBOther);
    }    

    @FXML
    private void RedirectFormulaire(ActionEvent event) throws IOException 
    {
        FXMLLoader FL = new FXMLLoader(getClass().getResource("/gui/Etablissement1.fxml"));
        Parent root = (Parent) FL.load();
        Pane.getChildren().setAll(root);
        Session S = new Session();
        System.out.println(S.user.id);
    }
    
}
