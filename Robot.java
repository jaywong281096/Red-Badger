
public class Robot {
	
	private int coordx;
	private int coordy;
	private char direction;
	private boolean lost;

	//Represents a specific robot
	public Robot(int x, int y, char dir){
		coordx = x;
		coordy = y;
		direction = dir;
	}
	
	public int getX(){
		return this.coordx;
	}
	
	public int getY(){
		return this.coordy;
	}
	
	public char getDir(){
		return this.direction;
	}
	
	public void becomeLost(){ //Setter for lost
		this.lost = true;
	}
	
	public boolean isLost(){ //Getter for lost
		if (lost){
			return true;
		} else {
			return false;
		}
	}
	
	//Following methods are used to carry out the commands LRF, further commands may be added in a similar fashion
	//I had considered using a command pattern however for the sake of simplicity and lack of time I went for a more common solution
	//Also considered using enumerations to form a kind of circular list containing NESW with L having an offset of -1 and R +1 for a more 
	//compact and efficient solution
	public void turnLeft() {
		switch (this.direction) {
			case 'N': direction = 'W'; break;
		    case 'W': direction = 'S'; break;
		    case 'S': direction = 'E'; break;
		    case 'E': direction = 'N'; break;
		}
	}
	
	public void turnRight() {
		switch (this.direction) {
			case 'N': direction = 'E'; break;
		    case 'W': direction = 'N'; break;
		    case 'S': direction = 'W'; break;
		    case 'E': direction = 'S'; break;
		}
	}
	
	public void moveForward() {
		switch (this.direction) {
			case 'N': coordy = coordy+1; break;
		    case 'W': coordx = coordx-1; break;
		    case 'S': coordy = coordy-1; break;
		    case 'E': coordx = coordx+1; break;
		}
	}
	
	//As mentioned before new commands can be added like this moveBackwards method.
	//This is used in cases where a robot has fallen and I want to return it to its prior position
	public void moveBackward() {
		switch (this.direction) {
			case 'N': coordy = coordy-1; break;
		    case 'W': coordx = coordx+1; break;
		    case 'S': coordy = coordy+1; break;
		    case 'E': coordx = coordx-1; break;
		}
	}
	
	//Method for how a robot should react to any particular command. Extra cases can be added for future commands
	//Default case for when an invalid command is input
	public void robotMove(char command){
		switch(command) {
			case 'L' :
				turnLeft();
				break;
			case 'R' :
				turnRight();
				break;
			case 'F' :
				moveForward();
				break;
			default :
				System.out.println("Invalid command");
		}
	}
}
