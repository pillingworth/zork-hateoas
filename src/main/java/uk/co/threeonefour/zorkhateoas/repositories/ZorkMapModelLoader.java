package uk.co.threeonefour.zorkhateoas.repositories;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.threeonefour.zorkhateoas.model.MapModel;

@Configuration
public class ZorkMapModelLoader {

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public MapModel loadZork() throws IOException {

    ClassPathResource resource = new ClassPathResource("/data/zork.json");
    try (InputStream inputStream = resource.getInputStream();) {
      return objectMapper.readValue(inputStream, MapModel.class);
    }
  }

}

