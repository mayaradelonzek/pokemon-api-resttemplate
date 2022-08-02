package pokemonapi.resttemplate.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.integration.service.response.converter.PokemonConverter;
import pokemonapi.resttemplate.controller.PokemonController;
import pokemonapi.resttemplate.controller.Problem;
import pokemonapi.resttemplate.integration.model.PokemonResponse;
import pokemonapi.resttemplate.integration.service.PokemonIntegrationService;
import pokemonapi.resttemplate.integration.service.exception.BusinessException;
import pokemonapi.resttemplate.integration.service.exception.PokemonNotFoundException;
import pokemonapi.resttemplate.model.Pokemon;
import pokemonapi.resttemplate.repository.PokemonRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/pokemons")
@CrossOrigin(origins = "*")
public class PokemonControllerImpl implements PokemonController {

    public PokemonIntegrationService pokemonIntegrationService;
    public PokemonRepository pokemonRepository;

    protected PokemonControllerImpl(PokemonIntegrationService pokemonIntegrationService, PokemonRepository pokemonRepository) {
        this.pokemonIntegrationService = pokemonIntegrationService;
        this.pokemonRepository = pokemonRepository;
    }

    @Deprecated(since = "uso do spring")
    public PokemonControllerImpl() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> findById(@PathVariable("id") Integer id ) {
        PokemonResponse pokemon = pokemonIntegrationService.findById(id);
        return ResponseEntity.ok(PokemonConverter.converter(pokemon));
    }

    @GetMapping("poke/{name}")
    public ResponseEntity<PokemonResponse> findByName(@PathVariable("name") String name) {
        PokemonResponse pokemonResponse = pokemonIntegrationService.findByName(name);
        return ResponseEntity.ok(PokemonConverter.converter(pokemonResponse));
    }

    @PostMapping
    public ResponseEntity<Pokemon> Save(@Valid @RequestBody Pokemon pokemon) {
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<Problem> handlePokemonNotFoundException(PokemonNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = this.buildProblem(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Problem> handleBusinessException(BusinessException ex) {
        HttpStatus status = ex.getStatus();
        Problem problem = this.buildProblem(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(problem);
    }

    private Problem buildProblem(Integer status, String message) {
        return Problem.builder()
                .status(status)
                .message(message)
                .build();
    }
}
