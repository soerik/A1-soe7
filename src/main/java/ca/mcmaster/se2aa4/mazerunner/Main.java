package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    
    private static final Logger logger = LogManager.getLogger();
     
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "maze input");

        Option pathOption = Option.builder("p")
            .hasArgs()
            .desc("path input")
            .build();
        options.addOption(pathOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        InputData inputdata = null;

        logger.info("** Starting Maze Runner");
        try {
            cmd = parser.parse( options, args);
            inputdata = CLIAdapter.adapt(cmd);
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        //BUSINESS LOGIC HERE
        Maze maze = new Maze(inputdata.getMazeInput());

        //checks if path is correct (p flag used)
        if (inputdata.isCheckPath() == true) {
            
            Runner lsrunner = RunnerFactory.createRunner("leftToRight", maze);
            Runner rsrunner = RunnerFactory.createRunner("rightToLeft", maze);
            PathChecker path1 = new PathChecker(maze, lsrunner, inputdata.getPathInput());
            PathChecker path2 = new PathChecker(maze, rsrunner, inputdata.getPathInput());
            
            if (path1.checkPath()) {
                System.out.println("correct path");
            } else if (path2.checkPath()) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        }
        
        //finds possible path (no p flag used)
        else {
            Runner runner = RunnerFactory.createRunner("leftToRight", maze);
            PathFinder path = new PathFinder(maze, runner);
            path.findPath();
        }
        
        
        logger.info("** End of MazeRunner");
    }
}
