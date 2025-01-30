package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder extends PathUtils {
    
    private static final Logger logger = LogManager.getLogger();

    public PathFinder(Maze maze, Runner runner) {
         super(maze, runner);
    }
    
    public void findPath() {

        while (!isAtExit()) {
            algoMove();
        }




        /* 
        int maxsteps = maze.getXLen() * maze.getYLen() * 2;
        int steps = 0;
        while (!isAtExit() && steps < maxsteps) {
            if (canMoveForward()) {
                runner.moveForward();
                System.out.print("F");
            } 
            steps++;
        }
        System.out.println();

        if (isAtExit()) {
            logger.info("Path found!");
        } else  {
            logger.error("ERROR:Path was not found.");
        }   
        */
    }

    public void algoMove() {
        if (isRightHandPassable()) {
            runner.changeDirection("RIGHT");
            runner.moveForward();
            System.out.print(" R F");
        } else if (canMoveForward()) {
            runner.moveForward();
            System.out.print("F");
        } else {
            runner.changeDirection("LEFT");
            runner.moveForward();
            System.out.print(" L ");
        }
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