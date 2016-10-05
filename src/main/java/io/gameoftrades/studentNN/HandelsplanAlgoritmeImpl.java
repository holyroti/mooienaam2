/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.algoritme.HandelsplanAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.markt.Handel;
import io.gameoftrades.model.markt.HandelType;
import io.gameoftrades.model.markt.Handelsplan;
import io.gameoftrades.model.markt.Handelswaar;
import io.gameoftrades.model.markt.actie.HandelsPositie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 *
 * @author mrctje
 */
public class HandelsplanAlgoritmeImpl implements HandelsplanAlgoritme{

    @Override
    public Handelsplan bereken(Wereld wereld, HandelsPositie hp) {
        final int SEARCHRADIUS = 1;

        int kapitaal = hp.getKapitaal();
        int maxTime = hp.getMaxActie();
        int capacity = hp.getRuimte();
        int timeLeft = hp.getTotaalActie();
        int winst = hp.getTotaalWinst();
        Stad beginStad = hp.getStad();
        
        ArrayList<Integer> winstList = new ArrayList<>();
        ArrayList<Handel> handelBiedt = new ArrayList<>();
        ArrayList<Handel> handelVraagt = new ArrayList<>();
        TreeMap<Handel, Integer> winstMap = new TreeMap<>();
        /*
        * Zoek en koop in meest winstgevende product bij start in nabije omgeving (1/10e tijd)
        * Travel naar stad voor verkooop
        * Repeat
         */
        for(Handel handel: wereld.getMarkt().getHandel()){
            if(handel.getHandelType().equals(HandelType.BIEDT) && handel.getStad().equals(beginStad) )
                handelBiedt.add(handel);

            if(handel.getHandelType().equals(HandelType.VRAAGT) && new SnelstePadAlgoritmeImpl(false).bereken(wereld.getKaart() ,beginStad.getCoordinaat(), handel.getStad().getCoordinaat()).getTotaleTijd() < timeLeft/SEARCHRADIUS)
                handelVraagt.add(handel);
        }

        for(Handel handelVraag: handelVraagt){
            for(Handel handelAanbod : handelBiedt){
                if(handelVraag.getHandelswaar().getNaam().equals(handelAanbod.getHandelswaar().getNaam())) {
                    if(winstMap.containsKey(handelVraag)) {
                        if (winstMap.get(handelVraag).compareTo(handelVraag.getPrijs() - handelAanbod.getPrijs()) < 0)
                            winstMap.put(handelVraag, handelVraag.getPrijs() - handelAanbod.getPrijs());
                    }else {
                        winstMap.put(handelVraag, handelVraag.getPrijs() - handelAanbod.getPrijs());
                    }
                }
            }
        }
        System.out.println(handelBiedt.size() + " " + handelVraagt.size() );
//        for(Handel handel: wereld.getMarkt().getHandel()){
//            System.out.println(handel.getPrijs());
//        }
        return new Handelsplan(null);
    }

    @Override
    public String toString() {
        return "Handelsplan algorithme";
    }
}
