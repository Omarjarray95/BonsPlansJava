/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

<<<<<<< HEAD
import com.jfoenix.controls.JFXButton;
=======
import UTILS.Utils;
import com.jfoenix.controls.JFXTextField;
import entities.Commentaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
>>>>>>> da9dea62fbda45dbd9d798f54404e0b7a42cf76a
import entities.Etablissement;
import entities.Session;
import entities.Tag;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
<<<<<<< HEAD
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
=======
import javafx.stage.FileChooser;
>>>>>>> da9dea62fbda45dbd9d798f54404e0b7a42cf76a
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import services.implementation.CommentaireService;
import services.implementation.EtablissementService;
import services.implementation.RatingService;
import services.implementation.DemandePartenariatService;
import services.implementation.EtablissementService;
import services.implementation.LikedEtablissementService;
import services.implementation.PartenariatService;
import services.implementation.TagService;
import services.implementation.VisitedEtablissementService;

/**
 * FXML Controller class
 *
 * @author Maissa
 */
public class EtablissementVBoxController implements Initializable {

    @FXML
    private Pane Pane;
    @FXML
    private ImageView IV;
    @FXML
    private Label Label;
    @FXML
    private Hyperlink Modifier;
    @FXML
    private Hyperlink Supprimer;
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
    private TextArea comm;
    @FXML
    private Button publier;
    @FXML
    private Rating rating;
    @FXML
    private Label nouv;
    @FXML
    private JFXTextField rate;
    @FXML
    private JFXTextField msg;
    @FXML
    private Button photo;
    @FXML
    private ImageView imageCommentaire;
    @FXML
    private ListView<Commentaire> ListeCommentaires;
    @FXML
    private MenuItem supp;
    @FXML
    private MenuItem modif;
    @FXML
    private MenuItem signal;
    private File file = null;
    private FileChooser fileChooser = new FileChooser();
    private static String uuid;
    private Image imagelog;
    final Stage stage=new Stage();
     private int Id;
    private Event AE;
    @FXML
    private Button Tags;
    @FXML
<<<<<<< HEAD
    private JFXButton btn;
    @FXML
    private Button reserv;
=======
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
>>>>>>> da9dea62fbda45dbd9d798f54404e0b7a42cf76a

    int idUser;
    @FXML
    private Label nomUser;
    int  idUserB;
    String nom; 
    @FXML
    private Button fcb;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        CommentaireService CS = new CommentaireService();
      List<Commentaire> AL= new ArrayList<Commentaire>();
      AL = CS.Affiche();
      ObservableList<Commentaire> liste_commentaires = FXCollections.observableArrayList(CS.Affiche());
      Session ss = new Session(); 
      System.out.println("******************");
      nomUser.setText(ss.user.nom);
      nom=ss.user.getNom();
      idUser=ss.user.getId();
      ListeCommentaires.setItems(liste_commentaires);
        
