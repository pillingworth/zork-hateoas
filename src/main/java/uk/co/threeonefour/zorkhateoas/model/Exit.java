package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;

public class Exit implements Serializable {

  private static final long serialVersionUID = 1L;

  private String source;
  private String target;
  private String dir;

  public Exit() {}

  public Exit(String source, String target, String dir) {
    this.source = source;
    this.target = target;
    this.dir = dir;
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

  public String getDir() {
    return dir;
  }

  public void setDir(String dir) {
    this.dir = dir;
  }

  @Override
  public String toString() {
    return "Exit [source=" + source + ", target=" + target + ", dir=" + dir + "]";
  }

}
