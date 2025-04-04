package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;

public class CLIAdapter {
    
    private static final Logger logger = LogManager.getLogger();

    public static InputData adapt(CommandLine cmd) {
        String mazeInput = cmd.getOptionValue("i");
        if (mazeInput == null) {
            logger.error("Error: no -i flag was found");
            System.exit(1);
        }

        String[] pathArray = cmd.getOptionValues("p");
        String pathInput = (pathArray != null) ? String.join(" ", pathArray) : null;
        boolean checkPath = cmd.hasOption("p");
        return new InputData(mazeInput, pathInput, checkPath);
    
    }
}
