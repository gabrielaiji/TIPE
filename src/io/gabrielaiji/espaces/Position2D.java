package io.gabrielaiji.espaces;


public class Position2D {
	private double x, y;
	
	public Position2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//Getters
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	//Setters
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	//Static
	
	public static double calculDistance(Position2D pos1, Position2D pos2){
		double x = pos1.getX() - pos2.getX();
		double y = pos1.getY() - pos2.getY();
		
		return Math.sqrt(x*x+y*y);
	}
	
	public static double calculDistanceCarre(Position2D pos1, Position2D pos2){
		double x = pos1.getX() - pos2.getX();
		double y = pos1.getY() - pos2.getY();
		
		return x*x+y*y;
	}
	
	public static double calculDirection(Position2D pos1, Position2D pos2){
		double x = pos2.getX() - pos1.getX();
		double y = pos2.getY() - pos1.getY();
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
		
		return direction;
	}
}
