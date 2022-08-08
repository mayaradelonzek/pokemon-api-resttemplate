package pokemonapi.resttemplate.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.integration.model.PokemonResponse;
import pokemonapi.resttemplate.model.Pokemon;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Pokémons")
public interface PokemonController {

    @ApiOperation(value = "Get a pokemon by id", notes = "Returns a pokemon as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> findById(@ApiParam(value = "Pokémon id", example = "1") Integer id);

    @ApiOperation(value = "Get a pokemon by name", notes = "Returns a pokemon as per the name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<PokemonResponse> findByName(@ApiParam(value = "Pokémon name", example = "bulbasaur") String name);

    @ApiOperation(value = "Create a new pokemon", notes = "Save a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<Pokemon> Save(@Valid @RequestBody Pokemon pokemon, HttpServletResponse httpServletResponse);

    @ApiOperation(value = "Delete a pokemon", notes = "Remove a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public void remove(@PathVariable Integer id);

    @ApiOperation(value = "Edit a pokemon", notes = "Edit a pokemon")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The pokémon was not found")
    })
    public ResponseEntity<Pokemon> edit(@PathVariable Integer id, @Valid @RequestBody Pokemon pokemon);

}
