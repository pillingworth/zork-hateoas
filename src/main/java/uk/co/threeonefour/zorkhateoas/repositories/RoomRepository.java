package uk.co.threeonefour.zorkhateoas.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.threeonefour.zorkhateoas.model.Exit;
import uk.co.threeonefour.zorkhateoas.model.MapModel;
import uk.co.threeonefour.zorkhateoas.model.Room;

@Repository
public class RoomRepository implements CrudRepository<Room, String> {

  private final MapModel mapModel;

  public RoomRepository(MapModel mapModel) {
    this.mapModel = mapModel;
  }

  @Override
  public List<Room> findAll() {
    return mapModel.getRooms();
  }

  @Override
  public Optional<Room> findById(String id) {
    return mapModel.getRooms().stream().filter((room) -> id.equals(room.getKey())).findFirst();
  }

  @Override
  public boolean existsById(String id) {
    return findById(id).isPresent();
  }

  @Override
  public Iterable<Room> findAllById(Iterable<String> ids) {
    throw new UnsupportedOperationException("findAllById");
  }

  @Override
  public long count() {
    return mapModel.getRooms().size();
  }

  public Optional<Room> findFirstByMapIdentifier(String mapIdentifier) {
    return mapModel.getRooms().stream().findFirst();
  }

  public Iterable<Room> findAllByMapIdentifier(String mapIdentifier) {
    return mapModel.getRooms();
  }

  public Optional<Room> findByRoomKey(String mapIdentifier, String roomKey) {
    return mapModel.getRooms().stream().filter((room) -> room.getKey().equals(roomKey)).findFirst();
  }

  public Iterable<Exit> findExitsByRoom(String mapIdentifier, String roomKey) {
    return mapModel.getExits().stream().filter((exit) -> exit.getSource().equals(roomKey)).collect(Collectors.toList());
  }
  
  @Override
  public <S extends Room> S save(S entity) {
    throw new UnsupportedOperationException("save");
  }

  @Override
  public <S extends Room> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("saveAll");
  }

  @Override
  public void deleteById(String id) {
    throw new UnsupportedOperationException("deleteById");
  }

  @Override
  public void delete(Room entity) {
    throw new UnsupportedOperationException("delete");
  }

  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    throw new UnsupportedOperationException("deleteAllById");
  }

  @Override
  public void deleteAll(Iterable<? extends Room> entities) {
    throw new UnsupportedOperationException("deleteAll");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("deleteAll");
  }
}
