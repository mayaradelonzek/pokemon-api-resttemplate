package pokemonapi.resttemplate.integration.service.response.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

public class SpriteDeserializer extends JsonDeserializer<URI> {

    private static final String UNDEFINED_SPRITE = "https://media-exp1.licdn.com/dms/image/C560BAQH13TDLlaBLbA/company-logo_200_200/0/1584544180342?e=2147483647&v=beta&t=WAU3JlVFWsSIiIRfQs7dzzzhWkjaT0UipgQ5P1opEVY";

    @Override
    public URI deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonParser);

        String nodeGen2 = root.findPath("versions")
                .findPath("generation-ii")
                .findPath("silver")
                .findPath("front_shiny")
                .textValue();

        String nodeDefault = root.findPath("front_default").textValue();

        String imgUri = Optional.ofNullable(nodeGen2).orElse(nodeDefault);


        return URI.create(StringUtils.isBlank(imgUri) ? UNDEFINED_SPRITE : imgUri);
    }

}
