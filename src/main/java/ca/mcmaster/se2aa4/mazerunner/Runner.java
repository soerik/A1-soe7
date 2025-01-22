package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    
    private static final Logger logger = LogManager.getLogger();
    
    private int xcoord;
    private int ycoord;
    private int directionmultiple;

    public Runner(int ycoord) {
        this.xcoord = 0;
        this.ycoord = ycoord;
        this.directionmultiple = 0;
    }  

    public String getDirection() {
        int directionvalue = directionmultiple % 4;
        String direction = null;
        switch (directionvalue) {
            case 0:
                direction = "EAST";
                break;
            case 1:
                direction = "SOUTH";
                break;
            case 2:
                direction = "WEST";
                break;            
            case 3:
                direction = "NORTH";
                break;
            default:
                logger.error("ERROR: could not find current direction.");
                System.exit(1);
        }
        return direction;
    }

    //changes the direction the runner is facing
    public void changeDirection(boolean isrightturn) {
        if (isrightturn == true) {
            directionmultiple++;
        } else {
            directionmultiple--;
        }
    }
}