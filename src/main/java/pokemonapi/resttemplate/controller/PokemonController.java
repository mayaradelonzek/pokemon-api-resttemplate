package pokemonapi.resttemplate.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.controller.dto.PokemonResponse;
import pokemonapi.resttemplate.controller.dto.UpdatePokemonRequest;
import pokemonapi.resttemplate.model.Pokemon;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Pokémons")
public interface PokemonController {

    @ApiOperation(value = "Get a pokémon by id", notes = "Returns a pokémon as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> findById(@ApiParam(value = "Pokémon id", example = "1") Integer id);

    @ApiOperation(value = "Get a pokémon by name", notes = "Returns a pokémon as per the name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> findByName(@ApiParam(value = "Pokémon name", example = "bulbasaur") String name);

    @ApiOperation(value = "Get a pokémon heavier than 50kg", notes = "Returns a pokémon heavier than 50kg")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<List<PokemonResponse>> findHeavyPokemon();

    @ApiOperation(value = "Create a new pokémon", notes = "Save a pokémon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> save(@Valid @RequestBody Pokemon pokemon, HttpServletResponse httpServletResponse);

    @ApiOperation(value = "Delete a pokémon", notes = "Remove a pokémon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public void remove(@PathVariable Integer id);

    @ApiOperation(value = "Edit a pokémon", notes = "Edit a pokémon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> edit(@PathVariable Integer id, @Valid @RequestBody UpdatePokemonRequest pokemon);

}
