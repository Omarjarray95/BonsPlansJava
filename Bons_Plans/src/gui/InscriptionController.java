/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
import services.implementation.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import test.AboutController;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class InscriptionController implements Initializable {
    ObservableList<String> sexList = FXCollections.observableArrayList("Femme","Homme");
    private StackPane rootPane;
    @FXML
    private JFXButton loginbnt;
    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private ChoiceBox<String> sexe;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField num;
    @FXML
    private JFXTextField login;
    
    private Matcher matcher;
    
    private static Pattern pattern;
    
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private RadioButton mem;
    private RadioButton mempro;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        sexe.setItems(sexList);
        // TODO
    }    

    @FXML
    private void recheck(KeyEvent event) {
    }

    @FXML
    private void login(ActionEvent event) throws IOException, AddressException, MessagingException {
        
   
//       String originalPassword = "Aman";

       login.textProperty().addListener((observable, oldValue, newValue) -> {
       login.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });
       mail.textProperty().addListener((observable, oldValue, newValue) -> {
       mail.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });  
       num.textProperty().addListener((observable, oldValue, newValue) -> {
       num.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });  
       name.textProperty().addListener((observable, oldValue, newValue) -> {
       name.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });  
       password1.textProperty().addListener((observable, oldValue, newValue) -> {
       password1.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });  
       password2.textProperty().addListener((observable, oldValue, newValue) -> {
       password2.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
       });  
       
             
      
       
        
          
        if (login.getText().trim().isEmpty() && mail.getText().trim().isEmpty() && name.getText().trim().isEmpty() && 
                password1.getText().trim().isEmpty() && password2.getText().trim().isEmpty() && num.getText().trim().isEmpty()){
         login.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            mail.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
              name.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
               password1.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                password2.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
              num.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
               
                return;
        
        
        }else{
         
           try {
               if(password1.getText() != password2.getText()){
                   
                   password1.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                   password2.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                   
                   
               }
               
               
               
               
               
               login.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
               mail.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
               name.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
               password1.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
               password2.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
               num.setStyle("-fx-border-color: red ; -fx-border-width: 0px ;");
                 

ServiceUser ser=new ServiceUser();
//test existance email

if (ser.notExistUser(mail.getText(),password1.getText())) {
    notification("Email Existe");       
    return;

           }
//test email doit contenir les caractéres 
if (validateEmail(mail.getText()) == false){
    notification("Email doit contenir les lettres @/.");
return;
}

if (check() == false) {
    return;
    
}

User us=new User();

//          us.setDTYPE(dtype.getText());
us.setBirthDate(Date.valueOf(date.getValue()));
//          us.setAge(Integer.valueOf(age.getText()));
us.setNom(name.getText());
us.setEmail(mail.getText());
us.setValid("false");
us.setSex(sexe.getValue());
if(this.mem.isSelected()){
    us.setDTYPE(mem.getText());
    us.setTest(mem.getText());
}
if(this.mempro.isSelected()){
    us.setDTYPE(mempro.getText());
    us.setTest(mempro.getText());
}
us.setLogin(login.getText());


us.setPassword(password1.getText());
//          us.setTel(Integer.valueOf(tel.getText()));

us.setTel(Integer.valueOf(num.getText()));
ser.addUser(us);

List<User> ls=ser.afficher();
System.out.println(ls);


String CodeX;

CodeX = "";

FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));

Parent root = loader.load();

AboutController personneController = loader.getController();

CodeX = Integer.toString(getRandomNumberInRange(100000,999999));

personneController.setcodeemail(CodeX);
//        personneController.setTypLabel(.getText());

name.getScene().setRoot(root);

sendM(mail.getText(),CodeX);
           } catch (Exception ex) {
               Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
           }
       
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
                    rootPane.getChildren().add(veil);
                } else if (rootPane.getChildren().contains(veil)) {
                    rootPane.getChildren().removeAll(veil);
                }
                
            });
            newStage.centerOnScreen();
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hlLogin(ActionEvent event)  {
        Stage hideThis = (Stage) loginbnt.getScene().getWindow();
        hideThis.close();
        
        setStage("/test/Loginn.fxml");
      
    }
        
  public boolean validateEmail(String txt) {
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(txt);
		return matcher.matches();
	}
 
//      public boolean checkEmail() 
//      public boolean checkEmail() {
//         
//
//          
////       
////        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
////                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
////
////
////
////        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
////
////        
////        // Associer l'entrée donnée à ce motif
////        Matcher matcher = pattern.matcher(email.getText());
////
////        
////        return matcher.matches();
////       
////
////
//}  
      

//     

    

    public boolean check(){
     String password = password1.getText();
             String pass2 = password2.getText();
             
              boolean status = false;
        int nums = 0, chars = 0;
        if (password1.getText().length() >= 6) {
            if (password1.getText() == null ? pass2 == null : password1.getText().equals(pass2)) {
                for (int x = 0; x < password.length(); x++) {
                    if ((password.charAt(x) == '0') || (password.charAt(x) == '1')
                            || (password.charAt(x) == '2') || (password.charAt(x) == '3')
                            || (password.charAt(x) == '4') || (password.charAt(x) == '5')
                            || (password.charAt(x) == '6') || (password.charAt(x) == '7')
                            || (password.charAt(x) == '8') || (password.charAt(x) == '9')) {
                        nums++;
                    }
                }
                if (nums > 0) {
                    for (int x = 0; x < password.length(); x++) {
                        if ((password.charAt(x) == '*') || (password.charAt(x) == '/')
                                || (password.charAt(x) == '?') || (password.charAt(x) == '!')
                                || (password.charAt(x) == '.') || (password.charAt(x) == ',')
                                || (password.charAt(x) == '&') || (password.charAt(x) == '$')) {
                            chars++;
                        }
                    }
                    if (chars > 0) {
                        status = true;
                    } else {
                        notification("Le mot de passe doit comporter un caractère spécial: * / ? ! . , & $");
                    }
                } else {
                    notification("Mot de passe doit contenir un certain nombre.");
                }
            } else {
                notification("Les mots de passe ne correspondent pas.");
                status = false;
            }
        } else {
            notification("Le mot de passe doit être supérieure à 5 caractères.");
         
            status = false;
        }
        return status;
    
    }

    public void notification(String RR){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(RR);

        alert.showAndWait();

    }
    private static int getRandomNumberInRange(int min, int max) {
	if (min >= max) {
		throw new IllegalArgumentException("max must be greater than min");
	}
	Random r = new Random();
	
       
           return r.nextInt((max - min) + 1) + min;
       
        
     //   return r.nextInt((max - min) + 1) + min;
}
 
    public void sendM(String EMail,String CodeX) {
   System.out.println("Email Send Start");
        final String username = "bonsplans03@gmail.com"; 
		final String password = "bonsplans123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
                props.put("mail.smtp.ssl.trust", "*"); 
                


		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username)); // From Email same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(EMail));// whome u have to send mails that person id
			message.setSubject("_Validation d'inscription_"); // Title
			
                        
                        
                        message.setText("Nouvelle inscription , " + Date.valueOf(date.getValue()) +", Code D'activation = " + CodeX);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			//throw new RuntimeException(e);
                        System.out.println(e);
                       
		}
    
    }


    

    }
    

