/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Terrein;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrctje
 */
public class SnelstePadAlgoritmeImpl implements SnelstePadAlgoritme{

    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat end) {
        List<Terrein> open = new ArrayList<>();
        List<Terrein> closed = new ArrayList<>();
        open.add(kaart.getTerreinOp(start));
        
        
        
        
        return null;
    }
    
}
