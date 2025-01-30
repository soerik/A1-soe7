package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    
    private static final Logger logger = LogManager.getLogger();
     
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "maze input");
        options.addOption("p", true, "path input");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        String mazeinput = null;
        String pathinput = null;
        Boolean checkpath = false;

        logger.info("** Starting Maze Runner");
        try {
            cmd = parser.parse( options, args);
            if (cmd.hasOption("i") && cmd.hasOption("p")) {
                mazeinput = cmd.getOptionValue("i");
                pathinput = cmd.getOptionValue("p");
                logger.info("Maze successfully inputted: {}", mazeinput);
                logger.info("Path successfully inputted: {}", pathinput);
                checkpath = true;
            } else if (cmd.hasOption("i")) {
                mazeinput = cmd.getOptionValue("i");
                logger.info("Maze successfully inputted: {}", mazeinput);
                checkpath = false;
            } else {
                logger.error("Error: No -i flag found.");
                System.exit(1);
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        //BUSINESS LOGIC HERE
        Maze maze = new Maze(mazeinput);
        Runner runner = new Runner(maze.getStartY());

        //checks if path is correct (p flag used)
        if (checkpath == true) {
            
            PathChecker path = new PathChecker(maze, runner, pathinput);
            path.checkPath();

        }
        
        //finds possible path (no p flag used)
        else {
            PathFinder path = new PathFinder(maze, runner);
            path.findPath();
        }
        
        
        logger.info("** End of MazeRunner");
    }
}
