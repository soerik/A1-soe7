package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathUtils {

    private static final Logger logger = LogManager.getLogger();

    protected Maze maze;
    protected Runner runner;

    public PathUtils(Maze maze, Runner runner) {
         this.maze = maze;
         this.runner = runner;
    }

    public boolean isAtExit() {
        logger.trace("checking if runner is at exit...");
        if (runner.getXCoord() == maze.getXLen() - 1 && runner.getYCoord() == maze.getEndY()) {
            logger.trace("Runner is at exit!");
            return true;
        } else {
            logger.trace("Runner is not at exit.");
            return false;
        }
    }

    public boolean canMoveForward() {
        Direction direction = runner.getDirection();
        boolean valid = false;
        switch (direction) {
            case EAST:
                if (runner.getXCoord() < maze.getXLen() - 1) {
                    valid = maze.isPassable(runner.getXCoord() + 1, runner.getYCoord());
                }
                break;
            case NORTH:
                if (runner.getYCoord() > 0) {
                    valid = maze.isPassable(runner.getXCoord(), runner.getYCoord() - 1);
                }
                break;
            case WEST:
                if (runner.getXCoord() > 0) {
                    valid = maze.isPassable(runner.getXCoord() - 1, runner.getYCoord());
                }
                break;
            case SOUTH:
                if (runner.getXCoord() < maze.getYLen() - 1) {
                    valid = maze.isPassable(runner.getXCoord(), runner.getYCoord() + 1);
                    break;
                }
                break;
        }

        if (valid == false) { logger.trace("cannot move forward."); }

        return valid;
    }

    //checks if it is possible to move forward, and if so, moves the runner forward in the direction they are facing
    public void moveForward() {
        if (canMoveForward()) {
            runner.moveForward();
            logger.trace("runner moved forward in direction: {}", runner.getDirection());
        } else {
            logger.trace("Cannot move forward.");
        }
    }


    //public String factorizedToCanonical(String canonicalform);

    //public String canonicalToFactorized(String factorizedform);


}