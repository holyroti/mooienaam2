/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.algoritme.HandelsplanAlgoritme;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.markt.Handel;
import io.gameoftrades.model.markt.HandelType;
import io.gameoftrades.model.markt.Handelsplan;
import io.gameoftrades.model.markt.Handelswaar;
import io.gameoftrades.model.markt.actie.HandelsPositie;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author mrctje
 */
public class HandelsplanAlgoritmeImpl implements HandelsplanAlgoritme{

    @Override
    public Handelsplan bereken(Wereld wereld, HandelsPositie hp) {
        int kapitaal = hp.getKapitaal();
        int maxTime = hp.getMaxActie();
        int capacity = hp.getRuimte();
        int timeLeft = hp.getTotaalActie();
        int winst = hp.getTotaalWinst();
        Stad beginStad = hp.getStad();
        
        ArrayList<Integer> winstList = new ArrayList<>();
        
        for(Handel handel: wereld.getMarkt().getHandel()){
//            if(handel.getHandelType().equals(HandelType.BIEDT))
                
                
        }
//        for(Handel handel: wereld.getMarkt().getHandel()){
//            System.out.println(handel.getPrijs());
//        }
        System.out.println(wereld.getMarkt().getHandel().get(0).getHandelswaar());
        return new Handelsplan(null);
    }

    @Override
    public String toString() {
        return "Handelsplan algorithme";
    }
    
    
}
