/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Paragraph;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import entities.Evenement;
import entities.Session;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.implementation.EtablissementService;
import services.implementation.EvenementService;
import services.implementation.GoingEventService;
import services.implementation.InterestedEventService;

/**
 * FXML Controller class
 *
 * @author Ons Ben Othmen
 */
public class EvenementProfileController implements Initializable {
    public int Id;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Hyperlink adresse;
    @FXML
    private JFXButton PDF;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    @FXML
    private ToggleButton interest;
    @FXML
    private ToggleButton going;
    InterestedEventService service1=new InterestedEventService();
    GoingEventService service2=new GoingEventService();
    @FXML
    private Label date;
    @FXML
    private Text nom;
    @FXML
    private Label des;
    @FXML
    private Text interests;
    @FXML
    private Text goings;
    @FXML
    private JFXButton modifer;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    /**
     * Initializes the controller class.
     */
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
    private void SetInterest(ActionEvent event) {
         Session ss= new Session ();
        int id_user=ss.user.id;
        if (interest.getText().equals("Intéressé(e)")){
        service1.delete(id_user, Id);
        interest.setText("Marquer intéressé(e)");
        }
        else {service1.add(id_user,Id);
        if (going.getText().equals("Partant(e)")){
        service2.delete(id_user, Id);
        going.setText("Marquer partant(e)");
        }
        interest.setText("Intéressé(e)");
        }
        EvenementService service=new EvenementService();
        Evenement e=service.findById(Id);
        goings.setText(e.getNbr_personnes()+" personnes partantes");
        interests.setText(e.getInteresses()+" personnes intéreéssées");
    }

    @FXML
    private void SetGoing(ActionEvent event) {
         Session ss= new Session ();
        int id_user=ss.user.id;
        if (going.getText().equals("Partant(e)")){
        service2.delete(id_user,Id);
        going.setText("Marquer partant(e)");}
        else {
        service2.add(id_user,Id);
        if (interest.getText().equals("Intéressé(e)")){
        service1.delete(id_user,Id );
        interest.setText("Marquer intéressé(e)");
        }
        going.setText("Partant(e)");
        }
        EvenementService service=new EvenementService();
        Evenement e=service.findById(Id);
        goings.setText(e.getNbr_personnes()+" personnes partantes");
        interests.setText(e.getInteresses()+" personnes intéreéssées");
    }
    public void loadData(int in){
        Session ss= new Session ();
        int id_user=ss.user.id;
            
        EvenementService service=new EvenementService();
        Evenement e=service.findById(in);
           EtablissementService etabService=new EtablissementService();
          Etablissement etab=etabService.findById(e.getId_etablissement());
        if (id_user==etab.getResponsable()){
        interest.setVisible(false);
        going.setVisible(false);
        }
          
          else{
        modifer.setVisible(false);
        supp.setVisible(false);
        if (service1.count(id_user, in)==0){
        interest.setText("Marquer intéressé(e)");}
        else interest.setText("Intéressé(e)");
        if (service2.count(id_user, in)==0){
        going.setText("Marquer partant(e)");}
        else going.setText("Partant(e)");
        }        System.out.println(in);

          System.out.println(etab.getNom());
                  adresse.setText(etab.getNom());
                    adresse.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id_etab = etab.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id_etab);
                    MainPane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(ActualitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
          nom.setText(e.getNom());
          date.setText(e.getDate().toLocalDate().toString());
          des.setText(e.getDescription()); 
          goings.setText(e.getNbr_personnes()+" personnes partantes");
          interests.setText(e.getInteresses()+" personnes intéreéssées");
          setId(in);
    }

    @FXML
    private void modify(ActionEvent event) {
 try {
                    
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateEvent.fxml"));
                    Parent root = (Parent) FL.load();
                    UpdateEventController EVC = FL.getController();
                    System.out.println(Id);
                    EVC.LoadData(Id);
                    MainPane.getChildren().setAll(root);
                        
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

    @FXML
    private void delete(ActionEvent event) {
                        EvenementService service=new EvenementService();
                        service.delete(service.findById(Id));
                         try {
                        Parent page1 = FXMLLoader.load(getClass().getResource("ListEvents.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Bons Plans - Les évenements courants");
                        stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
    }

    @FXML
    private void onload(ActionEvent event) throws IOException, DocumentException {
         WritableImage image = nom.snapshot(new SnapshotParameters(), null);
         WritableImage image2 = date.snapshot(new SnapshotParameters(), null);
      
         WritableImage image3 = des.snapshot(new SnapshotParameters(), null);
      
         File file = new File("chart.png");
         File file2 = new File("chart2.png");
         File file3 = new File("chart3.png");

         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
         ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file2);
         ImageIO.write(SwingFXUtils.fromFXImage(image3, null), "png", file3);

    OutputStream filex = new FileOutputStream(new File("C:\\Users\\Ons Ben Othmen\\Desktop\\PiJava\\BonsPlansJava\\PDF\\Test.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, filex);

            document.open();
            
            com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance("chart.png");
            com.itextpdf.text.Image img2 = com.itextpdf.text.Image.getInstance("chart2.png");
            com.itextpdf.text.Image img3 = com.itextpdf.text.Image.getInstance("chart3.png");
            
    

            document.add(img1);
            document.add(img2);
            document.add(img3);
    


            document.add(new Paragraph(new java.util.Date().toString()));
            document.close();
            filex.close();
                    Desktop.getDesktop().open(new File("C:\\Users\\Ons Ben Othmen\\Desktop\\PiJava\\BonsPlansJava\\PDF\\Test.pdf"));

        
    }
    
}
