package pokemonapi.resttemplate.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pokemonapi.resttemplate.model.integration.PokemonResponse;

import java.util.Optional;

@Service
public class PokemonIntegrationService {

    private final RestTemplate restTemplate;

    private final String uri;

    public PokemonIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.uri = "https://pokeapi.co/api/v2/pokemon";
    }

    public Optional<PokemonResponse> findById(int id) {
        String url = generateURLIntegration(id);
        return Optional.ofNullable(this.restTemplate.getForObject(url, PokemonResponse.class));
    }

    public Optional<PokemonResponse> findByName(String name) {
        String url = generateURLIntegration(name);
        return Optional.ofNullable(this.restTemplate.getForObject(url, PokemonResponse.class));
    }

    private String generateURLIntegration(int id) {
        return this.uri + "/" + id;
    }

    private String generateURLIntegration(String name) {
        return this.uri + "/" + name;
    }
}
