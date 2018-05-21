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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class ListEtablissementsController extends Application implements Initializable {
    private List<Etablissement> etabs;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Hyperlink etab;
    @FXML
    private Separator sep;
    @FXML
    private VBox VboxEvent;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    private Pagination pagination;
    @FXML
    private AnchorPane Main;

    /**
     * Initializes the controller class.
     */
@Override
    public void start(Stage stage) throws Exception {
Parent root = FXMLLoader.load(getClass().getResource("ListEtablissements.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    }


    public VBox getList(int pageIndex) throws IOException{
          VBox box = new VBox(5);
          box.setLayoutY(-14.0);
          box.setPrefHeight(315.0);
          box.setPrefWidth(352.0);
          int page = pageIndex * 6;
        
        if(!etabs.isEmpty()){
            for (int i = page; i < page + 6; i++){
        Etablissement e=etabs.get(i);
        Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
        fromToLabel2.setFont(etab.getFont());
        fromToLabel2.setTextFill(etab.getTextFill());
        fromToLabel2.setLayoutX(etab.getLayoutX());
        fromToLabel2.setLayoutY(etab.getLayoutY());
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
    return box;}
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
                EtablissementService service =new EtablissementService();
                etabs=service.getAll();
                etab.setVisible(false);    
      pagination = new Pagination(Math.round(service.countAll()/6)+1, 0);
        pagination.setStyle("-fx-border-color:red;");
           pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           VBox box = new VBox(5);
          box.setLayoutY(-14.0);
          box.setPrefHeight(315.0);
          box.setPrefWidth(352.0);
          int page = pageIndex * 6;
        
        if(!etabs.isEmpty()){
            if (page+6>etabs.size()){
                        for (int i = page; i <etabs.size(); i++){
        Etablissement e=etabs.get(i);
        Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
        fromToLabel2.setFont(etab.getFont());
        fromToLabel2.setTextFill(etab.getTextFill());
        fromToLabel2.setLayoutX(etab.getLayoutX());
        fromToLabel2.setLayoutY(etab.getLayoutY());
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
            else{
            for (int i = page; i < page + 6; i++){
        Etablissement e=etabs.get(i);
        Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
        fromToLabel2.setFont(etab.getFont());
        fromToLabel2.setTextFill(etab.getTextFill());
        fromToLabel2.setLayoutX(etab.getLayoutX());
        fromToLabel2.setLayoutY(etab.getLayoutY());
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
                }}}
    return box;
       }
   });
        
        
                AnchorPane.setTopAnchor(pagination, 10.0);
                AnchorPane.setRightAnchor(pagination, 10.0);
                AnchorPane.setBottomAnchor(pagination, 10.0);
                AnchorPane.setLeftAnchor(pagination, 10.0);
                Main.getChildren().addAll(pagination);
                 
                 // TODO
             
    }    
    public static void main(String[] args) 
    {
        launch(args);
        
    }
}
