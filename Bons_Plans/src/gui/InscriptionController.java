/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class InscriptionController implements Initializable {
    ObservableList<String> sexList = FXCollections.observableArrayList("F","H","Autre");
    @FXML
    private JFXButton loginbnt;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private JFXTextField ville;
    @FXML
    private ChoiceBox<String> sexe;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField num;
    @FXML
    private JFXTextField web;

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
    private void login(ActionEvent event) {
    }
    
}
