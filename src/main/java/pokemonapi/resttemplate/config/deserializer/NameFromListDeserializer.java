package pokemonapi.resttemplate.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public abstract class NameFromListDeserializer extends JsonDeserializer<List<String>> {

    private String campo;

    public NameFromListDeserializer(String campo) {
        this.campo = campo;
    }

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(p);

        return node.findValues(campo)
                .stream()
                .map(c -> c.findPath("name").textValue())
                .toList();
    }

}
