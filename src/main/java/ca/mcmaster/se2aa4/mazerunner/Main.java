package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "maze input");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        logger.info("** Starting Maze Runner");
        try {
            cmd = parser.parse( options, args);
            if (cmd.hasOption("i")) {
                String maze = cmd.getOptionValue("i");
                System.out.println("pass");
                logger.info("Maze successfully inputted: {}", maze);
            } else {
                System.out.println("fail");
                logger.error("Error: No -i flag found.");
                System.exit(1);
        }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        //BUSINESS LOGIC HERE
    
        logger.info("** End of MazeRunner");
        
    }
}
