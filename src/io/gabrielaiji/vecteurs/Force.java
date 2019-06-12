package io.gabrielaiji.vecteurs;

public class Force extends Vecteur{

	public Force(double norme, double direction) {
		super(norme, direction);
	}
	
	public static Force sommeVecteurs(Vecteur v1, Vecteur v2){
		Vecteur vec = Vecteur.sommeVecteurs(v1, v2);
		
		return new Force(vec.norme, vec.direction);
	}
	
	public static Force diffVecteurs(Vecteur v1, Vecteur v2){
		Vecteur vec = Vecteur.sommeVecteurs(v1, v2);
		
		return new Force(vec.norme, vec.direction);
	}
	
	public static double normalisation(double h){
		return 7./(478.*Math.PI*h*h);
	}
	
	/**
	public static double smoothingKernelNabla1(double distance, double h){
		return (normalisation(h)*2*distance/(h*h))* Math.exp(-(distance*distance)/(h*h))*(1./2.)*Math.pow(10, -3);
	}**/
	
	public static double smoothingKernelNabla1(double distance, double h){
		return (4*9*distance*distance)/(Math.PI*h*h*h*h*h*h*h*h)*(h*h*-distance*distance*distance)*(h*h*-distance*distance*distance);
	}
	
	public static double smoothingKernelNabla2(double distance, double h){
		return (2+4*(distance*distance)*normalisation(h)/(h*h))*Math.exp(-(distance*distance)/(h*h));
	}

}
