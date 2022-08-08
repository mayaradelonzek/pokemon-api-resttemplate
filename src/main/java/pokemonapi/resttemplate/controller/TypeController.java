package pokemonapi.resttemplate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.model.Type;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "Types")
public interface TypeController {

    @ApiOperation(value = "Create a new Pokémon type", notes = "Save a Pokémon type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The Pokémon type was not found")
    })
    public ResponseEntity<Type> Save(@Valid @RequestBody Type type, HttpServletResponse httpServletResponse);
}
