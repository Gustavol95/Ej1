package com.ej1.iedeveloper.ej1.model;

/**
 * Created by iedeveloper on 26/12/16.
 */

public class ControlState {

    private boolean visited;
    private boolean dirty;
    private boolean valid;

    public ControlState() {
        visited=false;
        dirty=false;
        valid=false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
