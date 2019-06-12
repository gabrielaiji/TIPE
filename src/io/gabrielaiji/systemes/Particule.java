package io.gabrielaiji.systemes;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.vecteurs.Vecteur;

public abstract class Particule {
	protected Position2D pos;
	protected double masse;
	protected double masseV;
	protected Vecteur vitesse;
	
	public abstract Position2D getPos();
	
	public abstract boolean isMovable();
	
	//Getters
	
	public  Vecteur getVit(){
		return this.vitesse;
	}
	
	public double getMasse(){
		return this.masse;
	}
	
	public double getMasseV(){
		return this.masseV;
	}

}
