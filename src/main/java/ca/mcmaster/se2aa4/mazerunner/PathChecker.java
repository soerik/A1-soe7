package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathChecker extends PathUtils {

    private static final Logger logger = LogManager.getLogger();

    private String userpath;

    public PathChecker(Maze maze, Runner runner, String userpath) {
        super(maze, runner);
        this.userpath = userpath;
    }

    public void checkPath() {
        for (int i = 0; i < userpath.length(); i++) {
            if (userpath.charAt(i) == 'F') {
                moveForward();
                logger.trace("runner moved forward.");
            } else if (userpath.charAt(i) == 'L') {
                runner.changeDirection("LEFT");
                logger.trace("runner turned left");
            } else if (userpath.charAt(i) == 'R') {
                runner.changeDirection("RIGHT");
                logger.trace("runner turned right");
            }
            logger.trace("Runner position: {}, {}", runner.getXCoord(), runner.getYCoord());
        }
        
        boolean atExit = isAtExit();
        if (atExit) {
            System.out.println("correct path");
        } else {
            System.out.println("incorrect path");
        }
    }






}