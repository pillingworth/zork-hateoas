package uk.co.threeonefour.zorkhateoas.repositories;

import java.util.Collections;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.threeonefour.zorkhateoas.model.Map;
import uk.co.threeonefour.zorkhateoas.model.MapModel;

@Repository
public class MapRepository implements CrudRepository<Map, String> {

  private final MapModel mapModel;

  public MapRepository(MapModel mapModel) {
    this.mapModel = mapModel;
  }

  @Override
  public Iterable<Map> findAll() {
    return Collections.singletonList(mapModel.asMap());
  }

  @Override
  public Optional<Map> findById(String id) {
    return Optional.of(mapModel.asMap());
  }

  @Override
  public boolean existsById(String id) {
    return true;
  }

  @Override
  public Iterable<Map> findAllById(Iterable<String> ids) {
    return Collections.singletonList(mapModel.asMap());
  }

  @Override
  public long count() {
    return 1;
  }

  @Override
  public <S extends Map> S save(S entity) {
    throw new UnsupportedOperationException("save");
  }

  @Override
  public <S extends Map> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("saveAll");
  }

  @Override
  public void deleteById(String id) {
    throw new UnsupportedOperationException("deleteById");
  }

  @Override
  public void delete(Map entity) {
    throw new UnsupportedOperationException("delete");
  }

  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    throw new UnsupportedOperationException("deleteAllById");
  }

  @Override
  public void deleteAll(Iterable<? extends Map> entities) {
    throw new UnsupportedOperationException("deleteAll");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("deleteAll");
  }
}
