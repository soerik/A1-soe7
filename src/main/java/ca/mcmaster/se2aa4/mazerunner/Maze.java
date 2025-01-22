package ca.mcmaster.se2aa4.mazerunner;

import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    
    private int[][] maze;
    private int wall;
    private int pass;
    private int[] dimensions;
    public int xlen;
    public int ylen;
    private String mazeinput;

    public Maze(String mazeinput) {
        this.mazeinput = mazeinput;
        this.wall = 1;
        this.pass = 0;
        this.dimensions = mazeDimensions();
        this.xlen = dimensions[0];
        this.ylen = dimensions[1];
        this.maze = mazeParser();
    }

    public int[] mazeDimensions() {
        int maze_xlen = 0;
        int maze_ylen = 0;
        
        try {
            File mazetxt = new File(mazeinput);
            Scanner scanner = new Scanner(mazetxt);
            
            //gets the x and y dimensions of maze
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                maze_xlen = line.length();
                maze_ylen++;
            }
        } catch (FileNotFoundException e) {
            logger.error("file not found");
            System.exit(1);
        }
        
        int[] dimensions = {maze_xlen, maze_ylen};
        return dimensions;
    }

    public int[][] mazeParser() {
        int[][] maze = new int[xlen][ylen];
        
        try {
            File mazetxt = new File(mazeinput);
            Scanner scanner = new Scanner(mazetxt);
            int y = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int x = 0; x < line.length(); x++) {
                    maze[x][y] = (line.charAt(x) == '#') ? wall : pass;
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            logger.error("file not found");
            System.exit(1);
        }
        return maze;
    }
}