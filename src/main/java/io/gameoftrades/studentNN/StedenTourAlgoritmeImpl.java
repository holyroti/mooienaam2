/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.gameoftrades.studentNN;

import io.gameoftrades.model.algoritme.StedenTourAlgoritme;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Stad;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author mrctje
 */
public class StedenTourAlgoritmeImpl implements StedenTourAlgoritme{

    @Override
    public List<Stad> bereken(Kaart kaart, List<Stad> list) {
        long startTime = System.nanoTime();
        
        double temp = 10000;
        
        double cdRate = .3; //Default 0.003 (about 50s for westoros)
        ArrayList<Stad> stedenList = new ArrayList<Stad>(list.size());
        ArrayList<Stad> currentList = new ArrayList<Stad>(list.size());
        ArrayList<Stad> bestList = new ArrayList<Stad>(list.size());
        for(Stad stad: list){
            if(kaart.getTerreinOp(stad.getCoordinaat()).getTerreinType().isToegankelijk()){
                stedenList.add(stad);
                currentList.add(stad);
                bestList.add(stad);
            }
        }
        
        
        while(temp > 1){
            int pos = (int) (stedenList.size() * Math.random());
            int pos2 = (int) (stedenList.size() * Math.random());
            
            Stad city1 = stedenList.get(pos);
            Stad city2 = stedenList.get(pos2);
            
            stedenList.set(pos2, city1);
            stedenList.set(pos, city2);
            
            int current = getDistance(currentList, kaart);
            int newDistance = getDistance(stedenList, kaart);
            
            if(acceptanceProbability(current, newDistance, temp) > Math.random()){
                currentList = new ArrayList<>(stedenList);
            }
            
            if (getDistance(currentList, kaart) < getDistance(bestList, kaart)) {
                bestList = new ArrayList<>(currentList);
            }
            
            temp *= 1-cdRate;
        }
//        AsciiArtDebugger debug = new AsciiArtDebugger();
//        debug.debugSteden(kaart, stedenList);
//        debug.debugSteden(kaart, bestList);
        long endTime = System.nanoTime();
        System.out.println("Elapsed time: " + ((endTime-startTime) / 1000000) + "ms");
        System.out.println("First solution: " + getDistance(stedenList, kaart));
        System.out.println("Best solution: " + getDistance(bestList, kaart));
        return bestList;
    }

    @Override
    public String toString() {
        return "Travelling salesman problem";
    }
    
    private int getDistance(List<Stad> list, Kaart kaart){
        SnelstePadAlgoritmeImpl padAlg = new SnelstePadAlgoritmeImpl();
        int totalTime = 0;
        for(int i = 0; i < list.size() - 1; i++){
                totalTime += padAlg.bereken(kaart, list.get(i).getCoordinaat(), list.get(i+1).getCoordinaat()).getTotaleTijd();
        }
        
        
        return totalTime;
    }
    
    private double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

}
