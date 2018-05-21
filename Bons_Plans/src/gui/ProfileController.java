/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import entities.Evenement;
import entities.Session;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import services.implementation.EvenementService;
import services.implementation.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class ProfileController implements Initializable 
{
    private List<Etablissement> etabss;
    private List<Evenement> events;
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
    @FXML
    private JFXButton reservation;
    private JFXButton btnRestore1;
    @FXML
    private JFXTextField emailu;
    @FXML
    private JFXTextField phoneu;
    @FXML
    private JFXTextField nomu;
    @FXML
    private JFXTextField usernameu;
    @FXML
    private JFXButton savee1;
    @FXML
    private JFXButton reclam;
    @FXML
    private Label lbnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Session ss =new Session();
        lbnom.setText(ss.user.login);
        usernameu.setText(ss.user.login);
        emailu.setText(ss.user.Email);
        nomu.setText(ss.user.nom);
        phoneu.setText(Integer.toString(ss.user.tel));
        System.out.println(ss.user.tel);
        try 
        {
                VBox box = FXMLLoader.load(getClass().getResource("Homepanel.fxml"));
                drawer.setSidePane(box);
                } 
        catch (IOException ex) 
        {
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
        Session ss =new Session();
        lbnom.setText(ss.user.nom);
        usernameu.setText(ss.user.login);
        emailu.setText(ss.user.Email);
        nomu.setText(ss.user.nom);
        phoneu.setText(Integer.toString(ss.user.tel));
    }

    @FXML
    private void showMine(ActionEvent event) 
    {
        Main.getChildren().clear();
        Session ss = new Session();
                EtablissementService service =new EtablissementService();
                etablis=service.getMine(ss.user.id);
                if (etablis.isEmpty())
                {
                Label box = new Label();
                box.setText("Vous Ne Possédez Pas D'Etablissement Pour Le Moment");
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
                  FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
                    
                   FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
    private void showDemandes(ActionEvent event) 
    {
        Main.getChildren().clear();
    }

    @FXML
    private void showFavoris(ActionEvent event) {
          Session ss= new Session ();
        int id_user=ss.user.id;
        Main.getChildren().clear();
                EtablissementService service =new EtablissementService();
                etablis=service.getFavorits(id_user);
                if (etablis.isEmpty()){
                           Label box = new Label();
         
                box.setText("Vous N'Aimez Aucun Etablissement");
                box.setVisible(true);
                box.setLayoutX(14.0);
                box.setLayoutY(103.0);
                
                Main.getChildren().addAll(box);
                }
                else{
           pagination = new Pagination(Math.round(etablis.size()/6)+1, 0);
           pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
           pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           VBox box = new VBox(5);
         
          box.setLayoutY(14.0);
          box.setLayoutX(14.0);
          box.setPrefHeight(240.0);
          box.setPrefWidth(334.0);
          int page = pageIndex * 6;
        
        
             if (page+6>etablis.size()){
            for (int i = page; i < etablis.size(); i++){
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
                    
                   FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
                   else{
                               for (int i = page; i < page + 6; i++){
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
                    
                   FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
    private void showVisited(ActionEvent event) {
        Main.getChildren().clear();
          Session ss= new Session ();
        int id_user=ss.user.id;
        EtablissementService service =new EtablissementService();
                etablis=service.getVisits(id_user);
                if (etablis.isEmpty()){
                           Label box = new Label();
         
                box.setText("Vous N'avez Visité Aucun Etablissement");
                box.setVisible(true);
                box.setLayoutX(14.0);
                box.setLayoutY(103.0);
                
                Main.getChildren().addAll(box);
                }
                else{
           pagination = new Pagination(Math.round(etablis.size()/6)+1, 0);
           pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
           pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           VBox box = new VBox(5);
         
          box.setLayoutY(14.0);
          box.setLayoutX(14.0);
          box.setPrefHeight(240.0);
          box.setPrefWidth(334.0);
          int page = pageIndex * 6;
        
        
             if (page+6>etablis.size()){
            for (int i = page; i < etablis.size(); i++){
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
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
                   else{
                               for (int i = page; i < page + 6; i++){
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
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
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
    private void showReclamations(ActionEvent event) {
         Main.getChildren().clear();
    }

    @FXML
    private void showInteresting(ActionEvent event) {
                Main.getChildren().clear();
          Session ss= new Session ();
        int id_user=ss.user.id;
        EvenementService service =new EvenementService();
                events=service.getInteresting(id_user);
                if (events.isEmpty()){
                           Label box = new Label();
         
                box.setText("Vous N'Etes Pas Intéressé Par Aucun Evenement");
                box.setVisible(true);
                box.setLayoutX(14.0);
                box.setLayoutY(103.0);
                
                Main.getChildren().addAll(box);
                }
                else{
           pagination = new Pagination(Math.round(etablis.size()/6)+1, 0);
           pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
           pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           VBox box = new VBox(5);
         
          box.setLayoutY(14.0);
          box.setLayoutX(14.0);
          box.setPrefHeight(240.0);
          box.setPrefWidth(334.0);
          int page = pageIndex * 6;
        
        
             if (page+6>events.size()){
            for (int i = page; i < events.size(); i++){
        Evenement e=events.get(i);
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
        
        
        box.getChildren().add(fromToLabel2);
                }}
                   else{
                               for (int i = page; i < page + 6; i++){
        Evenement e=events.get(i);
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
    private void showGoing(ActionEvent event) {
           Main.getChildren().clear();
          Session ss= new Session ();
        int id_user=ss.user.id;
        EvenementService service =new EvenementService();
                events=service.getGoing(id_user);
                if (events.isEmpty()){
                           Label box = new Label();
         
                box.setText("Vous N'Avez Pas Des Evenements à Visiter");
                box.setVisible(true);
                box.setLayoutX(14.0);
                box.setLayoutY(103.0);
                
                Main.getChildren().addAll(box);
                }
                else{
           pagination = new Pagination(Math.round(events.size()/6)+1, 0);
           pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
           pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           VBox box = new VBox(5);
         
          box.setLayoutY(14.0);
          box.setLayoutX(14.0);
          box.setPrefHeight(240.0);
          box.setPrefWidth(334.0);
          int page = pageIndex * 6;
        
        
             if (page+6>events.size()){
            for (int i = page; i < events.size(); i++){
        Evenement e=events.get(i);
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
        
        
        box.getChildren().add(fromToLabel2);
                }}
                   else{
                               for (int i = page; i < page + 6; i++){
        Evenement e=events.get(i);
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
    private void updatecompte(ActionEvent event) {
    }

     @FXML
    private void saveActio(ActionEvent event) throws ClassNotFoundException 
    {
        try 
        {
            ServiceUser ser=new ServiceUser();
            User us=new User();
            ser.updateUser(phoneu.getText(),nomu.getText(),usernameu.getText(),lbnom.getText(),emailu.getText());
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(test.CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void envoyerrecla(ActionEvent event) {
    }

    @FXML
    private void showReserved(ActionEvent event) {
    }
    }    
    

