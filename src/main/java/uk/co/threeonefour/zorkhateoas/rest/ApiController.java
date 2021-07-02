package uk.co.threeonefour.zorkhateoas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  public ApiController() {}

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public EntityModel<ApiMetadata> discover() {

    ApiMetadata apiMetadata =
        new ApiMetadata("Zork HATEAOS", "Zork as a HATEAOS linked data API", null);

    EntityModel<ApiMetadata> em = EntityModel.of(apiMetadata);
    em.add(linkTo(methodOn(ApiController.class).discover()).withSelfRel());
    em.add(linkTo(methodOn(MapController.class).listMaps()).withRel("maps"));

    return em;
  }

}
