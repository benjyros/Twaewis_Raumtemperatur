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
    Model model = new Model();

    @FXML
    private TextField tfArea;
    @FXML
    private TextField tfHeight;
    @FXML
    private ComboBox<String> cBoxHeating;
    @FXML
    private Label lblCosts;
    @FXML
    private Label lblError;
    @FXML
    private Button btnCalculate;
    @FXML
    private Slider sliderTemperatureIn;
    @FXML
    private Label lblTemperatureIn;
    @FXML
    private Slider sliderTemperatureOut;
    @FXML
    private Label lblTemperatureOut;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cBoxHeating.setItems(listHeating);
        
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
        
        sliderTemperatureIn.valueProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                //set the value of mynumber property into the table
                model.setTemperatureInProperty(sliderTemperatureIn.getValue());
                model.setTemperatureIn(sliderTemperatureIn.getValue());
                
                if(sliderTemperatureIn.getValue() < 19){
                    lblTemperatureIn.setTextFill(Color.web("#0000ff"));
                }
                else if(sliderTemperatureIn.getValue() < 23){
                    lblTemperatureIn.setTextFill(Color.web("#ff8800"));
                }
                else{
                    lblTemperatureIn.setTextFill(Color.web("#ff0000"));
                }
                
                if(model.getBtnpressed()){
                    model.calculate();
                }
            }
        });
        
        sliderTemperatureOut.valueProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                //set the value of mynumber property into the table
                model.setTemperatureOutProperty(sliderTemperatureOut.getValue());
                model.setTemperatureOut(sliderTemperatureOut.getValue());
                
                if(sliderTemperatureOut.getValue() < -5){
                    lblTemperatureOut.setTextFill(Color.web("#8800ff"));
                }
                else if(sliderTemperatureOut.getValue() < 10){
                    lblTemperatureOut.setTextFill(Color.web("#0000ff"));
                }
                else{
                    lblTemperatureOut.setTextFill(Color.web("#ff8800"));
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
        
        lblError.textProperty().bind(model.errorProperty());
        lblTemperatureIn.textProperty().bind(model.temperatureInProperty());
        lblTemperatureOut.textProperty().bind(model.temperatureOutProperty());
    }

    @FXML
    private void handleBtn_calculate(ActionEvent event) {
        if(model.checkEntries()) {
            model.setTemperatureIn(sliderTemperatureIn.getValue());
            model.setTemperatureOut(sliderTemperatureOut.getValue());
                
            lblCosts.textProperty().bind(model.costsProperty());
            
            model.setBtnpressed(true);
            model.calculate();
            
            btnCalculate.setVisible(false);
        }
    }
}
