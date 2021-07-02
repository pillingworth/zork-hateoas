package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;

public class Map implements Serializable {

  private static final long serialVersionUID = 1L;

  private String identifier;

  private String name;

  private String description;

  private Room start;

  public Map() {

  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Room getStart() {
    return start;
  }

  public void setStart(Room start) {
    this.start = start;
  }


}
