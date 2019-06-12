package io.gabrielaiji.vecteurs;

public class Vecteur {
	protected double norme;
	protected double direction;
	
	public Vecteur(double norme, double direction){
		this.norme = norme;
		this.direction = direction;
	}
	
	//Getters
	
	public double getNorme(){
		return this.norme;
	}
	
	public double getDirection(){
		return this.direction;
	}
	
	//Setters
	
	public void setNorme(double norme){
		this.norme = norme;
	}
	
	public void setDirection(double direction){
		this.direction = direction%(2*Math.PI);
	}
	
	public void addAngle(double angle){
		this.direction = (this.direction + angle)%(2*Math.PI);
	}
	
	//Static
	
	public static Vecteur sommeVecteurs(Vecteur v1, Vecteur v2){
		double norme1 = v1.norme, norme2 = v2.norme;
		double dir1 = v1.direction, dir2 = v2.direction;
		
		double x = norme1 *Math.cos(dir1) + norme2 *Math.cos(dir2);
		double y = norme1 *Math.sin(dir1) + norme2 *Math.sin(dir2);
		double norme = Math.sqrt(x*x+y*y);
		double direction;
		if(x==0.){
			direction = Math.PI/2;
			if(y<0){
				direction = (3*Math.PI)/2;
			}
		}
		else{
			direction = Math.atan(y/x);
			if(x < 0){
				direction += Math.PI;
			}
		}
		
		return new Vecteur(norme, direction);	
	}
	
	public static Vecteur diffVecteurs(Vecteur v1, Vecteur v2){
		double norme1 = v1.norme, norme2 = v2.norme;
		double dir1 = v1.direction, dir2 = (v2.direction + Math.PI)%(2*Math.PI);
		
		double x = norme1 *Math.cos(dir1) + norme2 *Math.cos(dir2);
		double y = norme1 *Math.sin(dir1) + norme2 *Math.sin(dir2);
		double norme = Math.sqrt(x*x+y*y);
		double direction;
		if(x==0.){
			direction = Math.PI/2;
			if(y<0){
				direction = (3*Math.PI)/2;
			}
		}
		else{
			direction = Math.atan(y/x);
			if(x < 0){
				direction += Math.PI;
			}
		}
		
		return new Vecteur(norme, direction);
	}
	
}
