package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathChecker extends PathUtils implements ChecksPath {

    private static final Logger logger = LogManager.getLogger();

    private String userpath;

    public PathChecker(Maze maze, Runner runner, String userpath) {
        super(maze, runner);
        this.userpath = factorizedToCanonical(userpath);
    }
   
    public boolean checkPath() {
        boolean isendrightside = (runner.getXCoord() == 0) ? true : false;
        
        for (int i = 0; i < userpath.length(); i++) {
            logger.trace("Runner position: {}, {}", runner.getXCoord(), runner.getYCoord());
            if (userpath.charAt(i) == 'F') {
                moveForward();
                logger.trace("runner moved forward.");
            } else if (userpath.charAt(i) == 'L') {
                runner.changeDirection(false);
                logger.trace("runner turned left");
            } else if (userpath.charAt(i) == 'R') {
                runner.changeDirection(true);
                logger.trace("runner turned right");
            } else if (userpath.charAt(i) == ' ') {
                //ignore whitespace
            } else {
                logger.error("ERROR: invalid character found: {}", userpath.charAt(i));
                System.out.println("ERROR: invalid path sequence.");
                System.exit(1);
            }
            
        }
        logger.trace("Runner position: {}, {}", runner.getXCoord(), runner.getYCoord());
        boolean atExit = (isendrightside) ? isAtRightExit() : isAtLeftExit();
        if (atExit) {
            return true;
        } else {
            return false;
        }
    }

}