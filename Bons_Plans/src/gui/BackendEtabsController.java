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
public class BackendEtabsController implements Initializable {
    private List<Etablissement> etabs;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    private JFXButton modifer;
    private JFXButton supp;
    @FXML
    private Hyperlink nom;
    @FXML
    private AnchorPane etabPane;
    @FXML
    private VBox mainPane;
    @FXML
    private AnchorPane main;
    private Pagination pagination;
    @FXML
    private AnchorPane Main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
               try 
               {
                VBox box = FXMLLoader.load(getClass().getResource("HomePanelBackend.fxml"));
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
                
                EtablissementService service =new EtablissementService();
                etabs=service.getAll();
                nom.setVisible(false);    
                pagination = new Pagination(Math.round(service.countAll()/6), 0);
                pagination.setStyle("-fx-border-color:red;");
                pagination.setPageFactory(new Callback<Integer, Node>() {
                public Node call(Integer pageIndex) {
                VBox box = new VBox(5);
                box.setLayoutY(-14.0);
                box.setPrefHeight(315.0);
                box.setPrefWidth(352.0);
                int page = pageIndex * 6;
                
                if(!etabs.isEmpty())
                {
                for (int i = page; i < page + 6; i++)
                {
                Etablissement e=etabs.get(i);
                Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
                fromToLabel2.setFont(nom.getFont());
                fromToLabel2.setTextFill(nom.getTextFill());
                fromToLabel2.setLayoutX(nom.getLayoutX());
                fromToLabel2.setLayoutY(nom.getLayoutY());
                fromToLabel2.setText(e.getNom());
                fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                try 
                {
                    int Id=e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id);
                    main.getChildren().setAll(root);
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
    
    public void getList() throws IOException{
        EtablissementService service =new EtablissementService();
        etabs=service.getAll();
        etabPane.setVisible(false);
        if(!etabs.isEmpty()){

                for (Etablissement e : etabs) {

       
        AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(etabPane.getStyle());
        newAnnoncesAnchorPane.setEffect(etabPane.getEffect());
        
       
        //fromtolabel      
        Hyperlink fromToLabel2 = new Hyperlink(e.getNom());
        fromToLabel2.setStyle(nom.getStyle());
        fromToLabel2.setText(e.getNom());
        fromToLabel2.setLayoutX(nom.getLayoutX());
        fromToLabel2.setLayoutY(nom.getLayoutY());
        fromToLabel2.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent action)
            {
                try 
                {
                    int Id = e.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("BackendProfileEtablissement.fxml"));
                    Parent root = (Parent) FL.load();
                    BackendProfileEtablissementController EVC = FL.getController();
                    EVC.loadData(Id);
                    main.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
        
        JFXButton suppButton=new JFXButton();
        suppButton.setStyle(supp.getStyle());
        suppButton.setText(supp.getText());
        suppButton.setTextFill(supp.getTextFill());
        suppButton.setLayoutX(supp.getLayoutX());
        suppButton.setLayoutY(supp.getLayoutY());
        
        JFXButton modifyButton=new JFXButton();
        modifyButton.setStyle(modifer.getStyle());
        modifyButton.setTextFill(modifer.getTextFill());
        modifyButton.setText(modifer.getText());
        modifyButton.setLayoutX(modifer.getLayoutX());
        modifyButton.setLayoutY(modifer.getLayoutY());
        
        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,modifyButton,suppButton);
        mainPane.getChildren().add(newAnnoncesAnchorPane);
        
                }}}

    
}
