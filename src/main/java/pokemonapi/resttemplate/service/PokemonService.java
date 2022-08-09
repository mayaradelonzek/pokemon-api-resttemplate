package pokemonapi.resttemplate.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pokemonapi.resttemplate.controller.converter.PokemonConverter;
import pokemonapi.resttemplate.controller.dto.PokemonResponse;
import pokemonapi.resttemplate.controller.dto.UpdatePokemonRequest;
import pokemonapi.resttemplate.integration.service.PokeApiService;
import pokemonapi.resttemplate.integration.service.exception.BusinessException;
import pokemonapi.resttemplate.integration.service.exception.PokemonNotFoundException;
import pokemonapi.resttemplate.model.Pokemon;
import pokemonapi.resttemplate.repository.PokemonRepository;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;
    private PokeApiService pokeApiService;

    public PokemonService(PokemonRepository pokemonRepository,
                          PokeApiService pokeApiService) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiService = pokeApiService;
    }

    public PokemonResponse findById(Integer id) {
        try {
            return pokeApiService.findById(id);
        } catch (PokemonNotFoundException ex) {
            Pokemon pokemon = this.pokemonRepository.findByIdAndActive(id, true).orElseThrow(() -> ex);
            return PokemonConverter.toResponse(pokemon);
        }
    }

    public PokemonResponse findByName(String name) {
        try {
            return pokeApiService.findByName(name);
        } catch (PokemonNotFoundException ex) {
            Pokemon pokemon = this.pokemonRepository.findByNameAndActive(name, true).orElseThrow(() -> ex);
            return PokemonConverter.toResponse(pokemon);
        }
    }

    public PokemonResponse create(Pokemon pokemon) {
        try {
            this.findByName(pokemon.getName());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Pokemon já existente");
        } catch (PokemonNotFoundException ex) {
            return PokemonConverter.toResponse(this.pokemonRepository.save(pokemon));
        }
    }

    public PokemonResponse delete(Integer id) {
        Pokemon pokemon = this.pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id.toString()));

        if (!pokemon.getActive()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Pokémon já está inativo");
        }

        pokemon.setActive(Boolean.FALSE);
        return PokemonConverter.toResponse(this.pokemonRepository.save(pokemon));
    }

    public PokemonResponse update(Integer id, UpdatePokemonRequest pokemonUpdate) {
        Pokemon pokemon = this.pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id.toString()));

        if (!pokemon.getActive()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Não é possível alterar pokémons inativos");
        }

        pokemon.setHeight(pokemonUpdate.getHeight());
        pokemon.setWeight(pokemonUpdate.getWeight());
        return PokemonConverter.toResponse(this.pokemonRepository.save(pokemon));
    }

    public List<PokemonResponse> findAllHeavyPokemon() {
        return this.pokemonRepository.findByActiveAndWeightGreaterThan(true, 50).stream()
                .map(PokemonConverter::toResponse)
                .toList();
    }

}
