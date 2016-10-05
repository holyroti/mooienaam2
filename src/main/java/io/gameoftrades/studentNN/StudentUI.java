package io.gameoftrades.studentNN;

import io.gameoftrades.debug.AsciiArtDebugger;
import io.gameoftrades.debug.Debugger;
import io.gameoftrades.debug.DummyDebugger;
import io.gameoftrades.ui.*;
import io.gameoftrades.studentNN.HandelaarImpl;

/**
 * Toont de visuele gebruikersinterface.
 * 
 * Let op: dit werkt alleen als je de WereldLader hebt geimplementeerd (Anders krijg je een NullPointerException).
 */
public class StudentUI {

	public static void main(String[] args) {
		MainGui.toon(new HandelaarImpl(), "westeros-kaart.txt");
                
	}
	
}
