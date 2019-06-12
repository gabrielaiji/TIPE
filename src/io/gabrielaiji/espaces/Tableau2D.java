package io.gabrielaiji.espaces;

import java.util.Iterator;

import io.gabrielaiji.systemes.MotionLess;
import io.gabrielaiji.systemes.Motional;
import io.gabrielaiji.systemes.Particule;
import io.gabrielaiji.systemes.SystemeFermeNonStatic;
import io.gabrielaiji.systemes.SystemeFermeStatic;
import io.gabrielaiji.vecteurs.Pressure;
import io.gabrielaiji.vecteurs.Viscosity;

public class Tableau2D {
	public Carre[][] map = {};
	private final double SpaceUnit;
	public final double h;
	public final double dT;
	public final int TailleMap;
	public final Position2D Origine;
	public final SystemeFermeNonStatic water = new SystemeFermeNonStatic();
	public SystemeFermeStatic wall = new SystemeFermeStatic();
	
	public Tableau2D(int tailleMap, double h, double dT){
		this.TailleMap = tailleMap;
		this.h = h;
		this.SpaceUnit = h/6.;
		this.dT = dT;
		
		this.map = new Carre[tailleMap][tailleMap];
		
		for(int i = 0; i < tailleMap; i++){
			for(int j = 0; j < tailleMap; j++){
				map[i][j] = new Carre();
			}
		}
		
		int xO;
		if(tailleMap%2==1){
			xO = tailleMap/2;
		}else{
			xO = (tailleMap/2)-1;
		}
		int yO = tailleMap/2;

		this.Origine = new Position2D(xO, yO);
	}
	
	
	public void createDefaultWall(){
		
		double masseV = 1000;
		double masse = 1.*Math.pow(10, -5);
		for(int i = 0; i <5; i++){
			for(int j = 0; j<50; j++){
				double x = (10 + i)*this.SpaceUnit;
				double y = (10+j)*this.SpaceUnit;
				
				int carreX = (int) (x/h);
				int carreY = (int) (y/h);
				
				MotionLess part = new MotionLess(new Position2D(x, -y), masse, masseV);
				
				this.map[carreY][carreX].addParticule(part);
				this.wall.addParticules(part);
			}
		}
		
		for(int i = 0; i <75; i++){
			for(int j = 0; j<5; j++){
				double x = (10+ 5 +i)*this.SpaceUnit;
				double y = (10+ 45 +j)*this.SpaceUnit;
				
				int carreX = (int) (x/h);
				int carreY = (int) (y/h);
				
				MotionLess part = new MotionLess(new Position2D(x, -y), masse, masseV);
				
				this.map[carreY][carreX].addParticule(part);
				this.wall.addParticules(part);
			}
		}
		
		for(int i = 0; i <5; i++){
			for(int j = 0; j<50; j++){
				double x = (10+ 75 +i)*this.SpaceUnit;
				double y = (10+ j)*this.SpaceUnit;
				
				int carreX = (int) (x/h);
				int carreY = (int) (y/h);
				
				MotionLess part = new MotionLess(new Position2D(x, -y), masse, masseV);
				
				this.map[carreY][carreX].addParticule(part);
				this.wall.addParticules(part);
			}
		}
		
		
	}
	
	public void createDefaultWaterTest(){
		double masseV = 1000;
		double masse = 1.*Math.pow(10, -5);
		
		for(int i = 44; i <45; i++){
			for(int j = 20; j<21; j++){
				double x = (15 + i)*this.SpaceUnit;
				double y = (10+j)*this.SpaceUnit;
				
				int carreX = (int) (x/h);
				int carreY = (int) (y/h);
				
				Motional part = new Motional(new Position2D(x, -y), masse, masseV);
				
				this.map[carreY][carreX].addParticule(part);
				this.water.addParticules(part);
			}
		}
	}
	
	public void calculateNextPositions(){
		Iterator<Motional> it = this.water.particules.iterator();
		while(it.hasNext()){
			Motional par = it.next();
			par.resetForce();
			
			Position2D pos = par.getPos();
			
			int carreX = (int) (pos.getX()/h);
			int carreY = (int) (-pos.getY()/h);
			this.map[carreY][carreX].removeParticule(par);
			
			for(int i=-1; i<2; i++){
				int carreXInter = carreX+i;
				if(carreXInter>=0 && carreXInter<this.TailleMap){
					
					for(int j=-1; j<2; j++){
						int carreYInter = carreY+j;
						if(carreYInter>=0 && carreYInter<this.TailleMap){
							Iterator<Particule> itPar2 = this.map[carreYInter][carreXInter].getWithinRangeParticules(pos, this.h).iterator();
							while(itPar2.hasNext()){
								
								Particule par2 = itPar2.next();
								
								Pressure pressure = new Pressure(par, par2, this.h);
								par.addForce(pressure);
								if(par2.isMovable()){
									Viscosity viscosity = new Viscosity(par, par2, this.h);
									par.addForce(viscosity);
								}
								
								/**
								System.out.println("Forces*****");
								System.out.println(pressure.getNorme());
								System.out.println(viscosity.getNorme());
								System.out.println();
								System.out.println();**/
								
							}
							
						}
					}
				}
				
			}
			par.calculNextStep(this.dT);
			//System.out.println("ForceTot :");
			//System.out.println(par.getForceTotal().getNorme());
			int newCarreX = (int) (pos.getX()/h);
			int newCarreY = (int) (-pos.getY()/h);
			this.map[newCarreY][newCarreX].addParticule(par);
			
			/**
			System.out.println("Pos*****");
			System.out.println(14*Math.pow(10, 3) * par.getPos().getX());
			System.out.println(14*Math.pow(10, 3) * par.getPos().getY());
			System.out.println();
			System.out.println();**/
		}
	}
	
	

}
