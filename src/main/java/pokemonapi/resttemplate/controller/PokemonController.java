package pokemonapi.resttemplate.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pokemonapi.resttemplate.config.converter.PokemonConverter;
import pokemonapi.resttemplate.model.integration.PokemonResponse;
import pokemonapi.resttemplate.service.PokemonIntegrationService;
import pokemonapi.resttemplate.service.exception.BusinessException;
import pokemonapi.resttemplate.service.exception.PokemonNotFoundException;

@RestController
@RequestMapping(value = "/api/v1/pokemon")
@CrossOrigin(origins = "*")
public class PokemonController {

    public PokemonIntegrationService pokemonIntegrationService;

    public PokemonController(PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonIntegrationService = pokemonIntegrationService;
    }

    @ApiOperation(value = "Get a pokemon by id", notes = "Returns a pokemon as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokemon was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> findById(@PathVariable("id") Integer id ) {
        PokemonResponse pokemon = pokemonIntegrationService.findById(id);
        return ResponseEntity.ok(PokemonConverter.converter(pokemon));
    }

    @ApiOperation(value = "Get a pokemon by name", notes = "Returns a pokemon as per the name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokemon was not found")
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<PokemonResponse> findByName(@PathVariable("name") String name) {
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
