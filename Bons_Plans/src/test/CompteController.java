/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Session;
import entities.User;
import gui.HomeController;
import gui.LoginController;
import gui.ReclamationController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.implementation.ServiceUser;

/**
 * FXML Controller class
 *
 * @author DADOU
 */
public class CompteController implements Initializable {

    @FXML
    private Label lbnom;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton btnRestore;
    @FXML
    private JFXTextField emailu;
    @FXML
    private JFXTextField nomu;
    @FXML
    private JFXButton savee;
    @FXML
    private JFXButton reclam;
    @FXML
    private JFXTextField phoneu;
    @FXML
    private JFXTextField usernameu;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  try 
                  {
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
        try{
            Session ss =new Session();
              lbnom.setText(ss.user.nom);
              /*emailu.setText(ss.user.Email);
              nomu.setText(ss.user.nom);
              usernameu.setText(ss.user.login);*/
             System.out.println(lbnom);
             sett();
        }
         finally
         {
             
         }   
  
        // TODO
    }    

    public void setlbnom(String lbnom) 
    {
        this.lbnom.setText(lbnom);
    }
    
    public void sett()
    {
        change(true); 
        try
        {
            Connection connection;
            PreparedStatement ps;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bonsplans", "root", "");
            ps = connection.prepareStatement("SELECT `username`, `nom`, `email`, `phone` FROM `user1` WHERE `nom` = ? " );
            ps.setString(1, String.valueOf(lbnom.getText()));
            ResultSet result = ps.executeQuery();
            if(result.next())
            {
                nomu.setText(lbnom.getText());
                System.out.println(result.getString("username"));
                System.out.println(result.getString("phone"));
                usernameu.setText(result.getString("username"));
//                     nomu.setText(result.getString("nom"));
//                     prenomu.setText(result.getString("prenom"));
                emailu.setText(result.getString("email"));
                phoneu.setText(result.getString("phone"));
                try 
                {
                    //            System.out.println(decrypt(result.getString("password").toString().trim(),"2018"));
//            System.out.println(result.getString("password"));
//Al(encrypt(result.getString("password"),"2018"));
//  passu.setText(decrypt(result.getString("password"),"2018"));
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(test.CompteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(test.CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void change(Boolean b)
       {
           if (b == false) 
           {
               nomu.setEditable(false);
               usernameu.setEditable(false);
               emailu.setEditable(false);
               phoneu.setEditable(false);
               
           }
           else
           {
               nomu.setEditable(true);
               usernameu.setEditable(true);
               emailu.setEditable(true);
               phoneu.setEditable(true);
           }
       }
       
         public void Al(String txt)
         {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information Dialog");
        alert.setContentText(txt);
        alert.showAndWait();
        }



    @FXML
    private void closeStage(MouseEvent event) 
    {
        nomu.getScene().getWindow().hide();
    }

    @FXML
    private void deconnecter(ActionEvent event) 
    {
     try 
     {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
            Parent root = loader.load();
            LoginController logi = loader.getController();
            nomu.getScene().setRoot(root);
     } 
     catch (IOException ex) 
     {
            Logger.getLogger(test.CompteController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @FXML
    private void updatecompte(ActionEvent event) 
    {
        
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
//
        @FXML
        private void envoyerrecla(ActionEvent event) throws IOException 
        {
    //         ((Node)event.getSource()).getScene().getWindow().hide();
                try 
                {  
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                
                Parent root = loader.load();
                
                ReclamationController rec = loader.getController();
                nomu.getScene().setRoot(root);
                } 
                catch (IOException ex) 
                {
                Logger.getLogger(test.CompteController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

}
