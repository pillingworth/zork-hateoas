package uk.co.threeonefour.zorkhateoas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class RoomMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    private String id;
    
    @JsonView(Views.Summary.class)
    private String identifier;
    
    @JsonView(Views.Summary.class)
    private String name;
    
    @JsonView(Views.Summary.class)
    private String description;

    private List<Room> rooms;
    private List<Exit> exits;

    public RoomMap() {

    }

    public RoomMap(List<Room> rooms, List<Exit> exits) {
        this.rooms = rooms;
        this.exits = exits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "RoomMap [id=" + id + ", identifier=" + identifier + ", name=" + name + ", description=" + description
                + "]";
    }


}