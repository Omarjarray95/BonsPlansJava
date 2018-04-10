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
import entities.LikedEtablissement;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.implementation.DemandePartenariatService;
import services.implementation.EtablissementService;
import services.implementation.InterestedEventService;
import services.implementation.LikedEtablissementService;
import services.implementation.PartenariatService;
import services.implementation.VisitedEtablissementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class ProfileEtablissementController implements Initializable {
    private int id;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Text nom;
    @FXML
    private Text visits;
    @FXML
    private Text likes;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton Demande;
    @FXML
    private Label labelPartenaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @FXML
    private ToggleButton like;
    @FXML
    private ToggleButton visited;
    

    @FXML
    private Button event;
    @FXML
    private Button offre;
    @FXML
    private Button offres;
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
    private void SetLike(ActionEvent event) {
            
    LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
    if (like.getText().equals("J'aime")){
    service1.add(id, 3);
    like.setText("Aimé");
    }
    else {
        service1.delete(id, 3);
        like.setText("J'aime");
    }
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(id);
    nom.setText(e.getNom());
    likes.setText("Aimé par "+service3.countLikes(id)+" personne(e)");
    
    }
   

    @FXML
    private void SetVisited(ActionEvent event) {
            
    LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
        if (visited.getText().equals("Marquer visité")){
        service2.add(id, 3);
        visited.setText("Visité");}
        else {
        service2.delete(id, 3);
        visited.setText("Marquer visité");
        }
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(id);
    nom.setText(e.getNom());
    visits.setText("Visité par "+service3.countVisited(id)+" personne(e)");
    
    }

    @FXML
    private void AjouterEvent(ActionEvent event) {
 try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutEvenement.fxml"));
                    Parent root = (Parent) FL.load();
                    AjoutEvenementController EVC = FL.getController();
                    EVC.loadDate(id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AjouterOffre(ActionEvent event) {
         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutOffre.fxml"));
                    Parent root = (Parent) FL.load();
                    AjoutOffreController EVC = FL.getController();
                    EVC.loadDate(id);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AllOffers(ActionEvent event) {
         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ListOffersEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    ListOffersEtabController EVC = FL.getController();
                    EVC.getList(id,"");
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AllEvents(ActionEvent event) {
                 try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ListEventsEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    ListEventsEtabController EVC = FL.getController();
                    
                    EVC.getList(id,"");
                    MainPane.getChildren().setAll(root);
                    
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
        
    public void loadData(int id){
            
    LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
    
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(id);
    nom.setText(e.getNom());
    likes.setText("Aimé par "+service3.countLikes(id)+" personne(e)");
    visits.setText("Visité par "+service3.countVisited(id)+" personne(e)");
    if (service1.count(3,id)==0){
    like.setText("J'aime");
    }
    else {like.setText("Aimé!");}
    if (service2.count(3,id)==0){
       visited.setText("Marquer visité");}
       else {visited.setText("Visité");}
    System.out.println(id);
    setId(id);
    DemandePartenariatService service=new DemandePartenariatService();
               PartenariatService service4=new PartenariatService();
               System.out.println(id);
               System.out.println(service4.check(id));
               System.out.println(service.check(id));
               
               if (service4.check(id)==0 && service.check(id)==0){
               Demande.setVisible(true);
               labelPartenaire.setVisible(false);
               }
               else {
               if (service4.check(id)!=0){
               Demande.setVisible(false);
               labelPartenaire.setText("Vous etes partenaire");
               }
               else if (service.check(id)!=0){
                Demande.setVisible(false);
                labelPartenaire.setText("Demande partenariat en attente");
               }
               }
              
    }

    @FXML
    private void DemandePartenariat(ActionEvent event) {
        
         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("DemandePartenariat.fxml"));
                    Parent root = (Parent) FL.load();
                    DemandePartenariatController EVC = FL.getController();
                    System.out.println(id);
                    EVC.loadData(id);
                    MainPane.getChildren().setAll(root);
                    
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

    
    
    
    
}
