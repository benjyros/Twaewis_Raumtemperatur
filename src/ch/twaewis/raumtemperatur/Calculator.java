/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.twaewis.raumtemperatur;

import java.math.*;

/**
 *
 * @author benny
 */
public class Calculator {

    private BigDecimal height = new BigDecimal(0);
    private BigDecimal priceHeating;
    
    private double area = 0;
    private double temperatureIn = 0;
    private double temperatureOut = 0;

    private String heating = "";

    public double getCosts() {
        return calculate();
    }

    private double calculate() {
        setHeating();
        return heatcosts(calculateHeat()).doubleValue();
    }

    private BigDecimal calculateHeat() {
        BigDecimal kelvinIn = new BigDecimal(temperatureIn + 273);
        BigDecimal kelvinOut = new BigDecimal(temperatureOut + 273);

        BigDecimal length = new BigDecimal(Math.sqrt(area));
        BigDecimal width = length;

        BigDecimal groundNRoof = (BigDecimal.valueOf(2).multiply(length.multiply(width).multiply(kelvinIn.subtract(kelvinOut))));
        BigDecimal walls = (BigDecimal.valueOf(4).multiply(length.multiply(height).multiply(kelvinIn.subtract(kelvinOut))));

        return groundNRoof.add(walls).divide(BigDecimal.valueOf(1000));
    }

    private BigDecimal heatcosts(BigDecimal heat) {
        BigDecimal costsPerHour = heat.multiply(priceHeating);
        BigDecimal costsPerMonth = costsPerHour.multiply(BigDecimal.valueOf(24).multiply(BigDecimal.valueOf(30)));
        return costsPerMonth;
    }

    private void setHeating() {
        switch(heating){
            case "Gasheizung":
                priceHeating = new BigDecimal(0.08);
                break;
            case "Ölheizung":
                priceHeating = new BigDecimal(0.11);
                break;
            case "Pellets":
                priceHeating = new BigDecimal(0.10);
                break;
            case "Wärmepumpe":
                priceHeating = new BigDecimal(0.05);              
                break;
        }
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setTemperatureIn(double temperatureIn) {
        this.temperatureIn = temperatureIn;
    }

    public void setTemperatureOut(double temperatureOut) {
        this.temperatureOut = temperatureOut;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }
    
    public boolean OutIsGreaterThanIn () {
        return temperatureOut >= temperatureIn;
    }
}
