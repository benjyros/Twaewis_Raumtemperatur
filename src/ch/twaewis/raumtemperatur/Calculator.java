/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.twaewis.raumtemperatur;

import java.math.BigDecimal;

/**
 *
 * @author benny
 */
public class Calculator {

    private BigDecimal area = new BigDecimal(0);
    private BigDecimal height = new BigDecimal(0);
    private BigDecimal temperature = new BigDecimal(0);

    private String heating = "";
    private String residence = "";

    public double getCosts() {
        return calculate();
    }
    
    private double calculate(){
        
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
