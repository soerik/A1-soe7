package ca.mcmaster.se2aa4.mazerunner;

import java.lang.Character;
import java.lang.StringBuilder;
import java.lang.Integer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class PathUtils implements PathUtilities {

    private static final Logger logger = LogManager.getLogger();

    protected Maze maze;
    protected Runner runner;
    protected Runner rsrunner;

    public PathUtils(Maze maze, Runner runner) {
         this.maze = maze;
         this.runner = runner;
    }

    public boolean isAtRightExit() {
        logger.trace("checking if runner is at exit...");
        if (runner.getXCoord() == maze.getXLen() - 1 && runner.getYCoord() == maze.getEndY()) {
            logger.trace("Runner is at exit!");
            return true;
        } else {
            logger.trace("Runner is not at exit.");
            return false;
        }
    }

    public boolean isAtLeftExit() {
        logger.trace("checking if runner is at exit...");
        if (runner.getXCoord() == 0 && runner.getYCoord() == maze.getStartY()) {
            logger.trace("Runner is at exit!");
            return true;
        } else {
            logger.trace("Runner is not at exit.");
            return false;
        }
    }


    public boolean canMoveForward() {
        Direction direction = runner.getDirection();
        logger.trace("Runner direction: {}", direction);
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


    public String factorizedToCanonical(String factorizedform) {
        logger.trace("Factorized form received: {}", factorizedform);
        int concurrentmoves;
        StringBuilder concurrentmovesstr = new StringBuilder(); //used incase numbers are > 9
        StringBuilder canonicalform = new StringBuilder();
        for (int i = 0; i < factorizedform.length(); i++) {
            char currchar = factorizedform.charAt(i);
            if (Character.isDigit(currchar)) {
                concurrentmovesstr.append(currchar);
            } else if (currchar == 'F' || currchar == 'L' || currchar == 'R') {
                if (concurrentmovesstr.length() == 0) {
                    canonicalform.append(currchar);
                } else {
                    concurrentmoves = Integer.parseInt(concurrentmovesstr.toString());
                    for (int j = 0; j < concurrentmoves; j++) {
                        canonicalform.append(currchar);
                    }
                    concurrentmovesstr.setLength(0); //clears stringbuilder
                }
            } else if (currchar == ' ') {
                //ignore whitespace
            } else { 
                logger.error("ERROR: invalid input found: {}", currchar);
                System.exit(1);
            }
        }
        
        logger.trace("Canonical form converted: {}", canonicalform);
        return canonicalform.toString();
    }

    public String canonicalToFactorized(String canonicalform) {
        char prevmove = canonicalform.charAt(0);
        char currmove;
        int repeatedmovecount = 1;
        StringBuilder factorizedformbuilder = new StringBuilder();
        
        for (int i = 1; i < canonicalform.length(); i++) {
            currmove = canonicalform.charAt(i);
            if (currmove != prevmove) {
                if (repeatedmovecount > 1) {
                    factorizedformbuilder.append(repeatedmovecount);
                }
                factorizedformbuilder.append(prevmove);
                factorizedformbuilder.append(" ");
                prevmove = currmove;
                repeatedmovecount = 1;
            } else {
                repeatedmovecount++;
            }
        }

        //This extra code is to cover the last set of moves
        if (repeatedmovecount > 1) {
            factorizedformbuilder.append(repeatedmovecount);
        }
        factorizedformbuilder.append(prevmove);

        String factorizedform = factorizedformbuilder.toString();
        logger.trace("Converted to factorized form.");

        return factorizedform;

    }


}