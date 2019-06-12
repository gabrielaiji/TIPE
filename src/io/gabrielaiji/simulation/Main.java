package io.gabrielaiji.simulation;

import io.gabrielaiji.espaces.Tableau2D;
import io.gabrielaiji.monitor.Window;

public class Main {
	public static void main(String[] args){
		
		Tableau2D simulation = new Tableau2D(750, 3.09*Math.pow(10, -3), 5.*Math.pow(10, -5));
		simulation.createDefaultWall();
		simulation.createDefaultWaterTest();
		
		
		/**
		for(int i=0; i<50; i++){
			System.out.println("-------------------------------------");
			System.out.println("calcul..." + i);
			simulation.calculateNextPositions();
		}**/
		Window window = new Window("test", 1000, 750);
		window.panel.setTableau(simulation);
		window.panel.refreshParticulesInventory();
		window.panel.setMotional(simulation.water.particules);
		window.animate();
		window.display();
		
	}
}
