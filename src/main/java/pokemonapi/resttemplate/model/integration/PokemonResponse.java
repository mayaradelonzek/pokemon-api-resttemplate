package pokemonapi.resttemplate.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pokemonapi.resttemplate.config.deserializer.MoveDeserializer;
import pokemonapi.resttemplate.config.deserializer.SpriteDeserializer;
import pokemonapi.resttemplate.config.deserializer.TypeDeserializer;

import java.net.URI;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonResponse {
    private int id;
    private String name;
    private int height;
    private int weight;

    @JsonProperty(value = "sprites")
    @JsonDeserialize(using = SpriteDeserializer.class)
    private URI spriteURI;

    @JsonProperty(value = "types")
    @JsonDeserialize(using = TypeDeserializer.class)
    private List<String> types;

    @JsonProperty(value = "moves")
    @JsonDeserialize(using = MoveDeserializer.class)
    private List<String> moves;
}
