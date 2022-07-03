package pokemonapi.resttemplate.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class SpriteDeserializer extends JsonDeserializer<URI> {

    @Override
    public URI deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(p);

        String nodeGen2 = root.findPath("versions")
                .findPath("generation-ii")
                .findPath("silver")
                .findPath("front_shiny")
                .textValue();

        String nodeDefault = root.findPath("front_default").textValue();

        String imgUri = Optional.ofNullable(nodeGen2).orElse(nodeDefault);

        return URI.create(imgUri);
    }

}
