/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package ch.twaewis.raumtemperatur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author benny
 */
public class ViewController implements Initializable {

    ObservableList<String> listHeating = FXCollections.observableArrayList("Gasheizung", "Ölheizung", "Pellets", "Wärmepumpe");
    ObservableList<String> listResidence = FXCollections.observableArrayList(
            "Aargau", "Appenzell Ausserrhoden", "Appenzell Innerrhoden", 
            "Basel-Landschaft", "Basel-Stadt", "Bern", "Freiburg", "Genf",
            "Glarus", "Graubünden", "Jura", "Luzern", "Neuenburg", "Nidwalden",
            "Obwalden", "Schaffhausen", "Schwyz", "Solothurn", "St. Gallen",
            "Tessin", "Thurgau", "Uri", "Waadt", "Wallis", "Zug", "Zürich"
    );
    Model model = new Model();

    @FXML
    private TextField tfArea;
    @FXML
    private TextField tfHeight;
    @FXML
    private ComboBox<String> cBoxHeating;
    @FXML
    private ComboBox<String> cBoxResidence;
    @FXML
    private Label lblCosts;
    @FXML
    private Label lblError;
    @FXML
    private Slider sliderTemperature;
    @FXML
    private Button btnCalculate;
    @FXML
    private Label lblTemperature;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cBoxHeating.setItems(listHeating);
        cBoxResidence.setItems(listResidence);
        
        tfArea.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //set the value of mynumber property into the table
                model.setArea(tfArea.getText());
                model.setErrorProperty();
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        tfHeight.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //set the value of mynumber property into the table
                model.setHeight(tfHeight.getText());
                model.setErrorProperty();
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        sliderTemperature.valueProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                //set the value of mynumber property into the table
                model.setTemperatureProperty(sliderTemperature.getValue());
                model.setTemperature(sliderTemperature.getValue());
                
                if(sliderTemperature.getValue() < 19){
                    lblTemperature.setTextFill(Color.web("#0000ff"));
                }
                else if(sliderTemperature.getValue() < 23){
                    lblTemperature.setTextFill(Color.web("#ff8800"));
                }
                else{
                    lblTemperature.setTextFill(Color.web("#ff0000"));
                }
                
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        cBoxHeating.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //set the value of mynumber property into the table
                model.setHeating(cBoxHeating.getValue());
                model.setErrorProperty();
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        cBoxResidence.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //set the value of mynumber property into the table
                model.setResidence(cBoxResidence.getValue());
                model.setErrorProperty();
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        lblError.textProperty().bind(model.errorProperty());
        lblTemperature.textProperty().bind(model.temperatureProperty());
    }

    @FXML
    private void handleBtn_calculate(ActionEvent event) {
        if(model.checkEntries()) {
            lblCosts.textProperty().bind(model.costsProperty());
            model.setBtnpressed(true);
            model.calculate();
            btnCalculate.setVisible(false);
        }
    }
}
