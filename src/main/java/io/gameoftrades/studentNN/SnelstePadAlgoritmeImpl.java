/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.debug.AsciiArtDebugger;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.model.algoritme.SnelstePadAlgoritme;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Richting;
import io.gameoftrades.model.kaart.Terrein;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mrctje
 */
public class SnelstePadAlgoritmeImpl implements SnelstePadAlgoritme{
    private final Map<Terrein, Cost> map;

    public SnelstePadAlgoritmeImpl() {
        this.map = new HashMap<>();
    }
    
    @Override
    public Pad bereken(Kaart kaart, Coordinaat start, Coordinaat end) {
        Terrein startNode = kaart.getTerreinOp(start);
        Terrein endNode = kaart.getTerreinOp(end);
        
        ArrayList<Terrein> open = new ArrayList<>();
        HashSet<Terrein> closed = new HashSet<>();
        
        open.add(startNode);
        
        for(int i = 0; i < kaart.getBreedte(); i++){
            for(int j = 0; j < kaart.getHoogte(); j++){
                map.put(kaart.getTerreinOp(Coordinaat.op(i, j)), new Cost());
            }
        }

        while(open.size() > 0){
            Terrein current = open.get(0);
            for(int i = 1; i < open.size(); i++){
                if(map.get(open.get(i)).getFCost() < map.get(current).getFCost() || map.get(open.get(i)).getFCost() == map.get(current).getFCost() && map.get(open.get(i)).gethCost() <  map.get(current).gethCost()){
                    current = open.get(i);
                }
            }
            
            open.remove(current);
            closed.add(current);
            
            if(current.equals(endNode)){
                
                AsciiArtDebugger debug = new AsciiArtDebugger();
                PadImpl pad = new PadImpl(track(startNode, endNode));
                
                debug.debugPad(kaart, start, pad);
                return pad;
            }
            
            for(Richting richting:current.getMogelijkeRichtingen()){
                Terrein neighbour = kaart.kijk(current, richting);
                if(!neighbour.getTerreinType().isToegankelijk() || closed.contains(neighbour))
                    continue;
                
                double newMovementCostToNeighbour = map.get(current).getgCost() + current.getCoordinaat().afstandTot(neighbour.getCoordinaat()) + neighbour.getTerreinType().getBewegingspunten();
                
                if(newMovementCostToNeighbour < map.get(neighbour).getgCost() || !open.contains(neighbour)){
                    Cost neighbourCost = map.get(neighbour);
                    neighbourCost.setgCost(newMovementCostToNeighbour);
                    neighbourCost.sethCost(neighbour.getCoordinaat().afstandTot(end));
                    neighbourCost.setParent(current);
                    if(!open.contains(neighbour))
                        open.add(neighbour);
                }
            }
        }
        return null;
    }
    
    private List<Terrein> track(Terrein start, Terrein end){
        List<Terrein> pad = new ArrayList<>();
        Terrein current = end;
        while(!current.equals(start)){
            pad.add(current);
            current = map.get(current).getParent();
        }
        Collections.reverse(pad);
        return pad;
    }
    
    @Override
    public String toString() {
        return "A* Pathfinding"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
