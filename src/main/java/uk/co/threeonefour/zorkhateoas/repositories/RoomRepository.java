package uk.co.threeonefour.zorkhateoas.repositories;

import java.util.Collections;

import org.springframework.stereotype.Repository;

import uk.co.threeonefour.zorkhateoas.model.Room;

@Repository
public class RoomRepository {

    public Iterable<Room> listRooms(String mapIdentifier) {
        return Collections.emptyList();
    }

    public Room getRoomByIdentifier(String mapIdentifier, String roomIdentifier) {
        throw new RuntimeException("Map with identifier " + mapIdentifier + " not found");
    }
}