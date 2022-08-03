package pokemonapi.resttemplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pokemonapi.resttemplate.integration.service.response.deserializer.MoveDeserializer;
import pokemonapi.resttemplate.integration.service.response.deserializer.SpriteDeserializer;
import pokemonapi.resttemplate.integration.service.response.deserializer.TypeDeserializer;

import javax.persistence.*;
import java.net.URI;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pokemon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Pokémon ID", example = "1", required = true)
    private Integer id;

    @ApiModelProperty(value = "Pokémon name", example = "bulbasaur", required = true)
    private String name;

    @ApiModelProperty(value = "Pokémon height", example = "7", required = false)
    private int height;

    @ApiModelProperty(value = "Pokémon weight", example = "69", required = false)
    private int weight;

    @ApiModelProperty(value = "Pokémon sprite", example = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/1.png", required = false)
    private String sprite;

    @ApiModelProperty(value = "Pokémon types", required = false)
    @OneToMany
    @JoinColumn(name = "id_pokemon", foreignKey = @ForeignKey(name = "FK_pokemon_type"))
    private List<Type> types;

    @ApiModelProperty(value = "Pokémon moves", required = false)
    @OneToMany
    @JoinColumn(name = "id_pokemon", foreignKey = @ForeignKey(name = "FK_pokemon_move"))
    private List<Move> moves;
}
