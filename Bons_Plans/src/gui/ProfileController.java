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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.implementation.EtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class ProfileController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton etabs;
    @FXML
    private JFXButton demandes;
    @FXML
    private JFXButton Favoris;
    @FXML
    private JFXButton visited;
    @FXML
    private JFXButton reclamations;
    @FXML
    private JFXButton interesting;
    @FXML
    private JFXButton going;
    @FXML
    private AnchorPane Main;
    private Pagination pagination;
    private List<Etablissement> etablis;

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
    private void showProfile(ActionEvent event) 
    {
        FXMLLoader FL= new FXMLLoader(getClass().getResource("Compte.fxml"));
        try
        {
            Parent root = (Parent) FL.load();
            MainPane.getChildren().setAll(root);
        }
        catch(IOException ex)
        {
        System.out.println(ex);
        }
    }

    @FXML
    private void showMine(ActionEvent event) 
    {
                EtablissementService service =new EtablissementService();
                etablis=service.getMine(2);
                if (etablis.isEmpty())
                {
                Label box = new Label();
                box.setText("Vous ne possédez pas d'établissement");
                box.setVisible(true);
                box.setLayoutX(14.0);
                box.setLayoutY(103.0);
                JFXButton ajout =new JFXButton();
                ajout.setLayoutX(244.0);
                ajout.setLayoutY(99.0);
                ajout.setStyle(going.getStyle());
                ajout.setText("Demander");
                ajout.setTextFill(going.getTextFill());
                Main.getChildren().addAll(box,ajout);
                }
                else
                {
                pagination = new Pagination(Math.round(etablis.size()/6)+1, 0);
                pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
                pagination.setPageFactory(new Callback<Integer, Node>() {
                public Node call(Integer pageIndex) 
                {
                VBox box = new VBox(5);
                box.setLayoutY(14.0);
                box.setLayoutX(14.0);
                box.setPrefHeight(240.0);
                box.setPrefWidth(334.0);
                int page = pageIndex * 6;
                
             if (page+6>etablis.size())
             {
                for (int i = page; i < etablis.size(); i++)
                {
                Etablissement e=etablis.get(i);
                Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
                fromToLabel2.setText(e.getNom());
                
                fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
                {
                @Override
                public void handle(ActionEvent action)
                {
                try 
                {
                    int Id=e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    ProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        box.getChildren().add(fromToLabel2);
             }
             }
                   else
             {
                   for (int i = page; i < page + 6; i++)
                   {
                    Etablissement e=etablis.get(i);
                    Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
                    fromToLabel2.setText(e.getNom());
                    fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                try 
                {
                    int Id=e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    ProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        box.getChildren().add(fromToLabel2);
                }}    
    return box;
       }
   });
                AnchorPane.setTopAnchor(pagination, 10.0);
                AnchorPane.setRightAnchor(pagination, 10.0);
                AnchorPane.setBottomAnchor(pagination, 10.0);
                AnchorPane.setLeftAnchor(pagination, 10.0);
                Main.getChildren().addAll(pagination);
                }
    }

    @FXML
    private void showDemandes(ActionEvent event) {
    }

    @FXML
    private void showFavoris(ActionEvent event) {
    }

    @FXML
    private void showVisited(ActionEvent event) {
    }

    @FXML
    private void showReclamations(ActionEvent event) {
    }

    @FXML
    private void showInteresting(ActionEvent event) {
    }

    @FXML
    private void showGoing(ActionEvent event) {
    }
    }    
    

