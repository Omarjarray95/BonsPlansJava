package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import entities.Etablissement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.implementation.DemandeService;
import services.implementation.EtablissementService;

public class EtablissementController implements Initializable 
{
    
    ObservableList<String> TypeItems = FXCollections.observableArrayList("Restaurants/Cafés","Boutiques","Hotels","Autres");
    ObservableList<String> Type1RItems = FXCollections.observableArrayList("Restaurant","Café","Fast-Food","Salon De Thé","Bar","Boite De Nuit","Glacier","Autre");
    ObservableList<String> Type1LItems = FXCollections.observableArrayList("Cinéma","Salle De Sport","Parc D'Attraction","Spa","Salon De Coiffure","Salle De Jeux","Autre");
    ObservableList<String> Type1BItems = FXCollections.observableArrayList("Grande Surface","Parfumerie","Parc D'Attraction","Boutique","Librairie","Fleuriste","Candy Shop","Autre");
    ObservableList<String> Type1HItems = FXCollections.observableArrayList("*","* *","* * *","* * * *","* * * * *");
    
    @FXML
    private ChoiceBox Type;
    @FXML
    private Button Image;
    @FXML
    private Label Path;
    @FXML
    private ChoiceBox Type1;
    @FXML
    private Button Submit;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Adresse;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Numero;
    @FXML
    private TextField SiteWeb;
    @FXML
    private TextField BudgetMoyen;
    @FXML
    private JFXDatePicker HoraireOuverture;
    @FXML
    private JFXDatePicker HoraireFermeture;
    @FXML
    private Label Horaire;
    @FXML
    private Label BudMoy;
    @FXML
    private Label BMDT;
    @FXML
    private Button NVK;
    @FXML
    private Button NVK1;
    @FXML
    private Button AGlobe;
    
