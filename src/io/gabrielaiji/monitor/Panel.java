package io.gabrielaiji.monitor;
import javax.swing.JPanel;

import io.gabrielaiji.espaces.Position2D;
import io.gabrielaiji.espaces.Tableau2D;
import io.gabrielaiji.systemes.MotionLess;
import io.gabrielaiji.systemes.Motional;
import io.gabrielaiji.systemes.Particule;
import io.gabrielaiji.vecteurs.Force;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Panel extends JPanel{
	public HashSet<Particule> particules = new HashSet<Particule>();
	public Tableau2D tableau;
	private final double Amplificator;
	private final int TailleParticule;
	//TEST
	public HashSet<Motional> motionals = new HashSet<Motional>();
	
	public Panel(double amplificator, int tailleParticule){
		super();
		
		this.Amplificator = amplificator;
		this.TailleParticule = tailleParticule;
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,  0, this.getWidth(), this.getHeight());
		
		
		Iterator<Particule> it = this.particules.iterator();
		while(it.hasNext()){
			Particule part = it.next();
			
			if(!part.isMovable())
				g.setColor(Color.black);
			else if(part.isMovable())
				g.setColor(Color.blue);
			
			Position2D pos = part.getPos();
			
			int x = (int) (this.Amplificator * pos.getX());
			int y = (int) (-this.Amplificator * pos.getY());
			g.fillOval(x, y, this.TailleParticule, this.TailleParticule);
		}
		
		this.showForces(g);
		
	}
	
	public void setTableau(Tableau2D tableau){
		this.tableau = tableau;
	}
	
	public void refreshParticulesInventory(){
		Iterator<MotionLess> it1 = this.tableau.wall.particules.iterator();
		while(it1.hasNext()){
			this.particules.add(it1.next());
		}
		
		Iterator<Motional> it2 = this.tableau.water.particules.iterator();
		while(it2.hasNext()){
			this.particules.add(it2.next());
		}
	}
	
	public void setMotional(HashSet<Motional> motionals){
		this.motionals = motionals;
	}
	
	public void showForces(Graphics g){
		Iterator<Motional> it = this.motionals.iterator();
		while(it.hasNext()){
			Motional part = it.next();
			Position2D pos = part.getPos();
			
			int X = (int) (this.Amplificator * pos.getX()+3);
			int Y = (int) (-(this.Amplificator * pos.getY()-3));
			
			Iterator<Force> itForce = part.getForces().iterator();
			while(itForce.hasNext()){
				Force force = itForce.next();
				
				double norme = force.getNorme();
				//System.out.println(norme);
				double direction = force.getDirection();
				//System.out.println(direction);
				
				int x =(int) (Math.pow(10, 8) *norme*Math.cos(direction));
				int y =(int) (-Math.pow(10, 8) *norme*Math.sin(direction));
				//System.out.println(x);
				//System.out.println(y);
				
				g.setColor(Color.red);
				g.drawLine(X, Y, X+x, Y+y);
			}
			g.setColor(Color.GREEN);
			Force force = part.getForceTotal();
			double norme = force.getNorme();
			double direction = force.getDirection();
			int x =(int) (Math.pow(10, 8) *norme*Math.cos(direction));
			int y =(int) (-Math.pow(10, 8) *norme*Math.sin(direction));
			g.drawLine(X, Y, X+x, Y+y);
			
		}
	}
	
}
