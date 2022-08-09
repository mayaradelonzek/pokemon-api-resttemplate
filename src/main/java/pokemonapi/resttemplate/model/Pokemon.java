package pokemonapi.resttemplate.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq")
    @SequenceGenerator(name = "pokemon_seq", sequenceName = "POKEMON_SEQ", initialValue = 906, allocationSize = 1)
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

    @ApiModelProperty(value = "Pokémon types", required = true)
    @ManyToOne
    @JoinColumn(name="type1_id", nullable=false)
    private Type type1;

    @ApiModelProperty(value = "Pokémon types", required = false)
    @ManyToOne
    @JoinColumn(name="type2_id")
    private Type type2;

    @ApiModelProperty(value = "Pokémon moves", required = false)
    @ManyToMany
    @JoinTable(
            name = "pokemon_moves",
            joinColumns = @JoinColumn(name = "id_pokemon"),
            inverseJoinColumns = @JoinColumn(name = "id_move"))
    private List<Move> moves;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    public Boolean hasTwoTypes() {
        return StringUtils.isNotBlank(Optional.ofNullable(this.type2)
                        .orElse(new Type())
                        .getName());
    }
}
