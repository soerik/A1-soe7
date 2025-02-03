package ca.mcmaster.se2aa4.mazerunner;

import java.lang.StringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder extends PathUtils implements FindsPath {
    
    private static final Logger logger = LogManager.getLogger();

    public PathFinder(Maze maze, Runner runner) {
         super(maze, runner);
    }
    
    public void findPath() {
        String canonicalpath = rightHandAlgorithm(); 
        String factorizedpath = canonicalToFactorized(canonicalpath);
        System.out.println(factorizedpath);
    }


    public String rightHandAlgoMove() {
        if (isRightHandPassable()) {
            runner.changeDirection(true);
            runner.moveForward();
            return "RF";
        } else if (canMoveForward()) {
            runner.moveForward();
            return "F";
        } else {
            runner.changeDirection(false);
            return "L";
        }
    }

    public String rightHandAlgorithm() {
        StringBuilder canonicalpath = new StringBuilder();
        while (!isAtRightExit()) {
            canonicalpath.append(rightHandAlgoMove());
        }
        return canonicalpath.toString();
    }

    public boolean isRightHandPassable() {
        switch (runner.getDirection()) {
            case EAST:
                return maze.isPassable(runner.getXCoord(), runner.getYCoord()+1);
            case NORTH:
                return maze.isPassable(runner.getXCoord()+1, runner.getYCoord());
            case WEST:
                return maze.isPassable(runner.getXCoord(), runner.getYCoord()-1);
            case SOUTH:
                return maze.isPassable(runner.getXCoord()-1, runner.getYCoord());
            default:
                logger.error("direction not found.");
                System.exit(1);
                return false;
        }
    }
}