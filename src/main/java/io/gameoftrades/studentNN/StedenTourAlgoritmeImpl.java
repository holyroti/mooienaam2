/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.debug.AsciiArtDebugger;
import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author mrctje
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme{

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> list) {
        AsciiArtDebugger debug = new AsciiArtDebugger();
        debug.debugSteden(kaart, list);
        return list;
    }

    @Override
    public String toString() {
        return "Travelling salesman problem";
    }
    
    
    
}
