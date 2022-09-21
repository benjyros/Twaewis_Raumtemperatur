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
    private final StringProperty temperatureIn = new SimpleStringProperty();
    private final StringProperty temperatureOut = new SimpleStringProperty();

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

            checkEntries.setArea(check);
            calculator.setArea(check);
        } catch (NumberFormatException e) {
            checkEntries.setArea(0);
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

    public void setTemperatureIn(double temperature) {
        try {
            calculator.setTemperatureIn(temperature);
        } catch (NumberFormatException e) {
            calculator.setTemperatureIn(21);
        }
    }

    public void setTemperatureOut(double temperature) {
        try {
            calculator.setTemperatureOut(temperature);
        } catch (NumberFormatException e) {
            calculator.setTemperatureOut(-2);
        }
    }

    public void setHeating(String heating) {
        checkEntries.setHeating(heating);
        calculator.setHeating(heating);
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

        setError(error);
    }

    public boolean checkEntries() {
        setErrorProperty();
        return checkEntries.correctArea() && checkEntries.correctHeight() && checkEntries.correctHeating();
    }

    public void calculate() {
        if (calculator.OutIsGreaterThanIn()) {
            setCosts("Keine Kosten: Ihr Raum muss nicht mehr beheizt werden.");
        } else if (checkEntries()) {
            setCosts("Heizkosten: CHF " + String.valueOf(calculator.getCosts()) + " / pro Monat");
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

    public void setTemperatureInProperty(double value) {
        DecimalFormat df = new DecimalFormat("##.#");
        temperatureIn.set(df.format(value) + " Grad");
    }

    public void setTemperatureOutProperty(double value) {
        DecimalFormat df = new DecimalFormat("##.#");
        temperatureOut.set(df.format(value) + " Grad");
    }

    public StringProperty temperatureInProperty() {
        return temperatureIn;
    }

    public StringProperty temperatureOutProperty() {
        return temperatureOut;
    }
}
