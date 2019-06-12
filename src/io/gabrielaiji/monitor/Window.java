package io.gabrielaiji.monitor;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private String title;
	private int height;
	private int width;
	private boolean visible;
	public Panel panel = new Panel(14*Math.pow(10, 3), 7);
	
	public Window(String title, int width, int height){
		System.out.println("Création d'un GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.title = title;
		this.height = height;
		this.width = width;
		this.visible = true;
		this.refresh();
		
		this.setContentPane(this.panel);
		this.setVisible((this.visible));
		
	}
	
	public void refresh(){
		this.setTitle(this.title);
		this.setSize(this.width, this.height);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible((this.visible));
	}
	
	public void display(){
		visible = true;
		this.setVisible((this.visible));
	}
	
	public void hide(){
		visible = false;
		this.setVisible((this.visible));
	}
	
	public void animate(){
		while(true){
			this.panel.tableau.calculateNextPositions();
			this.panel.repaint();
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
}

