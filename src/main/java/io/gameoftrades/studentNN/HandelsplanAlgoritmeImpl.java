/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.algoritme.HandelsplanAlgoritme;
import io.gameoftrades.model.markt.Handelsplan;
import io.gameoftrades.model.markt.actie.HandelsPositie;

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
        
        
        return null;
    }
    
}
