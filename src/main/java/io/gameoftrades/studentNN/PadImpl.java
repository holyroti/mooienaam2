/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Richting;
import io.gameoftrades.model.kaart.Terrein;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mrctje
 */
public class PadImpl implements Pad{
    
    List<Terrein> terreinen;
    
    public PadImpl(List<Terrein> terreinen){
        this.terreinen = terreinen;
    }

    @Override
    public int getTotaleTijd() {
        int cost = 0;
        for(Terrein terrein: terreinen){
            cost += terrein.getTerreinType().getBewegingspunten();
        }
        return cost;
    }

    @Override
    public Richting[] getBewegingen() {
        ArrayList<Richting> richtingen = new ArrayList<>();
        for (int i = 0; i < terreinen.size() -1; i++) {
              richtingen.add(Richting.tussen(terreinen.get(i).getCoordinaat(), terreinen.get(i+1).getCoordinaat()));
        }
        return (Richting[])richtingen.toArray(new Richting[richtingen.size()]);
        
        
    }

    @Override
    public Pad omgekeerd() {
        List<Terrein> reverse = terreinen.subList(0, terreinen.size());
        Collections.reverse(reverse);
        return new PadImpl(reverse);
    }

    @Override
    public Coordinaat volg(Coordinaat coord) {
        return terreinen.get(terreinen.size()).getCoordinaat();
    }
    
}
