package pokemonapi.resttemplate.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Pokemon ID", example = "1", required = true)
    private int id;

    @ApiModelProperty(notes = "Pokemon name", example = "bulbasaur", required = false)
    private String name;

    @ApiModelProperty(notes = "Pokemon height", example = "7", required = false)
    private int height;

    @ApiModelProperty(notes = "Pokemon weight", example = "69", required = false)
    private int weight;

    @ApiModelProperty(notes = "Pokemon sprite", example = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/1.png", required = false)
    @JsonProperty(value = "sprites")
    @JsonDeserialize(using = SpriteDeserializer.class)
    private URI spriteURI;

    @ApiModelProperty(notes = "Pokemon types", required = false)
    @JsonProperty(value = "types")
    @JsonDeserialize(using = TypeDeserializer.class)
    private List<String> types;

    @ApiModelProperty(notes = "Pokemon moves", required = false)
    @JsonProperty(value = "moves")
    @JsonDeserialize(using = MoveDeserializer.class)
    private List<String> moves;
}
