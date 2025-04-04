package ca.mcmaster.se2aa4.mazerunner;

public class RunnerFactory {

    public static Runner createRunner(String type, Maze maze) {
        if ("leftToRight".equals(type)) {
            return new Runner(0, maze.getStartY(), Direction.EAST);
        } else if ("rightToLeft".equals(type)) {
            return new Runner(maze.getXLen()-1, maze.getEndY(), Direction.WEST);
        } else {
            throw new IllegalArgumentException("Unknown runner type: " + type);
        }
    }
}
