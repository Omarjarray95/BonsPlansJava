package gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import entities.Etablissement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import org.controlsfx.control.RangeSlider;
import services.implementation.EtablissementService;

public class AffichagePaneController implements Initializable {

    @FXML
    private GridPane Grid;
    @FXML
    private Pane Pane;
    @FXML
    private TextField RechNom;
    @FXML
    private Button Rechercher;
    @FXML
    private RangeSlider BMSlider;
    @FXML
    private Label MinValue;
    @FXML
    private Label MaxValue;
    @FXML
    private RadioButton HOASC;
    @FXML
    private RadioButton HFDESC;
    @FXML
    private Button Confirmer;
    @FXML
    private RadioButton BMASC;
    @FXML
    private RadioButton BMDESC;
    
    private final ToggleGroup TG = new ToggleGroup();
    
    private final EtablissementService ES0 = new EtablissementService();
    
    private ArrayList<Etablissement> ESL0 = new ArrayList<>();
    
    @FXML
    private Pagination Pagination;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger Hamburger;
    
    private String type;
    
    /**
     * Initializes the controller class.
     */
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
        ESL0 = ES0.Affiche();
        int P = (ESL0.size()/4);
        if(ESL0.size()%4>0)
        {
            P++;
        }
        Pagination.setPageCount(P);
        BMSlider.highValueProperty().addListener(new ChangeListener<Number>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) 
                    {
                        String T = Integer.toString(new_val.intValue());
                        MaxValue.setText(T + " DT");
                    }
                });
        BMSlider.lowValueProperty().addListener(new ChangeListener<Number>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) 
                    {
                        String T = Integer.toString(new_val.intValue());
                        MinValue.setText(T + " DT");
                    }
                });
        HOASC.setUserData("HOASC");
        HFDESC.setUserData("HFDESC");
        BMASC.setUserData("BMASC");
        BMDESC.setUserData("BMDESC");
        HOASC.setToggleGroup(TG);
        HFDESC.setToggleGroup(TG);
        BMASC.setToggleGroup(TG);
        BMDESC.setToggleGroup(TG);
        
        Pagination.setPageFactory(new Callback<Integer, Node>() 
        {
            @Override
            public Node call(Integer pageIndex) 
            {   
        Grid.getChildren().clear();
        ArrayList<Etablissement> ESL1 = new ArrayList<>();
        int k1 = 0 + pageIndex*4;
        int l = 4 + pageIndex*4;
        if (l > ESL0.size())
                {
                    l = ESL0.size();
                }
        for (int k = k1; k < l; k++)
                {
                    ESL1.add(ESL0.get(k));
                }
        int i = -1;
        int j = 0;
        for (Etablissement E : ESL1)
        {
            VBox VB = new VBox();
            VB.setPadding(new Insets(0,10,0,10));
            File F = new File(E.getImage());
            Image I = new Image(F.toURI().toString());
            ImageView IV = new ImageView();
            IV.setImage(I);
            IV.setFitHeight(150);
            IV.setFitWidth(150);
            Hyperlink Nom1 = new Hyperlink(E.getNom());
            Nom1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            i++;
            if (i == 4)
                                   {
                break;
            }
            Grid.add(VB, i, j);
        }
                return Grid;
            }
        });
    }  

    @FXML
    private void RechercherParNom(ActionEvent event) 
    {
        String S = RechNom.getText();
        //EtablissementService ES = new EtablissementService();
        ESL0 = ES0.FindByName(S);
        ESL0.addAll(ES0.FindByTag(S));
        for(Etablissement E : ESL0)
        {
            int i = 0;
            for(Etablissement E1 : ESL0)
            {
                if(E.getId()==E1.getId())
                {
                    i++;
                }
            }
            if (i > 1)
            {
                ESL0.remove(E);
            }
        }
        int P = (ESL0.size()/4);
        if(ESL0.size()%4>0)
        {
            P++;
        }
        Pagination.setPageCount(P);
        Grid.getChildren().clear();
        Pagination.setPageFactory(new Callback<Integer, Node>() 
        {
            @Override
            public Node call(Integer pageIndex) 
            {   
        Grid.getChildren().clear();
        ArrayList<Etablissement> ESL1 = new ArrayList<>();
        int k1 = 0 + pageIndex*4;
        int l = 4 + pageIndex*4;
        if (l > ESL0.size())
                {
                    l = ESL0.size();
                }
        for (int k = k1; k < l; k++)
                {
                    ESL1.add(ESL0.get(k));
                }
        int i = -1;
        int j = 0;
        for (Etablissement E : ESL1)
        {
            VBox VB = new VBox();
            VB.setPadding(new Insets(0,10,0,10));
            File F = new File(E.getImage());
            Image I = new Image(F.toURI().toString());
            ImageView IV = new ImageView();
            IV.setImage(I);
            IV.setFitHeight(150);
            IV.setFitWidth(150);
            Hyperlink Nom1 = new Hyperlink(E.getNom());
            Nom1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            i++;
            if (i == 4)
            {
                break;
            }
            Grid.add(VB, i, j);
        }
                return Grid;
            }
        });
    }

    @FXML
    private void RPN(KeyEvent event) 
    {
        String S = RechNom.getText();
        //EtablissementService ES = new EtablissementService();
        ESL0 = ES0.FindByName(S);
        ESL0.addAll(ESL0.size(),ES0.FindByTag(S));
        for(Etablissement E : ESL0)
        {
            int i = 0;
            for(Etablissement E1 : ESL0)
            {
                if(E.getId()==E1.getId())
                {
                    i++;
                }
            }
            if (i > 1)
            {
                ESL0.remove(E);
            }
        }
        int P = (ESL0.size()/4);
        if(ESL0.size()%4>0)
        {
            P++;
        }
        Pagination.setPageCount(P);
        Grid.getChildren().clear();
        Pagination.setPageFactory(new Callback<Integer, Node>() 
        {
            @Override
            public Node call(Integer pageIndex) 
            {   
        Grid.getChildren().clear();
        ArrayList<Etablissement> ESL1 = new ArrayList<>();
        int k1 = 0 + pageIndex*4;
        int l = 4 + pageIndex*4;
        if (l > ESL0.size())
                {
                    l = ESL0.size();
                }
        for (int k = k1; k < l; k++)
                {
                    ESL1.add(ESL0.get(k));
                }
        int i = -1;
        int j = 0;
        for (Etablissement E : ESL1)
        {
            VBox VB = new VBox();
            VB.setPadding(new Insets(0,10,0,10));
            File F = new File(E.getImage());
            Image I = new Image(F.toURI().toString());
            ImageView IV = new ImageView();
            IV.setImage(I);
            IV.setFitHeight(150);
            IV.setFitWidth(150);
            Hyperlink Nom1 = new Hyperlink(E.getNom());
            Nom1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            i++;
            if (i == 4)
            {
                break;
            }
            Grid.add(VB, i, j);
        }
                return Grid;
            }
        });
    }

    @FXML
    private void RechercherParPara(ActionEvent event) 
    {
        List<Etablissement> ALE;
        final int MaV = 100;
        final int MiV = 0;
        ALE = ESL0.stream().filter((e)->e.getBudgetmoyen()<=MaV).filter((e)->e.getBudgetmoyen()>=MiV).sorted(Comparator.comparing(Etablissement::getNom)).collect(Collectors.toList());
        String S = " ";
        if (BMSlider.highValueProperty().intValue()!=1)
        {
            final int MaV1 = BMSlider.highValueProperty().intValue();
            final int MiV1 = BMSlider.lowValueProperty().intValue();
            ALE = ESL0.stream().filter((e)->e.getBudgetmoyen()<=MaV1).filter((e)->e.getBudgetmoyen()>=MiV1).sorted(Comparator.comparing(Etablissement::getNom)).collect(Collectors.toList());
        }
        if (TG.getSelectedToggle() != null )
        {
        S = TG.getSelectedToggle().getUserData().toString();
        if (S.equals("HOASC"))
        {
            ALE = ALE.stream().sorted(Comparator.comparing(Etablissement::getHoraire_ouverture)).collect(Collectors.toList());
        }
        if (S.equals("HFDESC"))
        {
            ALE = ALE.stream().sorted(Comparator.comparing(Etablissement::getHoraire_fermeture).reversed()).collect(Collectors.toList());
        }
        if (S.equals("BMASC"))
        {
            ALE = ALE.stream().sorted(Comparator.comparing(Etablissement::getBudgetmoyen)).collect(Collectors.toList());
        }
        if (S.equals("BMDESC"))
        {
            ALE = ALE.stream().sorted(Comparator.comparing(Etablissement::getBudgetmoyen).reversed()).collect(Collectors.toList());
        }
        }
        Grid.getChildren().clear();
        final List<Etablissement> ALE0 = ALE;
        int P = (ALE0.size()/4);
        if(ALE.size()%4>0)
        {
            P++;
        }
        Pagination.setPageCount(P);
        Pagination.setPageFactory(new Callback<Integer, Node>() 
        {
            @Override
            public Node call(Integer pageIndex) 
            {   
        Grid.getChildren().clear();
        ArrayList<Etablissement> ESL1 = new ArrayList<>();
        int k1 = 0 + pageIndex*4;
        int l = 4 + pageIndex*4;
        if (l > ALE0.size())
                {
                    l = ALE0.size();
                }
        for (int k = k1; k < l; k++)
                {
                    ESL1.add(ALE0.get(k));
                }
        int i = -1;
        int j = 0;
        for (Etablissement E : ESL1)
        {
            VBox VB = new VBox();
            VB.setPadding(new Insets(0,10,0,10));
            File F = new File(E.getImage());
            Image I = new Image(F.toURI().toString());
            ImageView IV = new ImageView();
            IV.setImage(I);
            IV.setFitHeight(150);
            IV.setFitWidth(150);
            Hyperlink Nom1 = new Hyperlink(E.getNom());
            Nom1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            Label LBM = new Label(Integer.toString(E.getBudgetmoyen()) + " DT");
            Label LHOHF = new Label(E.getHoraire_ouverture() + " - " + E.getHoraire_fermeture());
            LBM.setFont(Font.font("Verdana",FontWeight.BOLD,12));
            LHOHF.setFont(Font.font("Verdana",FontWeight.BOLD,12));
            VB.getChildren().addAll(IV,Nom1,LBM,LHOHF);
            i++;
            if (i == 4)
            {
                break;
            }
            Grid.add(VB, i, j);
        }
                return Grid;
            }
        });
    }
    
    public void AfficheParTag(ArrayList<Etablissement> AL)
    {
        int P = (AL.size()/4);
        if(AL.size()%4>0)
        {
            P++;
        }
        Pagination.setPageCount(P);
        Grid.getChildren().clear();
        Pagination.setPageFactory(new Callback<Integer, Node>() 
        {
            @Override
            public Node call(Integer pageIndex) 
            {   
        Grid.getChildren().clear();
        ArrayList<Etablissement> ESL1 = new ArrayList<>();
        int k1 = 0 + pageIndex*4;
        int l = 4 + pageIndex*4;
        if (l > AL.size())
                {
                    l = AL.size();
                }
        for (int k = k1; k < l; k++)
                {
                    ESL1.add(AL.get(k));
                }
        int i = -1;
        int j = 0;
        for (Etablissement E : ESL1)
        {
            VBox VB = new VBox();
            VB.setPadding(new Insets(0,10,0,10));
            File F = new File(E.getImage());
            Image I = new Image(F.toURI().toString());
            ImageView IV = new ImageView();
            IV.setImage(I);
            IV.setFitHeight(150);
            IV.setFitWidth(150);
            Hyperlink Nom1 = new Hyperlink(E.getNom());
            Nom1.setOnAction(new EventHandler<ActionEvent>() 
            {
            @Override
            public void handle(ActionEvent e)
            {
                try 
                {
                    int Id = E.getId();
                    FXMLLoader FL = new FXMLLoader(getClass().getResource("EtablissementVBox.fxml"));
                    Parent root = (Parent) FL.load();
                    EtablissementVBoxController EVC = FL.getController();
                    EVC.ShowEtablissement(Id);
                    Pane.getChildren().setAll(root);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(AffichagePaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
            Nom1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
            VB.setAlignment(Pos.BASELINE_CENTER);
            VB.getChildren().addAll(IV,Nom1);
            i++;
            if (i == 4)
            {
                break;
            }
            Grid.add(VB, i, j);
        }
                return Grid;
            }
        });
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
