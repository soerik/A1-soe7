package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder extends PathUtils {
    
    private static final Logger logger = LogManager.getLogger();

    public PathFinder(Maze maze, Runner runner) {
         super(maze, runner);
    }
    
    public void findPath() {
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
    }
    
    



    
}