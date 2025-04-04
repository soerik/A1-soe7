package ca.mcmaster.se2aa4.mazerunner;

public class InputData {
    private String mazeinput;
    private String pathinput;
    private boolean checkpath;

    public InputData(String mazeinput, String pathinput, boolean checkpath) {
        this.mazeinput = mazeinput;
        this.pathinput = pathinput;
        this.checkpath = checkpath;
    }

    public String getMazeInput() {
        return mazeinput;
    }

    public String getPathInput() {
        return pathinput;
    }

    public boolean isCheckPath() {
        return checkpath;
    }
}

