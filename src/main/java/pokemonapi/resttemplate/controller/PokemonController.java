package pokemonapi.resttemplate.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import pokemonapi.resttemplate.integration.model.PokemonResponse;

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

}
