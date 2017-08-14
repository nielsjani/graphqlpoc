package graphqlnew.service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
public class PokemonService {

    @Inject
    private PokemonGraphFactory pokemonGraphFactory;

    public Map<String, Object> get(String query, Map<String, Object> arguments) {
        return executeGraphQLQuery(query, arguments);
    }

    public Map<String, Object> mutate(String query, Map<String, Object> args) {
        return executeGraphQLQuery(query, args);
    }

    private Map<String, Object> executeGraphQLQuery(String query, Map<String, Object> arguments) {
        return pokemonGraphFactory.getGraph().execute(query, (Object) null, arguments).getData();
    }
}
