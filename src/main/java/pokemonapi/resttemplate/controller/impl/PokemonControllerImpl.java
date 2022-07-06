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

@RestController
@RequestMapping(value = "/api/v1/pokemons")
@CrossOrigin(origins = "*")
public class PokemonControllerImpl implements PokemonController {

    public PokemonIntegrationService pokemonIntegrationService;

    protected PokemonControllerImpl(PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonIntegrationService = pokemonIntegrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> findById(@PathVariable("id") Integer id ) {
        PokemonResponse pokemon = pokemonIntegrationService.findById(id);
        return ResponseEntity.ok(PokemonConverter.converter(pokemon));
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<PokemonResponse> findByName(@RequestParam("name") String name) {
        PokemonResponse pokemonResponse = pokemonIntegrationService.findByName(name);
        return ResponseEntity.ok(PokemonConverter.converter(pokemonResponse));
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
