package io.gabrielaiji.systemes;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.vecteurs.Vecteur;

public class MotionLess extends Particule{
	private final Position2D pos;
	
	public MotionLess(Position2D pos, double masse, double masseV){
		this.pos = pos;
		this.masse = masse;
		this.masseV = masseV;
		this.vitesse = new Vecteur(0, 0);
	}
	
	public Position2D getPos(){
		return this.pos;
	}
	
	public boolean isMovable(){
		return false;
	}
}
