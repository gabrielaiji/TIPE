package io.gabrielaiji.systemes;

import java.util.HashSet;

public class SystemeFermeNonStatic extends SystemeFerme{
	
public HashSet<Motional> particules = new HashSet<Motional>();
	
	public SystemeFermeNonStatic(){
		
	}
	
	public void addParticules(Motional particule){
		this.particules.add(particule);
	}

}