       ListeCommentaires.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() 
       {

    @Override
    public ListCell<Commentaire> call(ListView<Commentaire> p) 
    {
        return new ListCell<Commentaire>() 
        {

            ImageView IV1= new ImageView();
            
           @Override 
    protected void updateItem(Commentaire item, boolean empty) { 
        super.updateItem(item, empty); 
        setText(null); 
        if (!empty && item != null) { 
            final String text = String.format("%s %s %s",item.getComment()," Ajouté le"+item.getCreated(),item.getNom()); 
            setText(text);
            File F = new File(item.getPhoto());
        Image I = new Image(F.toURI().toString());
        IV1.setImage(I);
        IV1.setFitHeight(40);
        IV1.setFitWidth(40);
        setGraphic(IV1);
        } }       };}})  ;
        
      //ListeCommentaires.setCellFactory(lv -> new maissa());
        
        
        rating.ratingProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number t, Number t1) {
                msg.setText(t1.toString());
            }
        });   
       
         
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
    }    
    Date now = new Date(); 
        DateFormat df = DateFormat.getDateTimeInstance();
        String dat = df.format(now); 
    
    public void ShowEtablissement(int Id)
    {
        LikedEtablissementService service1=new LikedEtablissementService();
    
        VisitedEtablissementService service2=new VisitedEtablissementService();
        EtablissementService ES = new EtablissementService();
        

        likes.setText("Aimé par "+ES.countLikes(Id)+" personne(e)");
        visits.setText("Visité par "+ES.countVisited(Id)+" personne(e)");

        
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
        rate.setText(E.getRating()+" /5");
        Text T = new Text(E.getDescription());
        this.Id = E.getId();
        Session ss= new Session ();
        int id_user;
        id_user=ss.user.id;
        System.out.println(E.getResponsable());
        if (id_user==E.getResponsable()){
        Modifier.setText(Modifier.getText() + " " + E.getNom());
        Supprimer.setText(Supprimer.getText() + " " + E.getNom());
        like.setVisible(false);
        visited.setVisible(false);
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
        else{
               Modifier.setVisible(false);
               Supprimer.setVisible(false);
               event.setVisible(false);
               offre.setVisible(false);
               Demande.setVisible(false);
               labelPartenaire.setVisible(false);
            if (service1.count(id_user,Id)==0){
        like.setText("J'aime");
        }
        else {like.setText("Aimé!");}
        if (service2.count(id_user,Id)==0){
        visited.setText("Marquer visité");}
        else {visited.setText("Visité");}}
    }

    
        public int recupererCom(int id)
        {
        return id; 
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
    void weather(ActionEvent event) 
    {
//        Stage stage = new Stage();
//        stage.setTitle("Web View");
//        Scene scene = new Scene(new Browser(),750,500, Color.web("#666970"));
//        stage.setScene(scene);
//        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
//        stage.show();
        Stage s = new Stage() ;
        //           System.out.println("ena lahne"+new Utils().extractVilleFromAdresse(adresse.getText()));
        new WebViewSample().start(s,"tunis");
    }

    @FXML
    private void ajouterReserv(ActionEvent event) throws IOException 
    {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationIHM.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          ReservationIHMController RIC = fxmlLoader.getController();
          RIC.GetE(Id);
          Pane.getChildren().setAll(root1);
    }
    class Browser extends javafx.scene.layout.Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser(String ville) {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        webEngine.load("http://www.accuweather.com/en/tn/"+ville+"/321398/weather-forecast/321398");
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
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
    private void AjoutCommentaire(ActionEvent event) {
        int Idd ;
        CommentaireService CS = new CommentaireService();
        Idd=this.recupererCom(Id);
        String photoCom;
        photoCom=uuid;
        CS.add(comm.getText(),Idd,dat,photoCom,idUser,nom);
         afficherComm();
        //****************
    }
    
     public void afficherComm(){
        CommentaireService CS = new CommentaireService();
       List<Commentaire> AL= new ArrayList<Commentaire>();
        AL = CS.Affiche();
            ObservableList<Commentaire> liste_commentaires = FXCollections.observableArrayList(CS.Affiche());

        ListeCommentaires.setItems(liste_commentaires);
        ListeCommentaires.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() 
       {

    @Override
    public ListCell<Commentaire> call(ListView<Commentaire> p) 
    {
        return new ListCell<Commentaire>() 
        {

            ImageView IV1= new ImageView();
            
           @Override 
    protected void updateItem(Commentaire item, boolean empty) { 
        super.updateItem(item, empty); 
        setText(null); 
        if (!empty && item != null) { 
            final String text = String.format("%s %s %s",item.getComment()," Ajouté le"+item.getCreated(),item.getNom()); 
            setText(text);
            File F = new File(item.getPhoto());
        Image I = new Image(F.toURI().toString());
        IV1.setImage(I);
        IV1.setFitHeight(40);
        IV1.setFitWidth(40);
        setGraphic(IV1);
        } }       };}})  ;
        
               
        
    }

    @FXML
    private void noter(MouseEvent event) 
    {
        // if(      )
        // 
          int Idd;
        Idd=this.recupererCom(Id);
        EtablissementService ES = new EtablissementService();
        RatingService RS = new RatingService();
        Etablissement E = ES.findById(Idd);
        ES.AjoutRate(Float.valueOf(msg.getText()),Idd,E.getNbrRates(),E.getNombre());
        RS.addRating(Float.valueOf(msg.getText()),Idd,idUser);
        rate.setText(E.getRating()+" /5");   
        
    }

    @FXML
    private void DeleteComm(ActionEvent event) {
        
       idUserB=ListeCommentaires.getSelectionModel().getSelectedItem().getId_user();
       System.out.println(idUserB + " " + idUser);
         if (!ListeCommentaires.getSelectionModel().isEmpty() && (idUserB == idUser)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Etes-vous sûr de vouloir supprimer?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CommentaireService C = new CommentaireService();
                C.delete(ListeCommentaires.getSelectionModel().getSelectedItem().getId());
                afficherComm();
            } 
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Vous ne pouvez pas supprimer ce commentaire !");
            alert.showAndWait();
        }
    }

    @FXML
    private void UpdateComm(ActionEvent event) {
        int Idd=this.recupererCom(Id); 
        idUserB = ListeCommentaires.getSelectionModel().getSelectedItem().getId_user();
        System.out.println(idUserB + " " + idUser);
            if (!(ListeCommentaires.getSelectionModel().isEmpty()) && (idUserB == idUser))  {
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("/gui/ModifierCommentaire.fxml"));

            try {
                Parent root = (Parent) FL.load();
                Pane.getChildren().setAll(root);        
                gui.ModifierCommentaireController MCC = FL.getController();
                MCC.comment.setText(ListeCommentaires.getSelectionModel().getSelectedItem().getComment());
                MCC.ModifCommentaire(ListeCommentaires.getSelectionModel().getSelectedItem().getId(),Idd);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Vous ne pouvez pas modifier ce commentaire !");
            alert.showAndWait();
        }
    }

    @FXML
    private void signalerComm(ActionEvent event) {
         
        idUserB = ListeCommentaires.getSelectionModel().getSelectedItem().getId_user();
        System.out.println(idUserB + " " + idUser);
            if (!(ListeCommentaires.getSelectionModel().isEmpty()) && (idUserB != idUser))  {
        
        
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/gui/SignalerCommentaire.fxml"));

            try {
                Parent root = (Parent) fXMLLoader.load();
            Pane.getChildren().setAll(root);  
                CommentaireService C = new CommentaireService();
                gui.SignalerCommentaireController SCC=fXMLLoader.getController(); 
               int Idd=this.recupererCom(Id);

                SCC.SignalerCommentaire(ListeCommentaires.getSelectionModel().getSelectedItem().getId(),Idd,idUser);
            } catch (IOException ex) {
                System.out.println(ex);
            } }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Vous ne pouvez pas signaler ce commentaire !");
            alert.showAndWait();
        }
            
            
            
            
            
    }

    @FXML
    private void SavePhoto(ActionEvent event) {
         File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            uuid = file.getAbsolutePath();
            System.out.println(file.toURI().toString());
            imagelog = new Image(file.toURI().toString());
            imageCommentaire.setImage(imagelog);
            Utils u = new Utils();
            /*String emplacement = "C:\\Users\\Maissa\\Documents\\NetBeansProjects\\pibonsplans5''\\src\\GUI\\"+uuid;
            System.out.println(emplacement);
            u.CopyImage(emplacement, file.toPath().toString());*/
            System.out.println("rrrrr"); }}
    @FXML
    private void SetVisited(ActionEvent event) {
                Session ss= new Session ();
        int id_user;
        id_user=ss.user.id;
         LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
        if (visited.getText().equals("Marquer visité")){
        service2.add(Id, id_user);
        visited.setText("Visité");}
        else {
        service2.delete(Id, id_user);
        visited.setText("Marquer visité");
        }
    EtablissementService service3=new EtablissementService();
    
    Etablissement e=service3.findById(Id);
   
    visits.setText("Visité par "+service3.countVisited(Id)+" personne(e)");
    }

    @FXML
    private void SetLike(ActionEvent event) {
           Session ss= new Session ();
        int id_user;
        id_user=ss.user.id;
            LikedEtablissementService service1=new LikedEtablissementService();
    
    VisitedEtablissementService service2=new VisitedEtablissementService();
    if (like.getText().equals("J'aime")){
    service1.add(Id, id_user);
    like.setText("Aimé");
    }
    else {
        service1.delete(Id, id_user);
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

    @FXML
    private void Facebook(ActionEvent event) {
        
                Browser browser = new Browser();
                Browser.load("www.facebook.com", new Dimension(300, 300), new Point(30, 20));
    }
    
   
}
