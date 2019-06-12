package io.gabrielaiji.vecteurs;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.systemes.Particule;

public class Viscosity extends Force{

	public Viscosity(Particule par1, Particule par2, double h) {
		super(0, 0);
		
		Position2D pos1 = par1.getPos();
		Position2D pos2 = par2.getPos();
		
		double distance = Position2D.calculDistance(pos1, pos2);
		
		if(!(distance==0)){
			double masse2 = par2.getMasse();
			double masseV2 = par2.getMasseV();
			Vecteur vit = Vecteur.diffVecteurs(par1.getVit(), par2.getVit());
			
			double norme = (masse2/masseV2)*vit.getNorme()*Force.smoothingKernelNabla2(distance, h);
			
			this.setNorme(norme);
			this.setDirection(vit.getDirection());
		}
		
	}

}
