package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

  private static final long serialVersionUID = 1L;

  private String key;
  private String name;
  private String desc;

  public Room() {}

  public Room(String key, String name, String desc) {
    this.key = key;
    this.name = name;
    this.desc = desc;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return "Room [key=" + key + ", name=" + name + ", desc=" + desc + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Room other = (Room) obj;
    return Objects.equals(key, other.key);
  }

}
