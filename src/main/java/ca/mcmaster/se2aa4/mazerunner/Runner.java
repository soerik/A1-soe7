package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    
    private static final Logger logger = LogManager.getLogger();
    

    private int xcoord;
    private int ycoord;
    private Direction direction;

    //left to right runner
    public Runner(int ycoord) {
        this.xcoord = 0;
        this.ycoord = ycoord;
        this.direction = Direction.EAST;
    }  

    //right to left runner
    public Runner(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = Direction.WEST;
    }

    public int getXCoord() {
        return xcoord;
    }

    public int getYCoord() {
        return ycoord;
    }

    public Direction getDirection() {
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


    //changes the direction the runner is facing
    public void changeDirection(boolean isturningright) {
        logger.trace("changing direction...");
        if (direction == Direction.EAST) {
            if (isturningright == true) {
                direction = Direction.SOUTH;
            } else {
                direction = Direction.NORTH;
            }
        } else if (direction == Direction.SOUTH) {
            if (isturningright == true) {
                direction = Direction.WEST;
            } else {
                direction = Direction.EAST;
            }
        } else if (direction == Direction.WEST) {
            if (isturningright == true) {
                direction = Direction.NORTH;
            } else {
                direction = Direction.SOUTH;
            }
        } else if (direction == Direction.NORTH) {
            if (isturningright == true) {
                direction = Direction.EAST;
            } else {
                direction = Direction.WEST;
            }
        } else {
            logger.error("ERROR: direction variable does not point to direction.");
            System.exit(1);
        }
        logger.info("direction changed. Now facing {}", direction);
    }


    public void moveForward() {
        switch (direction) {
            case EAST:
                xcoord++;
                break;
            case NORTH:
                ycoord--;
                break;
            case WEST:
                xcoord--;
                break;
            case SOUTH:
                ycoord++;
                break;
        }
    }
}