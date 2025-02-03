package ca.mcmaster.se2aa4.mazerunner;

import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    
    private String mazeinput;
    private int[][] maze;
    private int wall;
    private int pass;
    private int[] dimensions;
    private int xlen;
    private int ylen;
    private int starty;
    private int endy;

    public Maze(String mazeinput) {
        this.mazeinput = mazeinput;
        this.wall = 1;
        this.pass = 0;
        this.dimensions = getMazeDimensions();
        this.xlen = dimensions[0];
        this.ylen = dimensions[1];
        this.maze = mazeParser();
        this.starty = locateStart();
        this.endy = locateEnd();
    }

    public int getXLen() {
        return xlen;
    }

    public int getYLen() {
        return ylen;
    }   

    public int getStartY() {
        return starty;
    }

    public int getEndY() {
        return endy;
    }

    public int[] getMazeDimensions() {
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
            scanner.close();
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
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.error("file not found");
            System.exit(1);
        }
        return maze;
    }

    //returns y coordinate of starting square (x is assumed to be 0) 
    public int locateStart() {
        int ycoord = -1;
        int iswall = 1;
        while (iswall == 1) {
            ycoord++;
            iswall = maze[0][ycoord];
        }
        logger.info("Maze Entrace Coordinates: {}, {}", 0, ycoord);
        return ycoord;
    }

    //returns y coordinate of ending square (x is assumed to be xlen-1) 
    public int locateEnd() {
        int ycoord = -1;
        int iswall = 1;
        while(iswall == 1) {
            ycoord++;
            iswall = maze[xlen-1][ycoord];
        }
        logger.info("Maze Exit Coordinates: {}, {}", xlen-1, ycoord);
        return ycoord;
    }

    public boolean isPassable(int xcoord, int ycoord) {
        if (maze[xcoord][ycoord] == 0) {
            return true;
        } else { return false; }
    }
}




