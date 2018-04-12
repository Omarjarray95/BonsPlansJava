/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import entities.Evenement;
import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import services.implementation.EvenementService;
import org.controlsfx.control.Notifications;
import services.implementation.EtablissementService;
import services.implementation.OffreService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class AjoutOffreController implements Initializable {
    private int id;
    @FXML
    private JFXTextField pourcentage;
    @FXML
    private JFXTextField code;
    @FXML
    private Label pourLabel;
    @FXML
    private Label codeLabel;
    @FXML
    private AnchorPane MainPane;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    private DatePicker deb;
    @FXML
    private TextField offre;
    @FXML
    private TextArea des;
    @FXML
    private DatePicker fin;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;

    /**
     * Initializes the controller class.
     */
    public void loadDate(int id)
    {
        System.out.println(id);
    setId(id);
    EtablissementService serviceEtab=new EtablissementService();
                        Etablissement etab=serviceEtab.findById(id);
                        System.out.println(serviceEtab.checkPartner(id));
                        System.out.println(id);
     if (serviceEtab.checkPartner(id)==0){
                                code.setVisible(false);
                                pourcentage.setVisible(false);
                                pourLabel.setVisible(false);
                                codeLabel.setVisible(false);
            }
    }
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
    private void AjoutOffre(ActionEvent event) {
        ValidationSupport validationSupport = new ValidationSupport();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	EtablissementService serviceEtab=new EtablissementService();
        Etablissement etab=serviceEtab.findById(id);


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

            Offre offer=new Offre(id, dateDebut, dateFin,des.getText(),offre.getText());
                                if (serviceEtab.checkPartner(id)==0){
                        offer.setCode("");
                        offer.setPourcentage(0);
            }
                                else {
                                offer.setCode(code.getText());
                                offer.setPourcentage(Double.parseDouble(pourcentage.getText()));
                                }
            OffreService service=new OffreService();
            service.add(offer);
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("Offre ajouté  ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
                        
                                        try 
                {
                    Offre off=service.findByName(offer.getOffre());
                    int Idd = off.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("OffreProfile.fxml"));
                    Parent root = (Parent) FL.load();
                    OffreProfileController EVC = FL.getController();
                    EVC.loadData(Idd);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
            
        }
    
}
    
    
}
