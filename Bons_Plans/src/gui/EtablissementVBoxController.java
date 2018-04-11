package gui;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.implementation.DemandePartenariatService;
import services.implementation.EtablissementService;
import services.implementation.LikedEtablissementService;
import services.implementation.PartenariatService;
import services.implementation.TagService;
import services.implementation.VisitedEtablissementService;

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
    @FXML
    private ToggleButton visited;
    @FXML
    private ToggleButton like;
    @FXML
    private JFXButton event;
    @FXML
    private JFXButton offre;
    @FXML
    private Text visits;
    @FXML
    private Text likes;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton offres;
    @FXML
    private Label labelPartenaire;
    @FXML
    private JFXButton Demande;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    public void ShowEtablissement(int Id)
    {
        LikedEtablissementService service1=new LikedEtablissementService();
    
        VisitedEtablissementService service2=new VisitedEtablissementService();
        EtablissementService ES = new EtablissementService();
        
        
        likes.setText("Aimé par "+ES.countLikes(Id)+" personne(e)");
        visits.setText("Visité par "+ES.countVisited(Id)+" personne(e)");
        if (service1.count(3,Id)==0){
        like.setText("J'aime");
        }
        else {like.setText("Aimé!");}
        if (service2.count(3,Id)==0){
        visited.setText("Marquer visité");}
        else {visited.setText("Visité");}
        System.out.println(Id);
        
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
        DemandePartenariatService service=new DemandePartenariatService();
               PartenariatService service4=new PartenariatService();
               System.out.println(Id);
               System.out.println(service4.check(Id));
               System.out.println(service.check(Id));
               
               if (service4.check(Id)==0 && service.check(Id)==0){
               Demande.setVisible(true);
               labelPartenaire.setVisible(false);
               }
               else {
               if (service4.check(Id)!=0){
               Demande.setVisible(false);
               labelPartenaire.setText("Vous etes partenaire");
               }
               else if (service.check(Id)!=0){
                Demande.setVisible(false);
                labelPartenaire.setText("Demande partenariat en attente");
               }
               }
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

    @FXML
    private void SetVisited(ActionEvent event) {
         LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
        if (visited.getText().equals("Marquer visité")){
        service2.add(Id, 3);
        visited.setText("Visité");}
        else {
        service2.delete(Id, 3);
        visited.setText("Marquer visité");
        }
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(Id);
   
    visits.setText("Visité par "+service3.countVisited(Id)+" personne(e)");
    }

    @FXML
    private void SetLike(ActionEvent event) {
            LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
    if (like.getText().equals("J'aime")){
    service1.add(Id, 3);
    like.setText("Aimé");
    }
    else {
        service1.delete(Id, 3);
        like.setText("J'aime");
    }
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(Id);
    
    likes.setText("Aimé par "+service3.countLikes(Id)+" personne(e)");
    }

    @FXML
    private void AjouterEvent(ActionEvent event) {
        try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutEvenement.fxml"));
                    Parent root = (Parent) FL.load();
                    AjoutEvenementController EVC = FL.getController();
                    EVC.loadDate(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AjouterOffre(ActionEvent event) {
            try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutOffre.fxml"));
                    Parent root = (Parent) FL.load();
                    AjoutOffreController EVC = FL.getController();
                    EVC.loadDate(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AllEvents(ActionEvent event) {
                         try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ListEventsEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    ListEventsEtabController EVC = FL.getController();
                    
                    EVC.getList(Id,"");
                    Pane.getChildren().setAll(root);
                    
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void AllOffers(ActionEvent event) {
                 try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("ListOffersEtab.fxml"));
                    Parent root = (Parent) FL.load();
                    ListOffersEtabController EVC = FL.getController();
                    EVC.getList(Id,"");
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void DemandePartenariat(ActionEvent event) {
          try 
                {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("DemandePartenariat.fxml"));
                    Parent root = (Parent) FL.load();
                    DemandePartenariatController EVC = FL.getController();
                    
                    EVC.loadData(Id);
                    Pane.getChildren().setAll(root);
                    
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
