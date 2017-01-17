import java.awt.Point;
import java.util.ArrayList;

public class Grid {
	private int dimx;
	private int dimy;
	private ArrayList<Point> scents;
	
	//Represents a specific grid, list of scents initialised
	public Grid(int x, int y){
		dimx = x;
		dimy = y;
		scents = new ArrayList<Point>();
	}
	
	public ArrayList<Point> getScents(){
		return this.scents;
	}
	
	public int getX(){
		return this.dimx;
	}
	
	public int getY(){
		return this.dimy;
	}
	
	//Check if grid is within the allowed dimensions
	public boolean validGrid(){
		if (dimx>50||dimx<0||dimy>50||dimy<0){
			return false;
		} else {
			return true;
		}
	}
}
