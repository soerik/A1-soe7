package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    
    private static final Logger logger = LogManager.getLogger();
    

    private int xcoord;
    private int ycoord;
    private Direction direction;

    public Runner(int xcoord, int ycoord, Direction direction) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = direction;
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