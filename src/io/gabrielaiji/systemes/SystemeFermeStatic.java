package io.gabrielaiji.systemes;

import java.util.HashSet;

public class SystemeFermeStatic extends SystemeFerme{
	
public HashSet<MotionLess> particules = new HashSet<MotionLess>();
	
	public SystemeFermeStatic(){
		
	}
	
	public void addParticules(MotionLess particule){
		this.particules.add(particule);
	}

}