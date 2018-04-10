package GUI;

import services.implementation.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PersonneFXMLController implements Initializable 
{

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Button valider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }       

    @FXML
    private void Ajout(ActionEvent event) 
    {
        String name = nom.getText();
        String lname = prenom.getText();
        UserService C = new UserService();
        C.add(name, lname);
    }
}
