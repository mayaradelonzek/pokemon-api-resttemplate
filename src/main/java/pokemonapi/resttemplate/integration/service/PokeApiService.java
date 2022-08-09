package pokemonapi.resttemplate.integration.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pokemonapi.resttemplate.controller.dto.PokemonResponse;
import pokemonapi.resttemplate.integration.service.exception.BusinessException;
import pokemonapi.resttemplate.integration.service.exception.PokemonNotFoundException;

@Service
public class PokeApiService {

    private final RestTemplate restTemplate;

    private final String uri;

    public PokeApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.uri = "https://pokeapi.co/api/v2/pokemon";
    }

    public PokemonResponse findById(Integer id) {
        String url = generateURLIntegration(id);
        try {
            return this.restTemplate.getForObject(url, PokemonResponse.class);
        } catch (HttpClientErrorException ex) {
            if (HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new PokemonNotFoundException(id.toString());
            }
            throw new BusinessException(ex.getStatusCode());
        }
    }

    public PokemonResponse findByName(String name) {
        String url = generateURLIntegration(name);

        try {
            return this.restTemplate.getForObject(url, PokemonResponse.class);
        } catch (HttpClientErrorException ex) {
            if (HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new PokemonNotFoundException(name);
            }
            throw new BusinessException(ex.getStatusCode());
        }
    }

    private String generateURLIntegration(int id) {
        return this.uri + "/" + id;
    }

    private String generateURLIntegration(String name) {
        return this.uri + "/" + name;
    }
}
