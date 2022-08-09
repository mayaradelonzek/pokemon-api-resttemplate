package pokemonapi.resttemplate.controller.dto;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class UpdatePokemonRequest {

    @Min(value=1, message = "O Pokémon não pode ter altura nula ou negativa")
    @Max(value=1000, message = "Altura máxima permitida é de 1000m")
    private int height;

    @Min(value=1, message = "O Pokémon não pode ter peso nulo ou negativo")
    @Max(value=1000, message = "Peso máximo permitido é de 1000kg")
    private int weight;

}
