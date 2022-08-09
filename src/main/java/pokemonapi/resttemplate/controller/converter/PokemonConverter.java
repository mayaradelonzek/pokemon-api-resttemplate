package pokemonapi.resttemplate.controller.converter;

import pokemonapi.resttemplate.controller.dto.PokemonResponse;
import pokemonapi.resttemplate.model.Move;
import pokemonapi.resttemplate.model.Pokemon;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PokemonConverter {

    public static PokemonResponse toResponse(Pokemon pokemon) {
        List<String> moveNames = pokemon.getMoves().stream().map(Move::getName).toList();
        List<String> typeNames = new ArrayList<>();
        typeNames.add(pokemon.getType1().getName());
        URI spriteURI = null;

        try {
            spriteURI = new URI(pokemon.getSprite());
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao gerar URI do pokémon");
        }

        if (pokemon.hasTwoTypes()) {
            typeNames.add(pokemon.getType2().getName());
        }

        return PokemonResponse.builder()
                .id(pokemon.getId())
                .moves(moveNames)
                .types(typeNames)
                .name(converterPrimeiraLetraParaMaiuscula(pokemon.getName()))
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .spriteURI(spriteURI)
                .build();
    }

    private static String converterPrimeiraLetraParaMaiuscula(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
