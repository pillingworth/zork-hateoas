package uk.co.threeonefour.zorkhateoas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import uk.co.threeonefour.zorkhateoas.model.Room;
import uk.co.threeonefour.zorkhateoas.model.RoomMap;
import uk.co.threeonefour.zorkhateoas.model.Views;
import uk.co.threeonefour.zorkhateoas.repositories.MapRepository;
import uk.co.threeonefour.zorkhateoas.repositories.RoomRepository;

@RestController
@RequestMapping(path = "/api/v1/maps")
public class MapController {

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private RoomRepository roomRepository;

    @JsonView(Views.Summary.class)
    @GetMapping(produces = "application/json")
    public Iterable<RoomMap> listMaps() {
        return mapRepository.listMaps();
    }

    @GetMapping(path = "/{mapIdentifier}", produces = "application/json")
    public RoomMap getMapByIdentifier(@PathVariable(name = "mapIdentifier") String mapIdentifier) {
        return mapRepository.getMapByIdentifier(mapIdentifier);
    }

    @JsonView(Views.Summary.class)
    @GetMapping(path = "/{mapIdentifier}/rooms", produces = "application/json")
    public Iterable<Room> listRoomsByMap(@PathVariable(name = "mapIdentifier") String mapIdentifier) {
        return roomRepository.listRooms(mapIdentifier);
    }

    @GetMapping(path = "/{mapIdentifier}/rooms/{roomIdentifier}", produces = "application/json")
    public Room getRoomByMapAndIdentifier(@PathVariable(name = "mapIdentifier") String mapIdentifier,
            @PathVariable(name = "roomIdentifier") String roomIdentifier) {
        return roomRepository.getRoomByIdentifier(mapIdentifier, roomIdentifier);
    }
}