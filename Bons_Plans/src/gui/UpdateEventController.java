/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import services.implementation.EvenementService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class UpdateEventController implements Initializable {

    private int id;
    @FXML
    private AnchorPane MainPane;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private DatePicker date;
    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouter;
    @FXML
    private JFXDatePicker deb;
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
public void LoadData(int id){
   
          EvenementService service=new EvenementService();
          Evenement e=service.findById(id);
          nom.setText(e.getNom());
          Date datee=e.getDate();
          LocalDate ddate=datee.toLocalDate();
          deb.setValue(ddate);
          description.setText(e.getDescription()); 
          setId(id);
          
     
}

    @FXML
    private void ModifierEvenement(ActionEvent event) {
        ValidationSupport validationSupport = new ValidationSupport();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	

        if(nom.getText().isEmpty() ){
           validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(nom, "Veuillez saisir le nom"));
        
        }
        else if(description.getText().isEmpty() ){
           validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(nom, "Veuillez saisir une description"));}
           else if(deb.getValue().isBefore(localDate)){
        validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(description, "Veuillez saisir une date valide"));

        }else{
            LocalDate ddate=deb.getValue();
            Date date = Date.valueOf(ddate);
            EvenementService service=new EvenementService();
            Evenement e=new Evenement(date, description.getText(),  nom.getText());
            System.out.println(id);
            System.out.println(date.toString());
            System.out.println(nom.getText());
            service.update(id,e);
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("Evenement modifié  ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
                        try 
                {
                    Evenement Event=service.findByName(e.getNom());
                    int Id = Event.getId();
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
          
}
    }
       
    

