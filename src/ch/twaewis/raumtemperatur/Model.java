/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.twaewis.raumtemperatur;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author benny
 */
public class Model {

    private final StringProperty costs = new SimpleStringProperty();
    private final StringProperty error = new SimpleStringProperty();
    private final StringProperty temperature = new SimpleStringProperty();

    private final CheckEntries checkEntries;
    private final Calculator calculator;

    private boolean btnpressed = false;

    public Model() {
        checkEntries = new CheckEntries();
        calculator = new Calculator();
    }

    public void setArea(String area) {
        try {
            double check = Double.parseDouble(area);

            checkEntries.setArea(new BigDecimal(area));
            calculator.setArea(new BigDecimal(area));
        } catch (NumberFormatException e) {
            checkEntries.setArea(new BigDecimal(0));
        }
    }

    public void setHeight(String height) {
        try {
            double check = Double.parseDouble(height);

            checkEntries.setHeight(new BigDecimal(height));
            calculator.setHeight(new BigDecimal(height));
        } catch (NumberFormatException e) {
            checkEntries.setHeight(new BigDecimal(0));
        }
    }

    public void setTemperature(double temperature) {
        try {
            calculator.setTemperature(BigDecimal.valueOf(temperature));
        } catch (NumberFormatException e) {
            calculator.setTemperature(BigDecimal.valueOf(21));
        }
    }

    public void setHeating(String heating) {
        checkEntries.setHeating(heating);
        calculator.setHeating(heating);
    }

    public void setResidence(String residence) {
        checkEntries.setResidence(residence);
        calculator.setResidence(residence);
    }

    public void setErrorProperty() {
        String error = "";

        if (!checkEntries.correctArea()) {
            error += "Bitte geben Sie eine richtige Fläche ein.\n";
        }
        if (!checkEntries.correctHeight()) {
            error += "Bitte geben Sie eine richtige Höhe ein.\n";
        }
        if (!checkEntries.correctHeating()) {
            error += "Bitte geben Sie einen Heizungstyp ein.\n";
        }
        if (!checkEntries.correctResidence()) {
            error += "Bitte geben Sie einen Wohnort ein.\n";
        }

        setError(error);
    }

    public boolean checkEntries() {
        return checkEntries.correctArea() && checkEntries.correctHeight() && checkEntries.correctHeating() && checkEntries.correctResidence();
    }

    public void calculate() {
        if (checkEntries()) {
            setCosts(String.valueOf(calculator.getCosts()));
        } else {
            setCosts("n/A");
        }
    }

    public void setBtnpressed(boolean pressed) {
        btnpressed = pressed;
    }

    public boolean getBtnpressed() {
        return btnpressed;
    }

    public void setCosts(String value) {
        costs.set(value);
    }

    public StringProperty costsProperty() {
        return costs;
    }

    public void setError(String value) {
        error.set(value);
    }

    public StringProperty errorProperty() {
        return this.error;
    }

    public void setTemperatureProperty(double value) {
        DecimalFormat df = new DecimalFormat("##.#");
        temperature.set(df.format(value) + " Grad");
    }

    public StringProperty temperatureProperty() {
        return temperature;
    }
}
