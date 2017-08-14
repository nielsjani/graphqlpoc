package graphqlnew.rest;

import graphqlnew.rest.graphql.GraphDto;
import graphqlnew.service.PokemonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.Map;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api")
public class PokemonResource {

    @Inject
    private PokemonService pokemonService;

    /*
    Simple rest-call to check if the app is running. Has nothing to do with Graphql
     */
    @RequestMapping(method = RequestMethod.GET, produces = TEXT_PLAIN_VALUE, path = "/status")
    public String getHealth() {
        return "Hello!";
    }

    /*
    You could use a GET-call and pass the graphql query as a queryparam, but then you'd have to escape the query.
    Why? The graphql query usually contains some 'strange' characters like '{' or ','.
    That's why I chose to use a PUT-call with a requestBody.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Map<String, Object> query(@RequestBody GraphDto graphDto) {
        return pokemonService.get(graphDto.query, graphDto.getArgs());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> mutate(@RequestBody GraphDto graphDto) {
        return pokemonService.mutate(graphDto.query, graphDto.getArgs());
    }
}
