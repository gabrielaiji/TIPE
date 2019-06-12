package io.gabrielaiji.vecteurs;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.systemes.Particule;

public class Pressure extends Force{

	public Pressure(Particule par1, Particule par2, double h) {
		super(0, 0);
		
		Position2D pos1 = par1.getPos();
		Position2D pos2 = par2.getPos();
		double distance = Position2D.calculDistance(pos1, pos2);
		
		if(!(distance==0)){			
			double direction = Position2D.calculDirection(pos2, pos1);
			double masse2 = par2.getMasse();
			double masseV2 = par2.getMasseV();
			double kernel = Force.smoothingKernelNabla1(distance, h);
			//double coeff = (masse2 * (masseV1+masseV2)/(masseV2*2));
			double coeff = (masse2/masseV2)*(1500./7.)*(Math.pow(par1.getMasseV()/1, 7)-1);
			
			double norme = coeff*kernel;
			/**
			System.out.println("norme : " + norme);
			System.out.println("direction : " + direction);
			System.out.println("kernel : "+kernel);
			System.out.println("coeff : "+coeff);**/
			this.setDirection(direction);
			this.setNorme(norme);
		}
		
	}

}
