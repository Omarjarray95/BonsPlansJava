/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Evenement;
import entities.Offre;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import services.implementation.EvenementService;
import services.implementation.OffreService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class UpdateOffreController implements Initializable {
   private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    private TextField offre;
    @FXML
    private DatePicker deb;
    @FXML
    private DatePicker fin;
    @FXML
    private TextArea des;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
public void LoadData(int id){
   
          OffreService service=new OffreService();
          Offre e=service.findById(id);
          offre.setText(e.getOffre());
          Date datedeb=e.getDate_debut();
          Date datefin=e.getDate_fin();
          LocalDate ddatedeb=datedeb.toLocalDate();
          LocalDate ddatefin=datefin.toLocalDate();
          des.setText(e.getDescription());
          deb.setValue(ddatedeb);
          fin.setValue(ddatefin);
          setId(id);
   
     
}
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
    private void ModifierOffre(ActionEvent event) {
          ValidationSupport validationSupport = new ValidationSupport();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	

        if(offre.getText().isEmpty() ){
           validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(offre, "Veuillez saisir le nom de l'offre"));
        
        }
        else if(des.getText().isEmpty() ){
           validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(des, "Veuillez saisir une description"));}
           else if(deb.getValue().isBefore(localDate)){
        validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(deb, "Veuillez saisir une date valide"));

        }
           else if(fin.getValue().isBefore(deb.getValue())){
        validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(deb, "Veuillez saisir une date valide"));}
        else{
            LocalDate debut=deb.getValue();
            Date dateDebut = Date.valueOf(debut);
            LocalDate finD=fin.getValue();
            Date dateFin = Date.valueOf(finD);            
            Offre offer=new Offre(dateDebut, dateFin,des.getText(),offre.getText());
            OffreService service=new OffreService();
            Offre o=service.findById(id);
            service.update(o, offer);
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("Offre modifié  ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
            
        }
    
    }
    
}
