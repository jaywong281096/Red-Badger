import java.awt.Point;

public class Controller {
	
	Grid grid;
	
	//Facilitates the interactions between grid and robot
	public Controller (String[] inputList){
		
		setGrid(inputList[0]); //Initialises grid
		
		if (!grid.validGrid()||inputList.length%2==0||inputList.length<=1){ //Checks whether grid is valid and whether number of strings in argument is valid
			System.out.println("Invalid input"); //An argument must have an odd number of strings and >1 to be valid
			System.exit(0);	//System.exit used as a quick solution. May be better alternatives
		}
		
		System.out.println("START");
		for (int i = 1; i<inputList.length; i++){ //Start at 1 as that is where the position and instructions begin
			if (i%2 !=0){ //At the index for each Robot position (The robots position is always at an odd index in the argument arraylist)
				Robot robot = createRobot(inputList[i]); 
				if (!outBounds(robot)){ //Make sure robot isn't initialised out of bounds
					doCommand(inputList[i+1],robot); //The next index from the robot position contains the robots instructions
					if (robot.isLost()){
						System.out.println(robot.getX()+""+robot.getY()+""+robot.getDir()+" LOST");
					} else {
						System.out.println(robot.getX()+""+robot.getY()+""+robot.getDir());
					}
				} else {
					System.out.println("Robot position OUT OF BOUNDS"); //If robot is initialised as out of bounds then output
				}
			} 
		}
		System.out.println("END");
	}
	
	public Robot createRobot(String positionStr){ //Takes into account coordinates from 0-50, there are better ways that may be more extensible
		if (positionStr.length()==5){
			Robot robot = new Robot(Character.getNumericValue(positionStr.charAt(0))*10+Character.getNumericValue(positionStr.charAt(1)),
									Character.getNumericValue(positionStr.charAt(2))*10+Character.getNumericValue(positionStr.charAt(3)),
									positionStr.charAt(4));
			return robot;
		} else {
			Robot robot = new Robot(Character.getNumericValue(positionStr.charAt(0)),Character.getNumericValue(positionStr.charAt(1)),positionStr.charAt(2));
			return robot;
		}
		
	}
	
	public void setGrid(String gridStr){ //Same as previous, takes into account double digit coordinates
		if (gridStr.length()==2){
			grid = new Grid(Character.getNumericValue(gridStr.charAt(0)), Character.getNumericValue(gridStr.charAt(1)));
		} else {
			grid = new Grid(Character.getNumericValue(gridStr.charAt(0))*10 + Character.getNumericValue(gridStr.charAt(1)), 
							Character.getNumericValue(gridStr.charAt(2))*10 + Character.getNumericValue(gridStr.charAt(3)));
		}
	}
	
	public void doCommand(String commandList, Robot robot){ //Parses through a string of commands for a specific robot
		for (int i = 0; i<commandList.length(); ++i){
			if (grid.getScents().isEmpty()){ //If scents is empty, used for a more efficient issuing of commands in case that no robots become lost
				robot.robotMove(commandList.charAt(i));
				if (outBounds(robot)){
					robot.moveBackward();
					grid.getScents().add(new Point(robot.getX(),robot.getY())); 
					robot.becomeLost(); //Set a robot as lost
					break;
				}
			} else { 
				if (grid.getScents().contains(new Point(robot.getX(),robot.getY()))){ //If robot is at a position with a scent
					robot.robotMove(commandList.charAt(i)); 
					if (outBounds(robot)){ //Check if robot has moved out of bounds
						robot.moveBackward(); //if so just revert the movement, essentially ignoring the command
					}
				} else { //Proceed normally. If/else's used so that a robot does not place multiple scents at the same position
					robot.robotMove(commandList.charAt(i));
					if (outBounds(robot)){
						robot.moveBackward();
						grid.getScents().add(new Point(robot.getX(),robot.getY())); 
						robot.becomeLost();
						break;
					}
				}
			}
		}
	}
	
	public boolean outBounds(Robot robot){ //Check if robot is out of bounds
		if ((robot.getX() > grid.getX())||(robot.getX() < 0)||(robot.getY() > grid.getY())||(robot.getY() < 0)) {
			return true;
		} else {
			return false;
		}
	}
}
