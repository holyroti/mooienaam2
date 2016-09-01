package io.gameoftrades.studentNN;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.kaart.Terrein;
import io.gameoftrades.model.kaart.TerreinType;
import io.gameoftrades.model.lader.WereldLader;
import io.gameoftrades.model.markt.Handel;
import io.gameoftrades.model.markt.HandelType;
import io.gameoftrades.model.markt.Handelswaar;
import io.gameoftrades.model.markt.Markt;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WereldLaderImpl implements WereldLader {

    @Override
    public Wereld laad(String resource) {
        FileInputStream fis = null;
        Kaart kaart = null;
        List<Stad> stedenList = new ArrayList<>();
        List<Handel> handelList = new ArrayList<>();
        try {
            //
            // Gebruik this.getClass().getResourceAsStream(resource) om een resource van het classpath te lezen.
            //
            // Kijk in src/test/resources voor voorbeeld kaarten.
            //
            // TODO Laad de wereld!
            //
            fis = new FileInputStream(resource);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WereldLaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
        try {
            String s;
            int count = 0;
            int hoogte = 0, breedte = 0;
            
            String[] terreinen;
            String[] steden = null;
            String[] handels;
            
            int aantalSteden = 0;
            int aantalHandels = 0;;
            while((s = br.readLine()) != null){
                if(count == 0){
                   String[] dimensie = s.split(",");
                   breedte = Integer.parseInt(dimensie[0]);
                   hoogte = Integer.parseInt(dimensie[1]);
                   kaart = new Kaart(breedte, hoogte);
                   count++;
                   continue;
                }
                if(count < hoogte+1){
                    terreinen = s.split("");
                    for(int i = 0; i < terreinen.length; i++){
                        Coordinaat coord = Coordinaat.op(i, count-1);
                        Terrein terrein = new Terrein(kaart, coord, TerreinType.fromLetter(terreinen[i].charAt(0)));
                    }
                }
                if(count > hoogte){
                    if(count == hoogte+1){
                       // System.out.println(s);
                        aantalSteden = Integer.parseInt(s);
                       // System.out.println("HELLOOOOO"+aantalSteden);
                        count++;
                        continue;
                    }
                    if(count < hoogte+2+aantalSteden){
                        steden = s.split(",");
                       // System.out.println(steden[2]);
                        
                            Coordinaat coord = Coordinaat.op(Integer.parseInt(steden[0]), Integer.parseInt(steden[1]));
                            Stad stad = new Stad(coord, steden[2]);
                            stedenList.add(stad);
                        
                    }
                    if(count > hoogte+1+aantalSteden){
                        if(count == hoogte+2+aantalSteden){
                            aantalHandels = Integer.parseInt(s);
                            count++;
                            continue;
                        }
                        
                        handels = s.split(",");
                        for(Stad stad: stedenList){
                            if(handels[0].equals(stad.getNaam())){
                                handelList.add(new Handel(stad, HandelType.valueOf(handels[1]), new Handelswaar(handels[2]), Integer.parseInt(handels[3])));
                            }
                        }
                    }
                }
                count++;
            }  
            
            System.out.println(sb);
        } catch (IOException ex) {
            Logger.getLogger(WereldLaderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Wereld(kaart, stedenList, new Markt(handelList));
    }

}
