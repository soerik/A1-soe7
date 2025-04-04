package ca.mcmaster.se2aa4.mazerunner;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class UnitTest {

    // Utility method to create a temporary maze file from a string
    private String createTempMazeFile(String content) throws IOException {
        Path tempFile = Files.createTempFile("maze", ".txt");
        Files.write(tempFile, content.getBytes());
        return tempFile.toString();
    }

    // Test 1: Verify that an open maze file has the expected dimensions.
    @Test
    public void testMazeDimensions() throws IOException {
        String mazeContent = "...\n...\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);
        int[] dimensions = maze.getMazeDimensions();

        assertEquals(3, dimensions[0]);
        assertEquals(3, dimensions[1]);
    }

    // Test 2: Verify that Maze.locateStart() and Maze.locateEnd() return the correct rows.
    @Test
    public void testLocateStartEnd() throws IOException {
        String mazeContent = "...\n...\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);

        assertEquals(0, maze.getStartY());
        assertEquals(0, maze.getEndY());
    }

    // Test 3: Verify that Maze.isPassable() correctly distinguishes walls from passable cells.
    @Test
    public void testIsPassable() throws IOException {
        String mazeContent = ".#.\n.#.\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);

        // (0,0) is '.', so it is passable.
        assertTrue(maze.isPassable(0, 0));
        // (1,0) is '#', so it is a wall.
        assertFalse(maze.isPassable(1, 0));
    }

    // Test 4: Verify that factorizedToCanonical converts a factorized path into its canonical form.
    @Test
    public void testFactorizedToCanonical() {
        PathFinder dummy = new PathFinder(null, null);
        String input = "2F L 3R";
        String expected = "FFLRRR";

        assertEquals(expected, dummy.factorizedToCanonical(input));
    }

    // Test 5: Verify that canonicalToFactorized compresses a canonical path correctly.
    @Test
    public void testCanonicalToFactorized() {
        PathFinder dummy = new PathFinder(null, null) ;
        String input = "FFLRRR";
        String expected = "2F L 3R";

        assertEquals(expected, dummy.canonicalToFactorized(input));
    }

    @Test
    public void testRightTurn() {
        
        // Right turn (East to South)
        Runner runner = new Runner(0,0, Direction.EAST);
        runner.changeDirection(true); // EAST -> SOUTH.
        assertEquals(Direction.SOUTH, runner.getDirection());
    }

    @Test
    public void testLeftTurn() {
        // Right turn (East to North)
        Runner runner = new Runner(0,0, Direction.EAST); 
        runner.changeDirection(false);
        assertEquals(Direction.NORTH, runner.getDirection());
    }

    // Test 7: Verify that Runner.moveForward() updates the coordinates correctly.
    @Test
    public void testMoveForward() {
        Runner runner = new Runner(0,1, Direction.EAST);
        runner.moveForward();
        // Moving forward while facing EAST should increment the x-coordinate.
        assertEquals(1, runner.getXCoord());
        assertEquals(1, runner.getYCoord());
    }

    // Test 8: Verify that PathChecker.checkPath() returns true for a correct path.
    @Test
    public void testPathCheckerValidPath() throws IOException {
        String mazeContent = "...\n...\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);
        Runner runner = new Runner(0,maze.getStartY(), Direction.EAST);
        String userPath = "FF"; 
        PathChecker checker = new PathChecker(maze, runner, userPath);
        assertTrue(checker.checkPath());
    }

    // Test 9: Verify that factorizedToCanonical handles multi-digit numbers correctly.
    @Test
    public void testFactorizedToCanonicalMultipleDigits() {
        
        String input = "10F";
        PathChecker dummy = new PathChecker(null, null, input) {
            @Override
            public boolean isAtRightExit() { return false; }
            @Override
            public boolean isAtLeftExit() { return false; }
            @Override
            public boolean canMoveForward() { return false; }
        };
        
        String expected = "FFFFFFFFFF";
        assertEquals(expected, dummy.factorizedToCanonical(input));
    }
}
