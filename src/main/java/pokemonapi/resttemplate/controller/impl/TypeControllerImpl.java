package pokemonapi.resttemplate.controller.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokemonapi.resttemplate.controller.TypeController;
import pokemonapi.resttemplate.controller.event.RecursoCriadoEvent;
import pokemonapi.resttemplate.model.Type;
import pokemonapi.resttemplate.repository.TypeRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/types")
@CrossOrigin(origins = "*")
public class TypeControllerImpl implements TypeController {

    private TypeRepository typeRepository;
    private ApplicationEventPublisher publisher;

    public TypeControllerImpl(TypeRepository typeRepository, ApplicationEventPublisher publisher) {
        this.typeRepository = typeRepository;
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<Type> Save(@Valid @RequestBody Type type, HttpServletResponse httpServletResponse) {
        Type savedType = typeRepository.save(type);
        publisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, savedType.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedType);
    }
}
