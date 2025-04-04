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
        assertEquals("Maze x-dimension should be 3", 3, dimensions[0]);
        assertEquals("Maze y-dimension should be 3", 3, dimensions[1]);
    }

    // Test 2: Verify that Maze.locateStart() and Maze.locateEnd() return the correct rows.
    @Test
    public void testLocateStartEnd() throws IOException {
        String mazeContent = "...\n...\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);
        // In an open maze, the first cell in the left column is passable, so startY should be 0,
        // and the first passable cell in the rightmost column is also in row 0.
        assertEquals("Start Y-coordinate should be 0", 0, maze.getStartY());
        assertEquals("End Y-coordinate should be 0", 0, maze.getEndY());
    }

    // Test 3: Verify that Maze.isPassable() correctly distinguishes walls from passable cells.
    @Test
    public void testIsPassable() throws IOException {
        String mazeContent = ".#.\n.#.\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);
        // (0,0) is '.', so it is passable.
        assertTrue("Cell (0,0) should be passable", maze.isPassable(0, 0));
        // (1,0) is '#', so it is a wall.
        assertFalse("Cell (1,0) should be a wall", maze.isPassable(1, 0));
    }

    // Test 4: Verify that factorizedToCanonical converts a factorized path into its canonical form.
    @Test
    public void testFactorizedToCanonical() {
        // Create a dummy PathUtils instance (using an anonymous subclass) since the method doesn't depend on maze/runner.
        String input = "2F L 3R";
        
        PathChecker dummy = new PathChecker(null, null, input) {
            @Override
            public boolean isAtRightExit() { return false; }
            @Override
            public boolean isAtLeftExit() { return false; }
            @Override
            public boolean canMoveForward() { return false; }
        };
        
        String expected = "FFLRRR";
        assertEquals("Factorized path should expand to canonical form", expected, dummy.factorizedToCanonical(input));
    }

    // Test 5: Verify that canonicalToFactorized compresses a canonical path correctly.
    @Test
    public void testCanonicalToFactorized() {
        PathFinder dummy = new PathFinder(null, null) {
            @Override
            public boolean isAtRightExit() { return false; }
            @Override
            public boolean isAtLeftExit() { return false; }
            @Override
            public boolean canMoveForward() { return false; }
        };
        String canonical = "FFLRRR";
        String expected = "2F L 3R";
        assertEquals("Canonical path should factorize to the expected form", expected, dummy.canonicalToFactorized(canonical));
    }

    // Test 6: Verify that Runner.changeDirection() updates the direction correctly.
    @Test
    public void testChangeDirection() {
        // Left-to-right runner starts facing EAST.
        Runner runner = new Runner(0,0);
        runner.changeDirection(true); // Turn right: EAST -> SOUTH.
        assertEquals("Turning right from EAST should yield SOUTH", Direction.SOUTH, runner.getDirection());

        runner = new Runner(0,0); // Reset runner facing EAST.
        runner.changeDirection(false); // Turn left: EAST -> NORTH.
        assertEquals("Turning left from EAST should yield NORTH", Direction.NORTH, runner.getDirection());
    }

    // Test 7: Verify that Runner.moveForward() updates the coordinates correctly.
    @Test
    public void testMoveForward() {
        Runner runner = new Runner(0,1); // This runner starts at (0,1) facing EAST.
        int initialX = runner.getXCoord();
        int initialY = runner.getYCoord();
        runner.moveForward();
        // Moving forward while facing EAST should increment the x-coordinate.
        assertEquals("X-coordinate should increase by 1 when moving EAST", initialX + 1, runner.getXCoord());
        assertEquals("Y-coordinate should remain unchanged when moving EAST", initialY, runner.getYCoord());
    }

    // Test 8: Verify that PathChecker.checkPath() returns true for a correct path.
    @Test
    public void testPathCheckerValidPath() throws IOException {
        // Create a simple 3x3 open maze where the correct path from the left entrance (0,0)
        // to the right exit ((maze.getXLen()-1, maze.getEndY())) is simply moving forward twice ("FF").
        String mazeContent = "...\n...\n...";
        String mazeFile = createTempMazeFile(mazeContent);
        Maze maze = new Maze(mazeFile);
        Runner runner = new Runner(0,maze.getStartY()); // Expected to start at (0,0).
        String userPath = "FF"; // Moves from (0,0) to (2,0) in an open maze.
        PathChecker checker = new PathChecker(maze, runner, userPath);
        assertTrue("A correct path should be validated as correct", checker.checkPath());
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
        
        String expected = "FFFFFFFFFF"; // Expect 10 consecutive 'F' characters.
        assertEquals("Multi-digit factorized input should expand correctly", expected, dummy.factorizedToCanonical(input));
    }

    // Test 10: Verify that Runner.mod returns a positive result for negative dividends.
    @Test
    public void testRunnerMod() {
        Runner runner = new Runner(0,0);
        int result = runner.mod(-1, 5);
        assertEquals("Modulus of -1 mod 5 should be 4", 4, result);
    }
}
