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
        String mazeinput = null;        
        String pathinput = null;
        String[] patharray = null;
        Boolean checkpath = false;

        logger.info("** Starting Maze Runner");
        try {
            cmd = parser.parse( options, args);
            if (cmd.hasOption("i") && cmd.hasOption("p")) {
                logger.trace("Detected i & p flags");
                mazeinput = cmd.getOptionValue("i");
                patharray = cmd.getOptionValues("p");
                if (patharray != null) {
                    pathinput = String.join(" ", patharray); // Join back into a full string
                } else {
                    logger.error("No path given.");
                    System.exit(1);
                }

                logger.info("Maze successfully inputted: {}", mazeinput);
                logger.info("Path successfully inputted: {}", pathinput);
                checkpath = true;
            } else if (cmd.hasOption("i")) {
                logger.trace("detected i flag");
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

        //checks if path is correct (p flag used)
        if (checkpath == true) {
            Runner lsrunner = new Runner(0, maze.getStartY(), Direction.EAST);
            Runner rsrunner = new Runner(maze.getXLen()-1, maze.getEndY(), Direction.WEST);
            PathChecker path1 = new PathChecker(maze, lsrunner, pathinput);
            PathChecker path2 = new PathChecker(maze, rsrunner, pathinput);
            
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
            Runner runner = new Runner(0,maze.getStartY(),Direction.EAST);
            PathFinder path = new PathFinder(maze, runner);
            path.findPath();
        }
        
        
        logger.info("** End of MazeRunner");
    }
}
