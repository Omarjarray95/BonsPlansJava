package gui;

import entities.Etablissement;
import entities.Tag;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.implementation.EtablissementService;
import services.implementation.TagService;

public class EtablissementVBoxController implements Initializable {

    @FXML
    private Pane Pane;
    @FXML
    private Label Label;
    @FXML
    private ImageView IV;
    @FXML
    private Label Adresse;
    @FXML
    private Label Horaire;
    @FXML
    private Label NDT;
    @FXML
    private Label BM;
    @FXML
    private Hyperlink SWB;
    @FXML
    private TextFlow Description;
    @FXML
    private Hyperlink Modifier;
    
    private int Id;
    private Event AE;
    
    @FXML
    private Hyperlink Supprimer;
    @FXML
    private Button Tags;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public void ShowEtablissement(int Id)
    {
        EtablissementService ES = new EtablissementService();
        Etablissement E = ES.findById(Id);
        File F = new File(E.getImage());
        Image I = new Image(F.toURI().toString());
        IV.setImage(I);
        Label.setText(E.getNom());
        Adresse.setText(Adresse.getText() + " " + E.getAdresse());
        Horaire.setText(Horaire.getText() + " " + E.getHoraire_ouverture() + " - " + E.getHoraire_fermeture());
        NDT.setText(NDT.getText() + " " + E.getNumtel());
        BM.setText(BM.getText() + " " + E.getBudgetmoyen() + " DT");
        SWB.setText(E.getUrl());
        Text T = new Text(E.getDescription());
        this.Id = E.getId();
        Modifier.setText(Modifier.getText() + " " + E.getNom());
        Supprimer.setText(Supprimer.getText() + " " + E.getNom());
        Description.getChildren().addAll(T);
    }

    @FXML
    private void ModifierInfo(ActionEvent event) 
    {
        FXMLLoader FL = new FXMLLoader(getClass().getResource("Etablissement.fxml"));
        try 
        {
            Parent root = (Parent) FL.load();
            Pane.getChildren().setAll(root);
            EtablissementController EC = FL.getController();
            EC.ModifEtablissement(Id);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Supp(ActionEvent event) 
    {
        EtablissementService ES = new EtablissementService();
        ES.Supp(Id);
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
        Parent root;
        try 
        {
            root = (Parent) FL.load();
            Pane.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficheTags(ActionEvent event) 
    {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        HBox TEE = new HBox();
        HBox TPB = new HBox();
        HBox TNE = new HBox();
        TNE.setSpacing(10);
        TEE.setSpacing(10);
        dialogVbox.getChildren().add(TEE);
        dialogVbox.getChildren().add(TPB);
        dialogVbox.getChildren().add(TNE);
        dialogVbox.setPadding(new Insets(10,10,10,10));
        dialogVbox.setSpacing(10);
        TagService TS = new TagService();
        int i = 0;
        ArrayList<Tag> TNEAL = TS.AfficheTNE(Id);
        TextField AddTag = new TextField();
        for (Tag T1 : TNEAL)
        {
            Button TB1 = new Button(T1.getNom());
            TB1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                AddTag.setText(TB1.getText());
            }
            });
            TNE.getChildren().add(TB1);
            i++;
            if (i == 3)
            {
                HBox TNE1 = new HBox();
                TNE1.setSpacing(10);
                dialogVbox.getChildren().add(TNE1);
                TNE = TNE1;
                i = 0;
            }
        }
        ArrayList<Tag> TAL = TS.AfficheTE(Id);
        for(Tag T2:TAL)
        {
            HBox TPD = new HBox();
            Button TB = new Button(T2.getNom());
            Button DB = new Button("x");
            TPD.getChildren().addAll(TB,DB);
            TEE.getChildren().add(TPD);
            TB.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    String S = TB.getText();
                    EtablissementService ES = new EtablissementService();
                    ArrayList<Etablissement> TFWS = ES.FindByTag(S);
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    Parent root = (Parent) FL.load();
                    AffichagePaneController APC = FL.getController();
                    APC.AfficheParTag(TFWS);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            });
            DB.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                String T = TB.getText();
                Tag T1 = TS.findByName(T);
                EtablissementService ES = new EtablissementService();
                ES.SuppTag(Id, T1.getId());
            }
            });
        }
        Button V = new Button("Ajouter Tag");
        Button R = new Button("Rechercher");
        V.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                EtablissementService ES = new EtablissementService();
                String T = AddTag.getText();
                if (T.length()>0)
                {
                    TS.Ajout(T);
                    Tag T1 = TS.findByName(T);
                    ES.AjoutTag(Id, T1.getId());
                }
            }
            });
        R.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    String S = AddTag.getText();
                    EtablissementService ES = new EtablissementService();
                    ArrayList<Etablissement> TFWS = ES.FindByTag(S);
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichagePane.fxml"));
                    Parent root = (Parent) FL.load();
                    AffichagePaneController APC = FL.getController();
                    APC.AfficheParTag(TFWS);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(EtablissementVBoxController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            });
        TPB.setSpacing(10);
        TPB.getChildren().addAll(AddTag,V,R);
        Scene dialogScene = new Scene(dialogVbox, 400, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
}
