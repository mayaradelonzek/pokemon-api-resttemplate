package pokemonapi.resttemplate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.model.Move;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Moves")
public interface MoveController {

    @ApiOperation(value = "Create a new Pokémon move", notes = "Save a Pokémon move")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The Pokémon move was not found")
    })
    public ResponseEntity<Move> Save(@Valid @RequestBody Move move, HttpServletResponse httpServletResponse);
}
