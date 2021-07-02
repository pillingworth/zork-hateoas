package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;
import java.util.List;

public class MapModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private String identifier;

  private String name;

  private String description;

  private List<Room> rooms;

  private List<Exit> exits;

  public MapModel() {

  }

  public MapModel(List<Room> rooms, List<Exit> exits) {
    this.rooms = rooms;
    this.exits = exits;
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

  public Map asMap() {
    Map map = new Map();
    map.setIdentifier(identifier);
    map.setName(this.name);
    map.setDescription(this.description);
    return map;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public List<Exit> getExits() {
    return exits;
  }

  public void setExit(List<Exit> exits) {
    this.exits = exits;
  }

  @Override
  public String toString() {
    return "Map [identifier=" + identifier + ", name=" + name + ", description=" + description
        + "]";
  }


}
