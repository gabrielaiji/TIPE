package io.gabrielaiji.systemes;

import java.util.HashSet;


public abstract class SystemeFerme{
	public HashSet<Particule> particules = new HashSet<Particule>();
	
	public SystemeFerme(){
		
	}
	
	public void addParticules(Particule particule){
		this.particules.add(particule);
	}
}
