/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.kaart.Terrein;

/**
 *
 * @author mrctje
 */
public class Cost {
    private double fCost;
    private double gCost;
    private double hCost;
    private Terrein parent;
    
    public Cost(){
        gCost = 0;
        hCost = 0;
    }
    public double getFCost(){
        return getgCost() + gethCost();
    }

    /**
     * @return the gCost
     */
    public double getgCost() {
        return gCost;
    }

    /**
     * @param gCost the gCost to set
     */
    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    /**
     * @return the hCost
     */
    public double gethCost() {
        return hCost;
    }

    /**
     * @param hCost the hCost to set
     */
    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    /**
     * @return the parent
     */
    public Terrein getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Terrein parent) {
        this.parent = parent;
    }
    
    
}
