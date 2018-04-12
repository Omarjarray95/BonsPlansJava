/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import entities.Session;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.implementation.ServiceUser;
import techniques.InputValidation;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class LoginController implements Initializable {
    
public Session sess;

    @FXML
    private JFXTextField emailtxt;
    @FXML
    private JFXPasswordField passwordtxt;
    @FXML
    private JFXButton loginbnt;
    @FXML
    private Hyperlink forgetpw;
    public static final String ACCOUNT_SID = "AC7185b945059e749f980f0bf2e55ea174";
    public static final String AUTH_TOKEN = "367e1cf028a830948f42e4e9dc2fc589";
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
             sess = new Session();
    
    }    
 @FXML
    private void On_forgetpw(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mot de passe oublié ?");
        dialog.setHeaderText("Veuillez saisir votre numero de telephone");
        dialog.setContentText("Saissizer votre numero:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
            if (InputValidation.isPhoneNumber(result.get()) == 0) 
            {
                Alert alert = new InputValidation().getAlert("ERREUR", "Attention! Numero Invalide!");
                alert.showAndWait();
            } 
            else 
            {
                User U = null;
                ServiceUser us = new ServiceUser();
                
                U = us.findbynum(result.get());
                if(U == null)
                {
                    System.out.println("uuu");
                }
                if (us.findbynum(result.get()) == null) {
                    Alert alert = new InputValidation().getAlert("ERREUR", "Attention! Utilisateur n'existe pas!");
                    alert.showAndWait();
                } else {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                    Message message;
                    message = Message
                            .creator(new PhoneNumber("+21653198869"), new PhoneNumber("+13074593244"),
                                    "Votre mot de passe = " + U.getPassword())
                            .create();

                    System.out.println(message.getSid());
                    Alert alert = new InputValidation().getAlert("SUCCES", "Votre mot de passe a été envoyé à " + U.getTel());
                    alert.showAndWait();
                }
            }
        }
    }
    
    @FXML
    private void recheck(KeyEvent event) {
    }

    @FXML
   private void login(ActionEvent event) throws ClassNotFoundException, SQLException, InstantiationException {
     
        boolean test = false;
      
      if (passwordtxt.getText().trim().isEmpty() && emailtxt.getText().trim().isEmpty()) {
        Al("Entrez vote email et votre mot de passe");
        return;
       }else{}
      
      if (emailtxt.getText().trim().isEmpty()){
       Al("Entrez vote email");
        
       }else if (passwordtxt.getText().trim().isEmpty()){
         
        Al("entrez vote mot de passe");
       }else{
          
              // Al("Btn Clicked");
              
                // Log();
              
              String username = emailtxt.getText();
              String password = passwordtxt.getText();
              
              
              sess.Loginn(username,password);
              
              
        
        
        
       }   
    }
  
    public void Al(String txt){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information Dialog");
        alert.setContentText(txt);
        alert.showAndWait();
    }
    
    public void Log() throws ClassNotFoundException, SQLException {
         Al("Open Void Log");
        if (emailtxt.getText() != null && passwordtxt.getText() != null) {
          Al("if Open");
            Connection connection;
        PreparedStatement ps;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bonsplans", "root", "");
            ps = connection.prepareStatement("SELECT `email`, `password` FROM `user1` WHERE `email` = ? AND `password` = ?");
            ps.setString(1, String.valueOf(emailtxt.getText()));
            ps.setString(2, String.valueOf(passwordtxt.getText()));
            ResultSet result = ps.executeQuery();
            
           
            if(result.next()){
              //  jLabel_Message.setText("Login Successesfully");
               Al("Login Succes :)");
               
               
               
            }
            else{
             //   jLabel_Message.setText("Invalide Username Or Password");
               Al("Failed("); 
               
               
               
            }
        } catch (SQLException ex) {
           System.out.println("Errer");
            System.out.println(ex);
        }
        
        }
    }
    @FXML
    private void Inscription(ActionEvent event) {
         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Inscription");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }
    public void setStage(String fxml) {
        try {
            //dim overlay on new stage opening
            Region veil = new Region();
            veil.setPrefSize(1100, 650);
            veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
            Stage newStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.getScene().getRoot().setEffect(new DropShadow());
            newStage.showingProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
//                    rootPane.getChildren().add(veil);
//                } else if (rootPane.getChildren().contains(veil)) {
//                    rootPane.getChildren().removeAll(veil);
//                }
                }
                
            });
            newStage.centerOnScreen();
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
