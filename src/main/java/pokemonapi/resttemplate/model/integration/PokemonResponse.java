package pokemonapi.resttemplate.model.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
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

@ApiModel(description = "Represents a Pokémon")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonResponse {

    @ApiModelProperty(value = "Pokémon ID", example = "1", required = true)
    private int id;

    @ApiModelProperty(value = "Pokémon name", example = "bulbasaur", required = true)
    private String name;

    @ApiModelProperty(value = "Pokémon height", example = "7", required = false)
    private int height;

    @ApiModelProperty(value = "Pokémon weight", example = "69", required = false)
    private int weight;

    @ApiModelProperty(value = "Pokémon sprite", example = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/1.png", required = false)
    @JsonProperty(value = "sprites")
    @JsonDeserialize(using = SpriteDeserializer.class)
    private URI spriteURI;

    @ApiModelProperty(value = "Pokémon types", required = false)
    @JsonProperty(value = "types")
    @JsonDeserialize(using = TypeDeserializer.class)
    private List<String> types;

    @ApiModelProperty(value = "Pokémon moves", required = false)
    @JsonProperty(value = "moves")
    @JsonDeserialize(using = MoveDeserializer.class)
    private List<String> moves;
}
