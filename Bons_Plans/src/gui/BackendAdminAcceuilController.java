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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.implementation.EtablissementService;
import services.implementation.LikedEtablissementService;
import services.implementation.VisitedEtablissementService;
/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class BackendAdminAcceuilController  implements Initializable {
    @FXML
    private Label etabs;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    @FXML
    private StackPane stack;
    private Pagination pagination;
    @FXML
    private Label users;
    @FXML
    private Label offers;
    @FXML
    private Label events;
    @FXML
    private AnchorPane Main;
    @FXML
    private Label users1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try 
                {
                VBox box = FXMLLoader.load(getClass().getResource("HomePanelBackend.fxml"));
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
                
                EtablissementService service=new EtablissementService();
                users.setText(service.countUsers()+" Utilisateurs");
                etabs.setText(service.countAll()+" Etablissements");
                offers.setText(service.countOffers()+" Offres");
                events.setText(service.countEvents()+" Evenements");
                pagination = new Pagination(4, 0);
                pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
                pagination.setPageFactory(new Callback<Integer, Node>() {
           public Node call(Integer pageIndex) {
           StackPane box = new StackPane();
          box.setLayoutY(141.0);
          box.setLayoutX(179.0);
          box.setPrefHeight(206.0);
          box.setPrefWidth(340.0);
          int page = pageIndex * 1;
        
       
           
          box.getChildren().add(createChart(page));
          
                
    return box;
       }
   });
                
                
        
                AnchorPane.setTopAnchor(pagination, 10.0);
                AnchorPane.setRightAnchor(pagination, 10.0);
                AnchorPane.setBottomAnchor(pagination, 10.0);
                AnchorPane.setLeftAnchor(pagination, 10.0);
                Main.getChildren().addAll(pagination);
                 
             
    }    
    
    public Chart createChart(int i)
    {
    if (i==0) 
    {
        return createPieChart();
    }
    else if (i==1)
    {
        return createBarChart();
    }
    else if (i==2)
    {
        return createBarChart2();
    }
    else 
    {
        return createPieChart();
    }
    }
    
           public Chart createPieChart() 
           {
                EtablissementService etabService=new EtablissementService();
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
                data.addAll(
         new PieChart.Data("Restaurants/Cafés  "+Math.round(etabService.Pourcentage("Restaurants/Cafés")*100)+"%", etabService.Pourcentage("Restaurants/Cafés")),
         new PieChart.Data("Boutiques  "+Math.round(etabService.Pourcentage("Boutiques")*100)+"%",etabService.Pourcentage("Boutiques")),
         new PieChart.Data("Hotels  "+Math.round(etabService.Pourcentage("Hotels")*100)+"%", etabService.Pourcentage("Hotels")),
         new PieChart.Data("Autres  "+Math.round(etabService.Pourcentage("Autres")*100)+"%",etabService.Pourcentage("Autres"))
                            );
                PieChart pie = new PieChart();
                pie.setData(data);
                pie.setTitle("Pourcentages Des Etablissements Par Type");
                return pie;         
            }
           
            public XYChart<CategoryAxis, NumberAxis> createBarChart() 
            {
                VisitedEtablissementService service=new VisitedEtablissementService();
                ObservableList<XYChart.Series<String, Double>> data =
                FXCollections.observableArrayList();
      Series<String, Double> as = new Series<>();
      Series<String, Double> bs = new Series<>();
      Series<String, Double> cs = new Series<>();
      Series<String, Double> ds = new Series<>();
      as.setName("Restaurants/Café");
      bs.setName("Boutiques");
      cs.setName("Hotels");
      ds.setName("Autres");
      as.getData().add(new XYChart.Data<>
         ("Restaurants/Café", service.countVisits("Restaurants/Cafés")));
      bs.getData().add(new XYChart.Data<>
         ("Boutiques", service.countVisits("Boutiques")));
      cs.getData().add(new XYChart.Data<>
         ("Hotels", service.countVisits("Hotels")));
      ds.getData().add(new XYChart.Data<>
         ("Autres", service.countVisits("Autres")));
      data.addAll(as, bs, cs, ds);
      CategoryAxis xAxis = new CategoryAxis();
      NumberAxis yAxis = new NumberAxis();
      BarChart bc = new BarChart<>(xAxis, yAxis);
      bc.setData(data);
      bc.setTitle("Nombre De Visites Des Etablissements Par Type");
      return bc;
            }
            
         public XYChart<CategoryAxis, NumberAxis> createBarChart2() 
         {
             LikedEtablissementService service=new LikedEtablissementService();
             ObservableList<XYChart.Series<String, Double>> data =
         FXCollections.observableArrayList();
      Series<String, Double> as = new Series<>();
      Series<String, Double> bs = new Series<>();
      Series<String, Double> cs = new Series<>();
      Series<String, Double> ds = new Series<>();
      as.setName("Restaurants/Cafés");
      bs.setName("Boutiques");
      cs.setName("Hotels");
      ds.setName("Autres");
      as.getData().add(new XYChart.Data<>
         ("Restaurants/Café", service.countLikes("Restaurants/Cafés")));
      bs.getData().add(new XYChart.Data<>
         ("Boutiques", service.countLikes("Boutiques")));
      cs.getData().add(new XYChart.Data<>
         ("Hotels", service.countLikes("Hotels")));
      ds.getData().add(new XYChart.Data<>
         ("Autres", service.countLikes("Autres")));
      data.addAll(as, bs, cs, ds);
      CategoryAxis xAxis = new CategoryAxis();
      NumberAxis yAxis = new NumberAxis();
      BarChart bc = new BarChart<>(xAxis, yAxis);
      bc.setData(data);
      bc.setTitle("Nombre De Visites Des Etablissements Par Type");
      return bc;
   }
}


