package pokemonapi.resttemplate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Health Controller")
@RestController
@RequestMapping("/api/v1/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @ApiOperation(value = "Get a health check from API", notes = "Returns a String")
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Teste API");
    }
}
