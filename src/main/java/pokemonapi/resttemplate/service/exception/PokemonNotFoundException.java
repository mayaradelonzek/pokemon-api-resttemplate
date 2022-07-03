package pokemonapi.resttemplate.service.exception;

import org.springframework.http.HttpStatus;

public class PokemonNotFoundException extends BusinessException {

    private static final String DEFAULT_MESSAGE = "O pokémon %s não foi encontrado";

    public PokemonNotFoundException(Integer pokemonId) {
        this(pokemonId.toString());
    }

    public PokemonNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, String.format(DEFAULT_MESSAGE, name));
    }

}
