package io.gabrielaiji.espaces;

import java.util.HashSet;
import java.util.Iterator;

import io.gabrielaiji.systemes.Particule;

public class Carre {
	private HashSet<Particule> particules = new HashSet<Particule>();
	
	
	public Carre(){
		
	}
	
	public void addParticule(Particule particule){
		this.particules.add(particule);
	}
	
	public void removeParticule(Particule particule){
		particules.remove(particule);
	}
	
	public HashSet<Particule> getWithinRangeParticules(Position2D pos, double h){
		HashSet<Particule>  lst = new HashSet<Particule>();
		
		Iterator<Particule> it = this.particules.iterator();
		while(it.hasNext()){
			Particule part = it.next();
			if(Position2D.calculDistance(part.getPos(), pos) <= h){
				lst.add(part);
			}
		}
		
		return lst;
	}

}