    public String Add;
    @FXML
<<<<<<< HEAD
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
=======
    private Button BoutonTest;
>>>>>>> 2121e57e4b70682b1ddac524b2b048ab97cef440
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
        Type.setItems(TypeItems);
        Image I = new Image(getClass().getResourceAsStream("Phone.png"));
        Image I1 = new Image(getClass().getResourceAsStream("Globe.png"));
        NVK.setGraphic(new ImageView(I));
        NVK1.setGraphic(new ImageView(I));
        AGlobe.setGraphic(new ImageView(I1));
    }

    private void ChoisirImage(ActionEvent event) 
    {
        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            String path = file.getAbsolutePath();
            Path.setText(path);
        }
    }
    
    @FXML
    private void ChoisirType(ActionEvent event) 
    {
        String T = Type.getValue().toString();
        if (T == "Restaurants/Cafés")
        {
            Type1.setVisible(true);
            Type1.setItems(Type1RItems);
        }
        if (T == "Boutiques")
        {
            Type1.setVisible(true);
            Type1.setItems(Type1BItems);
        }
        if (T == "Hotels")
        {
            Type1.setVisible(true);
            Type1.setItems(Type1HItems);
            Horaire.setVisible(false);
            HoraireOuverture.setVisible(false);
            HoraireFermeture.setVisible(false);
            BudMoy.setVisible(false);
            BudgetMoyen.setVisible(false);
            BMDT.setVisible(false);
        }
        if (T == "Autres")
        {
            Type1.setVisible(true);
            Type1.setItems(Type1LItems);
        }
    }

    @FXML
    private void Submit(ActionEvent event) 
    {
        if (Check() == false)
        {
            Alert A = new Alert(AlertType.ERROR);
            A.setTitle("Error !");
            A.setHeaderText("Données Invalides");
            A.setContentText("Merci De Vérifier Le Nom De L'Etablissement, Son Type Ou Son Adresse Afin De Pouvoir Accepter Votre Demande");
            A.show();
        }
        else
        {
            DemandeService DS = new DemandeService();
            int N = 0;
            int B = 0;
            String SO = null;
            String SF = null;
            if ((HoraireOuverture.getTime() != null) || (HoraireFermeture.getTime() != null))
            {
                SO = HoraireOuverture.getTime().toString();
                SF = HoraireFermeture.getTime().toString();
            }
            if (Numero.getText().length() > 0)
            {
            N = Integer.parseInt(Numero.getText());
            }
            if (BudgetMoyen.getText().length() > 0)
            {
                B = Integer.parseInt(BudgetMoyen.getText());
            }
            DS.Ajout(
                Nom.getText(),
                Type.getValue().toString(),
                Adresse.getText(),
                Description.getText(),
                SO,
                SF,
                N,
                SiteWeb.getText(),
                B,
                Type1.getValue().toString(),
                Path.getText());
        }
        
    }
    
    public void ModifEtablissement(int Id)
    {
        EtablissementService ES = new EtablissementService();
        Etablissement E = ES.findById(Id);
        Nom.setText(E.getNom());
        Type.setValue(E.getType());
        String S = E.getType();
        if (S.equals("Restaurants/Cafés"))
                {
                    Type1.setValue(E.getType_resto());
                }
        if (S.equals("Boutiques"))
                {
                    Type1.setValue(E.getType_shops());
                }
        if (S.equals("Hotels"))
                {
                    Type1.setValue(E.getNbrStars());
                }
        if (S.equals("Autres"))
                {
                    Type1.setValue(E.getType_loisirs());
                }
        Adresse.setText(E.getAdresse());
        Description.setText(E.getDescription());
        LocalTime LTO = LocalTime.parse(E.getHoraire_ouverture());
        LocalTime LTF = LocalTime.parse(E.getHoraire_fermeture());
        HoraireOuverture.setTime(LTO);
        HoraireFermeture.setTime(LTF);
        Numero.setText(Integer.toString(E.getNumtel()));
        SiteWeb.setText(E.getUrl());
        BudgetMoyen.setText(Integer.toString(E.getBudgetmoyen()));
        Path.setText(E.getImage());
        Submit.setText("Modifier Les Informations De " + E.getNom());
        Submit.setOnAction(new EventHandler<ActionEvent>() 
        {
        @Override 
        public void handle(ActionEvent e) 
        {
        EtablissementService ES = new EtablissementService();
        Integer N = Integer.parseInt(Numero.getText());
        Integer B = Integer.parseInt(BudgetMoyen.getText());
        ES.Modif(
                E.getId(),
                Nom.getText(),
                Type.getValue().toString(),
                Type1.getValue().toString(),
                Adresse.getText(),
                Description.getText(),
                HoraireOuverture.getTime().toString(),
                HoraireFermeture.getTime().toString(),
                N,
                SiteWeb.getText(),
                B,
                Path.getText());
        }
        });
    }

    @FXML
    private void ShowKeyboard(ActionEvent event) 
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        dialogVbox.setPadding(new Insets(10,10,10,10));
        dialogVbox.setSpacing(10);
        HBox Numbers = new HBox();
        Numbers.setAlignment(Pos.CENTER);
        for(int i = 0;i<10;i++)
        {
            Button BN = new Button(Integer.toString(i));
            BN.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                if (Numero.getText().length() != 8)
                {
                    Numero.setText(Numero.getText() + BN.getText());
                }
            }
            });
            Numbers.getChildren().add(BN);
        }
        HBox Controls = new HBox();
        Controls.setSpacing(10);
        Controls.setAlignment(Pos.CENTER);
        Button BOK = new Button("Confirmer Le Numéro");
        BOK.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                dialog.close();
            }
            });
        Button BD = new Button("DEL");
        BD.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                if (Numero.getText().length() > 0)
                {
                    StringBuilder sb = new StringBuilder(Numero.getText());
                    sb.deleteCharAt(Numero.getText().length()-1);
                    Numero.setText(sb.toString());
                }
            }
            });
        Button BR = new Button("Réinitialiser");
        BR.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                Numero.setText("");
            }
            });
        Controls.getChildren().addAll(BOK,BD,BR);
        dialogVbox.getChildren().addAll(Numbers,Controls);
        Scene dialogScene = new Scene(dialogVbox, 300, 80);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    private void ShowKeyboard1(ActionEvent event) 
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        dialogVbox.setPadding(new Insets(10,10,10,10));
        dialogVbox.setSpacing(10);
        HBox Numbers = new HBox();
        Numbers.setAlignment(Pos.CENTER);
        for(int i = 0;i<10;i++)
        {
            Button BN = new Button(Integer.toString(i));
            BN.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                if (BudgetMoyen.getText().length() != 3)
                {
                    BudgetMoyen.setText(BudgetMoyen.getText() + BN.getText());
                }
            }
            });
            Numbers.getChildren().add(BN);
        }
        HBox Controls = new HBox();
        Controls.setSpacing(10);
        Controls.setAlignment(Pos.CENTER);
        Button BOK = new Button("Confirmer Le Numéro");
        BOK.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                dialog.close();
            }
            });
        Button BD = new Button("DEL");
        BD.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                if (BudgetMoyen.getText().length() > 0)
                {
                    StringBuilder sb = new StringBuilder(BudgetMoyen.getText());
                    sb.deleteCharAt(BudgetMoyen.getText().length()-1);
                    BudgetMoyen.setText(sb.toString());
                }
            }
            });
        Button BR = new Button("Réinitialiser");
        BR.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                BudgetMoyen.setText("");
            }
            });
        Controls.getChildren().addAll(BOK,BD,BR);
        dialogVbox.getChildren().addAll(Numbers,Controls);
        Scene dialogScene = new Scene(dialogVbox, 300, 80);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    private boolean Check()
    {
        boolean B = true;
        if(Nom.getText().length() == 0)
        {
            B = false;
        }
        if(Type.getValue() == null)
        {
            B = false;
        }
        if(Type1.getValue() == null)
        {
            B = false;
        }
        if(Adresse.getText().length() == 0)
        {
            B = false;
        }
        if(Path.getText().isEmpty())
        {
            File F = new File("Et.png");
            Path.setText(F.getAbsolutePath());
        }
        return B;
    }

    @FXML
    private void ShowGM(ActionEvent event) 
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader FL = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Pane root;
        try 
        {
            root = FL.load();
            Button BCA = new Button("Confirmer L'Adresse");
            BCA.setLayoutX(10);
            BCA.setLayoutY(80);
            BCA.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override 
            public void handle(ActionEvent e) 
            {
                DirectionsFXMLController DFC = FL.getController();
                String S = DFC.getAddr();
                System.out.println(S);
                Adresse.setText(S);
            }
            });
            root.getChildren().add(BCA);
            Scene dialogScene = new Scene(root, 700, 500);
            dialog.setScene(dialogScene);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dialog.show();
    }
    
    public void PutAddr(String S)
    {
        Add = S;
        System.out.println(Add);
        Adresse.setPromptText(Add);
        System.out.println(Adresse.getText());
    }
    
}
