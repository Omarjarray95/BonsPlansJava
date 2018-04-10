package gui;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author omar
 */
public class DirectionsFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    /**
     *
     */
    protected DirectionsService directionsService;

    /**
     *
     */
    protected DirectionsPane directionsPane;

    /**
     *
     */
    protected StringProperty from = new SimpleStringProperty();

    /**
     *
     */
    protected StringProperty to = new SimpleStringProperty();

    /**
     *
     */
    protected DirectionsRenderer directionsRenderer = null;

    /**
     *
     */
    @FXML
    protected GoogleMapView mapView;

    @FXML
    private TextField Address;
    
    private GoogleMap map;
    
    private Marker M;
    
    private GeocodingService geocodingService;
    
    private StringProperty address = new SimpleStringProperty();
    
    @FXML
    private Button ConfirmAddress;
    
    private String Addr;
            List<String> l = new ArrayList<>();
    
    String[] s;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        mapView.addMapInializedListener(this);
        address.bind(Address.textProperty());
    }

    /**
     *
     */
    @Override
    public void mapInitialized() 
    {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();
        
        LatLong LL = new LatLong(36.8064948, 10.1815316);
        
        options.center(LL)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(options);
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(LL)
                .visible(Boolean.TRUE)
                .title("My Marker");

        M = new Marker( markerOptions );

        map.addMarker(M);
    }

    @FXML
    private void AddressTextField(ActionEvent event) 
    {
//        geocodingService.geocode(Address.getText(), (GeocodingResult[] results, GeocoderStatus status) -> 
//        {
//            LatLong latLong = null;
//            if( status == GeocoderStatus.ZERO_RESULTS) 
//            {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
//                alert.show();
//                return;
//            } 
//            else if( results.length > 1 ) 
//            {
//                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
//                alert.show();
//                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
//                for(GeocodingResult GR:results)
//                {
//                    System.out.println(GR);
//                }
//            } 
//            else 
//            {
//                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
//            }
//            map.setCenter(latLong);
//            M.setPosition(latLong);
//            map.setZoom(15);
//            setAddr(results[0].getFormattedAddress());
//        });
    }

    /**
     *
     * @param results
     * @param status
     */
    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void ConfirmAddress(ActionEvent event) 
    {
        try
        {
        FXMLLoader FL1 = new FXMLLoader(getClass().getResource("Etablissement.fxml"));
        Pane root = FL1.load();
        EtablissementController EC = FL1.getController();
        String Str = this.getAddr();
        EC.PutAddr(Str);
        }
        catch (IOException E)
        {
            System.out.println("Lé !");
        }
    }

    /**
     *
     * @return
     */
    public String getAddr() 
    {
        return Addr;
    }

    /**
     *
     * @param Addr
     */
    public void setAddr(String Addr) 
    {
        this.Addr = Addr;
    }

    @FXML
    private void AddressTextFieldTypeEvent(KeyEvent event) 
    {
        geocodingService.geocode(Address.getText(), (GeocodingResult[] results, GeocoderStatus status) -> 
        {
            l.clear();  
            
            for(int i =0;i<results.length;i++)
            {
            s=new String[results.length];
            s[i] = results[i].getFormattedAddress();
            System.out.println(results[i].getJSObject());
            l.add(results[i].getFormattedAddress());
            }
            
       for (GeocodingResult result : results) 
       {    
            TextFields.bindAutoCompletion(Address, s);
       }
       
        TextFields.bindAutoCompletion(Address, t-> 
        {
            return l;
        });
            
        LatLong latLong = null;
            if( status == GeocoderStatus.ZERO_RESULTS) 
            {
                /*Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();*/
                return;
            } 
            else if( results.length > 1 ) 
            {
                /*Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();*/
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                for(GeocodingResult GR:results)
                {
                    
                }
            } 
            else 
            {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            map.setCenter(latLong);
            M.setPosition(latLong);
            map.setZoom(15);
            setAddr(results[0].getFormattedAddress());
        });
    }

}