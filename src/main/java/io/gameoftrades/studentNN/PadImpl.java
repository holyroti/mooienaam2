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
    public int getTotaleTijd() { return calculateTotalTIme(terreinen, 0); }

    private int calculateTotalTIme(List<Terrein> t, int cost) {
        if(t.size() == 0) return cost;
        else {
            Terrein ter = t.get(0);
            t.remove(0);
            return calculateTotalTIme(t, cost + ter.getTerreinType().getBewegingspunten());
        }
    }

    @Override
    public Richting[] getBewegingen() {
        ArrayList<Richting> richtingen = new ArrayList<>();
//        for (int i = 0; i < terreinen.size() -1; i++) {
//              richtingen.add(Richting.tussen(terreinen.get(i).getCoordinaat(), terreinen.get(i+1).getCoordinaat()));
//        }
//        return richtingen.toArray(new Richting[richtingen.size()]);
        return getBeweging2(terreinen, richtingen);
    }

    private Richting[] getBeweging2(List<Terrein> t, ArrayList<Richting> richting) {
        if (terreinen.size() == richting.size()) return richting.toArray(new Richting[richting.size()]);
        else {
            Terrein ter = t.get(0);
            Terrein ter2 = t.get(1);
            t.remove(0);
            richting.add(Richting.tussen(ter.getCoordinaat(), ter2.getCoordinaat()));
            return getBeweging2(t, richting);
        }
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
