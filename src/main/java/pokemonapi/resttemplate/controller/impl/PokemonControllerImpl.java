package pokemonapi.resttemplate.controller.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.controller.event.RecursoCriadoEvent;
import pokemonapi.resttemplate.integration.service.response.converter.PokemonConverter;
import pokemonapi.resttemplate.controller.PokemonController;
import pokemonapi.resttemplate.controller.Problem;
import pokemonapi.resttemplate.integration.model.PokemonResponse;
import pokemonapi.resttemplate.integration.service.PokemonIntegrationService;
import pokemonapi.resttemplate.integration.service.exception.BusinessException;
import pokemonapi.resttemplate.integration.service.exception.PokemonNotFoundException;
import pokemonapi.resttemplate.model.Pokemon;
import pokemonapi.resttemplate.repository.PokemonRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/pokemons")
@CrossOrigin(origins = "*")
public class PokemonControllerImpl implements PokemonController {

    private PokemonIntegrationService pokemonIntegrationService;
    private PokemonRepository pokemonRepository;

    private ApplicationEventPublisher publisher;

    public PokemonControllerImpl(PokemonIntegrationService pokemonIntegrationService, PokemonRepository pokemonRepository, ApplicationEventPublisher publisher) {
        this.pokemonIntegrationService = pokemonIntegrationService;
        this.pokemonRepository = pokemonRepository;
        this.publisher = publisher;
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
    public ResponseEntity<Pokemon> Save(@Valid @RequestBody Pokemon pokemon, HttpServletResponse httpServletResponse) {
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        publisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, savedPokemon.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        pokemonRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> edit(@PathVariable Integer id, @Valid @RequestBody Pokemon pokemon) {
        try {
//            Pokemon pokemonSalvo = pokemonService.edit(id, pokemon);
//            return ResponseEntity.ok(pokemonSalvo);
            return null;
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
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
