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
public class CheckEntries {
    
    private BigDecimal area = new BigDecimal(0);
    private BigDecimal height = new BigDecimal(0);
    
    private String heating = "";
    private String residence = "";
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
    
    public boolean correctArea() {
        return area.intValue() != 0;
    }
    
    public boolean correctHeight() {
        return height.intValue() != 0;
    }
    
    public boolean correctHeating() {
        return !heating.equals("");
    }
    
    public boolean correctResidence() {
        return !residence.equals("");
    }
}
