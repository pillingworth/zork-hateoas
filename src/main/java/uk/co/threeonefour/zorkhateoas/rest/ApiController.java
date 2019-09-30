package uk.co.threeonefour.zorkhateoas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class ApiController {

    public ApiController() {
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ApiMetadata> discover() {

        /*
         * See https://stackoverflow.com/questions/36303642/spring-hateoas-controllerlinkbuilder-methodon-increasing-
         * response-times-signific for other examples of creating links using a processor to decorate objects
         */

        ApiMetadata apiMetadata = ApiMetadata.builder().name("Zork Hateoas")
                .description("Example Hateoas REST API using Zork").build();
        // and so on

        EntityModel<ApiMetadata> em = new EntityModel<>(apiMetadata);
        em.add(linkTo(MapController.class).withRel(IanaLinkRelations.COLLECTION));
        em.add(linkTo(methodOn(MapController.class).listMaps()).withRel(IanaLinkRelations.COLLECTION));

        return em;
    }

    public static final class ApiMetadata {
        private final String name;
        private final String description;
        private final String copyright;

        private ApiMetadata(String name, String description, String copyright) {
            super();
            this.name = name;
            this.description = description;
            this.copyright = copyright;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getCopyright() {
            return copyright;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String name;
            private String description;
            private String copyright;

            private Builder() {

            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder description(String description) {
                this.description = description;
                return this;
            }

            public Builder copyright(String copyright) {
                this.copyright = copyright;
                return this;
            }

            public ApiMetadata build() {
                return new ApiMetadata(name, description, copyright);
            }
        }
    }

}