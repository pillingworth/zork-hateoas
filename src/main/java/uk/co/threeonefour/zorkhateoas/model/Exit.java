package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;

public class Exit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String source;
    private String target;
    private String direction;

    public Exit() {
    }

    public Exit(String source, String target, String direction) {
        this.source = source;
        this.target = target;
        this.direction = direction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Exit [source=" + source + ", target=" + target + ", direction=" + direction + "]";
    }

}