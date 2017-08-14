package graphqlnew.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQL;
import graphql.annotations.GraphQLAnnotations;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphqlnew.domain.City;
import graphqlnew.rest.game.AddCityDto;
import graphqlnew.rest.game.CityDto;
import graphqlnew.rest.game.PokemonGameDto;
import graphqlnew.db.Db;
import graphqlnew.domain.PokemonGameAnnotated;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Named;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static graphql.GraphQL.newGraphQL;
import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphqlnew.domain.City.city;
import static graphqlnew.domain.EvilTeam.evilTeam;
import static graphqlnew.domain.PokemonGameAnnotated.pokemonGameAnnotated;

@Named
class PokemonGraphFactory {

    private GraphQL graph;

    private PokemonGraphFactory() {
        this.buildGraph();
    }

    private void buildGraph() {

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(buildQueryGraph())
                .mutation(buildMutationGraph())
                .build();

        this.graph = newGraphQL(schema).build();
    }

    private GraphQLObjectType buildQueryGraph() {
        return newObject()
                .name("query")
                .field(newFieldDefinition()
                        .name("games")
                        .argument(newArgument()
                                .name("generationmax")
                                .type(GraphQLInt)
                                .build())
                        .type(new GraphQLList(GraphQLAnnotations.object(PokemonGameAnnotated.class)))
                        .dataFetcher(env -> {
                            Integer generation = env.getArgument("generationmax");
                            if (generation == null) {
                                return Db.getInstance().getAll();
                            }
                            return Db.getInstance().findByGenMax(generation);
                        }))
                .build();
    }

    private GraphQLObjectType buildMutationGraph() {
        return newObject()
                .name("mutation")
                .field(newFieldDefinition()
                        .name("creategame")
                        .argument(newArgument()
                                .name("newgame")
                                .type(GraphQLAnnotations.inputObject(GraphQLAnnotations.object(PokemonGameDto.class), "input"))
                                .build())
                        .type(GraphQLAnnotations.object(PokemonGameDto.class))
                        .dataFetcher(env -> {
                            //TODO: map back to dto before returning + change newFieldDefinition.type to PokemonGameDto
                            PokemonGameAnnotated pokemonGameAnnotated = this.mapToDomain(new ObjectMapper().convertValue(env.getArgument("newgame"), PokemonGameDto.class));
                            return Db.getInstance().add(pokemonGameAnnotated);
                        }))
                .field(newFieldDefinition()
                        .name("addcity")
                        .argument(newArgument()
                                .name("newcity")
                                .type(GraphQLAnnotations.inputObject(GraphQLAnnotations.object(AddCityDto.class), "input"))
                                .build())
                        .type(GraphQLAnnotations.object(PokemonGameAnnotated.class))
                        .dataFetcher(env -> {
                            AddCityDto addCityDto = new ObjectMapper().convertValue(env.getArgument("newcity"), AddCityDto.class);
                            City city = this.mapToDomain(addCityDto.cityDto);
                            //TODO: map back to dto before returning + change newFieldDefinition.type to PokemonGameDto
                            return Db.getInstance().updateCity(addCityDto.id, city);
                        }))
                .build();
    }

    private City mapToDomain(CityDto cityDto) {
        return mapCity(cityDto);
    }

    private PokemonGameAnnotated mapToDomain(PokemonGameDto newgame) {
        return pokemonGameAnnotated()
                .withId(RandomUtils.nextInt(0, 10000))
                .withGeneration(newgame.generation)
                .withEvilTeam(evilTeam()
                        .withPrimaryColor(newgame.evilTeam.primaryColor)
                        .withName(newgame.evilTeam.primaryColor))
                .withCities(Arrays.stream(newgame.cities).map(this::mapCity)
                        .collect(Collectors.toList()));
    }

    private City mapCity(CityDto cityDto) {
        return city()
                .withEvilOrganisationPresent(cityDto.evilOrganisationPresent)
                .withGymLeader(cityDto.gymLeader)
                .withName(cityDto.name);
    }

    GraphQL getGraph() {
        return graph;
    }
}
