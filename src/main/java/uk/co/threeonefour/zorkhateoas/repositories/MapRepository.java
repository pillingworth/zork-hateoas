package uk.co.threeonefour.zorkhateoas.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import uk.co.threeonefour.zorkhateoas.db.GraphSupplierTransactionTemplate;
import uk.co.threeonefour.zorkhateoas.model.Room;
import uk.co.threeonefour.zorkhateoas.model.RoomMap;

@Repository
public class MapRepository {

    @Autowired
    Supplier<OrientGraph> orientGraphSupplier;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, RoomMap> maps;

    @PostConstruct
    public void init() {

        maps = new HashMap<>();

        GraphSupplierTransactionTemplate txTemplate = new GraphSupplierTransactionTemplate(orientGraphSupplier);

        ClassPathResource resource = new ClassPathResource("/data/zork.json");
        try (InputStream inputStream = resource.getInputStream();) {
            RoomMap map = objectMapper.readValue(inputStream, RoomMap.class);

            txTemplate.execute(graph -> {
                Vertex mapVertex = graph.addVertex("class:map");
                mapVertex.setProperty("identifier", map.getIdentifier());
                mapVertex.setProperty("name", map.getName());
                mapVertex.setProperty("description", map.getDescription());

                if (map.getRooms() != null) {
                    for (Room room : map.getRooms()) {
                        Vertex roomVertex = graph.addVertex("class:room");
                        roomVertex.setProperty("identifier", room.getKey());
                        roomVertex.setProperty("name", room.getName());
                        roomVertex.setProperty("description", room.getDescription());

                    }
                }

                return true;
            });

        } catch (IOException e) {
            throw new RuntimeException("Failed to load maps", e);
        }
    }

    public Iterable<RoomMap> listMaps() {
        return maps.values();
    }

    public RoomMap getMapByIdentifier(String identifier) {
        RoomMap map = maps.get(identifier);
        if (map == null) {
            throw new RuntimeException("Map with identifier " + identifier + " not found");
        }
        return map;
    }
}