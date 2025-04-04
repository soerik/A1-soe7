package ca.mcmaster.se2aa4.mazerunner;

public class RunnerFactory {

    public static Runner createRunner(String type, Maze maze) {
        if ("leftToRight".equalsIgnoreCase(type)) {
            // left-to-right runner: starting x is fixed at 0
            return new Runner(0, maze.getStartY());
        } else if ("rightToLeft".equalsIgnoreCase(type)) {
            return new Runner(maze.getXLen()-1, maze.getEndY());
        } else {
            throw new IllegalArgumentException("Unknown runner type: " + type);
        }
    }
}
