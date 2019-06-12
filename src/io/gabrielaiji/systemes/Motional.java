package io.gabrielaiji.systemes;

import java.util.HashSet;
import java.util.Iterator;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.vecteurs.Force;
import io.gabrielaiji.vecteurs.Vecteur;

public class Motional extends Particule{
	protected HashSet<Force> forces = new HashSet<Force>();
	private Position2D pos;
	private Force forceTotal;
	private Vecteur acceleration;
	private final double g = 9.8;
	
	public Motional(Position2D pos, double masse, double masseV){
		this.pos = pos;
		this.masse = masse;
		this.masseV = masseV;
		
		this.forces.add(new Force(this.masse * g, (3./2.)*Math.PI));
		this.acceleration = new Vecteur(0, 0);
		this.vitesse = new Vecteur(0, 0);
	}
	
	public void addForce(Force force){
		this.forces.add(force);
	}
	
	public void resetForce(){
		this.forces = new HashSet<Force>();
		this.forces.add(new Force(this.masse * g, (3./2.)*Math.PI));
		//this.forces.add(new Force(this.masse * g, (1./2.)*Math.PI));
	}
	
	public void calculAcc(){
		
		Iterator<Force> it = this.forces.iterator();
		Force inter = new Force(0, 0);
		int i = 0;
		
		if(it.hasNext()){
			inter = it.next();
			while(inter.getNorme() == 0. && it.hasNext()){
				inter = it.next();
			}
		}
		while(it.hasNext() && inter.getNorme()!=0.){
			i ++;
			Force force = it.next();
			if(force.getNorme()==0.0){
				inter = Force.sommeVecteurs(inter, force);
				//System.out.println("Force "+i+"ieme : norme : "+inter.getNorme());
			}
		}
		this.forceTotal = inter;
		//System.out.println("Norme inter : " + inter.getNorme());
		
		double norme = inter.getNorme()/this.getMasse();
		this.acceleration = new Vecteur(norme, inter.getDirection());
	}
	
	public void calculNextStep(double dT){
		//System.out.println("calcul nouveau pos : ");
		this.calculAcc();
		Vecteur acc = new Vecteur(this.acceleration.getNorme()*dT, this.acceleration.getDirection());
		//System.out.println("Norme Acc : " + acc.getNorme());
		this.vitesse = Vecteur.sommeVecteurs(this.vitesse, acc);
		
		double vitNorme = this.vitesse.getNorme();
		//System.out.println("Norme Vitesse : " + vitNorme);
		double direction = this.vitesse.getDirection();
		double vitX = vitNorme*Math.cos(direction);
		double vitY = vitNorme*Math.sin(direction);
		
		Position2D pos = this.getPos();
		double x = pos.getX();
		double y = pos.getY();
		
		x = x + vitX*dT;
		y = y + vitY*dT;
		
		/**
		System.out.println();
		System.out.println("vitX*dT : " + vitX*dT);
		System.out.println("vitY*dT : " + vitY*dT);
		System.out.println();
		System.out.println();**/
		
		this.setPos(new Position2D(x, y));
	}
	
	public boolean isMovable(){
		return true;
	}
	
	//Getters
	
	public Force getForceTotal(){
		return this.forceTotal;
	}
	
	public Position2D getPos(){
		return this.pos;
	}
	
	public Vecteur getAcc(){
		return this.acceleration;
	}
	
	public HashSet<Force> getForces(){
		return this.forces;
	}
	
	//Setters
	
	public void setPos(Position2D pos){
		this.pos = pos;
	}
	
	public void setAcc(Vecteur vecteur){
		this.acceleration = vecteur;
	}
	
	public void setVit(Vecteur vecteur){
		this.vitesse = vecteur;
	}

}
