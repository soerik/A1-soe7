package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    
    private static final Logger logger = LogManager.getLogger();
    
    private int xcoord;
    private int ycoord;
    private int directionmultiple;
    private String direction;


    public Runner(int ycoord) {
        this.xcoord = 0;
        this.ycoord = ycoord;
        this.directionmultiple = 0;
        this.direction = "EAST";
    }  

    public int getXCoord() {
        return xcoord;
    }

    public int getYCoord() {
        return ycoord;
    }

    public String getDirection() {
        return direction;
    }

    //modulus operator (needed instead of remainder)
    public int mod(int x, int y) {
    int result = x % y;
    if (result < 0) {
        result += y;
    }
    return result;
    }

    public void setDirection() {
        int directionvalue = mod(directionmultiple, 4);
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
                logger.error("ERROR: could not find current direction. directionmultiple: {} directionvalue: {}", directionmultiple, directionvalue);
                System.exit(1);
        }
    }

    //changes the direction the runner is facing
    public void changeDirection(String direction) {
        logger.trace("changing direction...");
        if (direction == "RIGHT") {
            directionmultiple++;
        } else if (direction == "LEFT") {
            directionmultiple--;
        } else {
            logger.error("ERROR: changeDirection() only intakes \"RIGHT\" or \"LEFT\"");
            System.exit(1);
        }
        setDirection();
        logger.info("direction changed");
    }


    public void moveForward() {
        switch (direction) {
            case "EAST":
                xcoord++;
                break;
            case "NORTH":
                ycoord--;
                break;
            case "WEST":
                xcoord--;
                break;
            case "SOUTH":
                ycoord++;
                break;
        }
    }
}