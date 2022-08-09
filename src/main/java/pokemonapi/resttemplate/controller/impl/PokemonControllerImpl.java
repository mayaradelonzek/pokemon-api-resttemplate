package pokemonapi.resttemplate.controller.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.controller.converter.PokemonConverter;
import pokemonapi.resttemplate.controller.dto.UpdatePokemonRequest;
import pokemonapi.resttemplate.controller.event.RecursoCriadoEvent;
import pokemonapi.resttemplate.controller.PokemonController;
import pokemonapi.resttemplate.controller.dto.PokemonResponse;
import pokemonapi.resttemplate.model.Pokemon;
import pokemonapi.resttemplate.service.PokemonService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pokemons")
@CrossOrigin(origins = "*")
public class PokemonControllerImpl implements PokemonController {

    private PokemonService pokemonService;
    private ApplicationEventPublisher publisher;

    public PokemonControllerImpl(PokemonService pokemonService,
                                 ApplicationEventPublisher publisher) {
        this.pokemonService = pokemonService;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<List<PokemonResponse>> findHeavyPokemon() {
        return ResponseEntity.ok(this.pokemonService.findAllHeavyPokemon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> findById(@PathVariable("id") Integer id ) {
        return ResponseEntity.ok(this.pokemonService.findById(id));
    }

    @GetMapping("poke/{name}")
    public ResponseEntity<PokemonResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(pokemonService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<PokemonResponse> save(@Valid @RequestBody Pokemon pokemon, HttpServletResponse httpServletResponse) {
        PokemonResponse savedPokemon = this.pokemonService.create(pokemon);
        publisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, savedPokemon.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        this.pokemonService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponse> edit(@PathVariable Integer id, @Valid @RequestBody UpdatePokemonRequest pokemon) {
        return ResponseEntity.ok(this.pokemonService.update(id, pokemon));
    }
}
