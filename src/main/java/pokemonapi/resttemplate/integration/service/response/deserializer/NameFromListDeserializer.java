package pokemonapi.resttemplate.integration.service.response.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public abstract class NameFromListDeserializer extends JsonDeserializer<List<String>> {

    private final String field;

    protected NameFromListDeserializer(String field) {
        this.field = field;
    }

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);

        return node.findValues(field)
                .stream()
                .map(c -> c.findPath("name").textValue())
                .toList();
    }

}
