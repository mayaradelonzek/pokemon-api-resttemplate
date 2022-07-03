package pokemonapi.resttemplate.config.converter;

import pokemonapi.resttemplate.model.integration.PokemonResponse;

public class PokemonConverter {

    public static PokemonResponse converter(PokemonResponse pokemonResponse) {
        String name = pokemonResponse.getName();
        pokemonResponse.setName(converterPrimeiraLetraParaMaiuscula(name));
        return pokemonResponse;
    }

    private static String converterPrimeiraLetraParaMaiuscula(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
