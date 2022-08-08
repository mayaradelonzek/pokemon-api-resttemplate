package pokemonapi.resttemplate.controller.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.controller.MoveController;
import pokemonapi.resttemplate.controller.event.RecursoCriadoEvent;
import pokemonapi.resttemplate.model.Move;
import pokemonapi.resttemplate.repository.MoveRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/moves")
@CrossOrigin(origins = "*")
public class MoveControllerImpl implements MoveController {

    private MoveRepository moveRepository;
    private ApplicationEventPublisher publisher;

    public MoveControllerImpl(MoveRepository moveRepository, ApplicationEventPublisher publisher) {
        this.moveRepository = moveRepository;
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<Move> Save(@Valid @RequestBody Move move, HttpServletResponse httpServletResponse) {
        Move savedMove = moveRepository.save(move);
        publisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, savedMove.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMove);
    }
}
