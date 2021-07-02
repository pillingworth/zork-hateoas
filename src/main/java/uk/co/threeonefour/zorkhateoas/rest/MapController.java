package uk.co.threeonefour.zorkhateoas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.threeonefour.zorkhateoas.model.Map;
import uk.co.threeonefour.zorkhateoas.model.Room;
import uk.co.threeonefour.zorkhateoas.repositories.MapRepository;
import uk.co.threeonefour.zorkhateoas.repositories.RoomRepository;

@RestController
@RequestMapping(path = "/maps")
public class MapController {

  private final MapRepository repository;

  private final RoomRepository roomRepository;

  public MapController(MapRepository repository, RoomRepository roomRepository) {
    this.repository = repository;
    this.roomRepository = roomRepository;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<Map>>> listMaps() {

    List<EntityModel<Map>> resourceList = new ArrayList<>();
    repository.findAll().forEach((map) -> {
      EntityModel<Map> resource = EntityModel.of(map);
      resource.add(linkTo(methodOn(MapController.class).getMap(map.getIdentifier())).withSelfRel());
      resourceList.add(resource);
    });

    CollectionModel<EntityModel<Map>> resources = CollectionModel.of(resourceList);
    resources.add(linkTo(MapController.class).withSelfRel());

    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{mapId}")
  public ResponseEntity<EntityModel<Map>> getMap(@PathVariable String mapId) {

    return this.repository.findById(mapId).map((map) -> {
      EntityModel<Map> resource = EntityModel.of(map);
      resource.add(linkTo(methodOn(MapController.class).getMap(mapId)).withSelfRel());
      roomRepository.findFirstByMapIdentifier(mapId).ifPresent((room) -> {
        resource.add(linkTo(MapController.class).slash(mapId).slash("rooms").slash(room.getKey())
            .withRel("firstRoom"));
      });

      return resource;
    }).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }


  @GetMapping("/{mapId}/rooms")
  public ResponseEntity<CollectionModel<EntityModel<Room>>> listMapRooms(
      @PathVariable String mapId) {

    List<EntityModel<Room>> resourceList = new ArrayList<>();
    roomRepository.findAllByMapIdentifier(mapId).forEach((room) -> {
      EntityModel<Room> resource = EntityModel.of(room);
      resource.add(
          linkTo(methodOn(MapController.class).getMapRoom(mapId, room.getKey())).withSelfRel());
      resourceList.add(resource);
    });

    CollectionModel<EntityModel<Room>> resources = CollectionModel.of(resourceList);
    resources.add(linkTo(methodOn(MapController.class).listMapRooms(mapId)).withSelfRel());

    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{mapId}/rooms/{roomKey}")
  public ResponseEntity<EntityModel<Room>> getMapRoom(@PathVariable String mapId,
      @PathVariable String roomKey) {

    return this.roomRepository.findByRoomKey(mapId, roomKey).map((room) -> {
      EntityModel<Room> resource = EntityModel.of(room);
      resource.add(linkTo(methodOn(MapController.class).getMapRoom(mapId, roomKey)).withSelfRel());
      roomRepository.findExitsByRoom(mapId, roomKey).forEach((exit) -> {
        resource.add(linkTo(methodOn(MapController.class).getMapRoom(mapId, exit.getTarget()))
            .withRel(exit.getDir()));
      });

      return resource;
    }).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
