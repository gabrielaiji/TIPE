package io.gabrielaiji.vecteurs;

public class Arrow extends Force{
	public double departX, departY;
	public double finX, finY;

	public Arrow(double norme, double direction, double departX, double departY) {
		super(norme, direction);
		this.departX = departX;
		this.departY = departY;
		
		this.calculFin();	
	}

	public void calculFin(){
		this.finX = this.departX + this.norme * Math.cos(this.direction);
		this.finY = this.departY - this.norme * Math.sin(this.direction);
	}
	
	

}
