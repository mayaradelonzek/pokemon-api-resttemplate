package pokemonapi.resttemplate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pokemonapi.resttemplate.model.integration.PokemonResponse;
import pokemonapi.resttemplate.service.PokemonIntegrationService;

@RestController
@RequestMapping(value = "/api/v1/pokemon")
@CrossOrigin(origins = "*")
public class PokemonController {

    public PokemonIntegrationService pokemonIntegrationService;

    public PokemonController(PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonIntegrationService = pokemonIntegrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> findById(@PathVariable("id") int id) {
        PokemonResponse pokemon = pokemonIntegrationService.findById(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon nao encontrado");
                });
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PokemonResponse> findByName(@PathVariable("name") String name) {
        PokemonResponse pokemonResponse = pokemonIntegrationService.findByName(name)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon nao encontrado");
                });
        return ResponseEntity.ok(pokemonResponse);
    }
}
