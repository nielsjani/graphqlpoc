package graphql.rest;

import graphql.GraphQL;
import graphql.annotations.GraphQLAnnotations;
import graphql.db.AnnotatedDb;
import graphql.mapping.PokemonGameAnnotated;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.HashMap;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static graphql.Scalars.GraphQLInt;
import static graphql.mapping.City.city;
import static graphql.mapping.EvilTeam.evilTeam;
import static graphql.mapping.PokemonGameAnnotated.pokemonGameAnnotated;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MainAnnotations {

    private static final String SIMPLEQUERY = "{games {generation, cities {name, gymLeader}}}";
    private static final String ARGSQUERY = "{games(generation:5) {generation, evilTeam {name}, cities {name, gymLeader}}}";
    private static final String VARIABLESQUERY = "query GetByGen($gen: Int) {games(generation:$gen) {generation, evilTeam {name}, cities {name, gymLeader}}}";
    private static final String MUTATE = "mutation addgame($newgame: inputPokemonGameAnnotated) {creategame(newgame: $newgame){generation}}";

    public static void main(String[] args) {
        GraphQLObjectType queryType = newObject()
                .name("query")
                .field(newFieldDefinition()
                        .name("games")
                        .argument(newArgument()
                                .name("generation")
                                .type(GraphQLInt)
                                .build())
                        .type(new GraphQLList(GraphQLAnnotations.object(PokemonGameAnnotated.class)))
                        .dataFetcher(env -> {
                            Integer generation = env.getArgument("generation");
                            if (generation == null) {
                                return AnnotatedDb.getInstance().getAll();
                            }
                            return AnnotatedDb.getInstance().findByGen(generation);
                        }))
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        //EXPERIMENT 1
//        Map<String, Object> resultSimpleQuery = graphQL.execute(SIMPLEQUERY).getData();
        //EXPERIMENT 2
//        Map<String, Object> resultArgsQuery = graphQL.execute(ARGSQUERY).getData();

        //EXPERIMENT 3
//        HashMap<String, Object> arguments = newHashMap();
//        arguments.put("gen", 5);
//        Map<String, Object> resultVarsQuery = graphQL.execute(VARIABLESQUERY, (Object) null, arguments).getData();

        //EXPERIMENT 4
        String resultMutate = mutate();

        System.out.println(resultMutate);
    }

    private static String mutate() {
        GraphQLObjectType mutationType = newObject()
                .name("mutation")
                .field(newFieldDefinition()
                        .name("creategame")
                        .argument(newArgument()
                                .name("newgame")
                                .type(GraphQLAnnotations.inputObject(GraphQLAnnotations.object(PokemonGameAnnotated.class), "input"))
                                .build())
                        .type(GraphQLAnnotations.object(PokemonGameAnnotated.class))
                        .dataFetcher(env -> {
                            PokemonGameAnnotated newgame = env.getArgument("newgame");
                            return newgame;
                        }))
                .build();

        HashMap<String, Object> arguments = newHashMap();
        PokemonGameAnnotated toAdd = pokemonGameAnnotated()
                .withGeneration(3)
                .withEvilTeam(evilTeam().withName("Aqua").withPrimaryColor("Blue"))
                .withCities(newArrayList(city()
                        .withName("Sootopolis")
                        .withGymLeader("Wallace")
                        .withEvilOrganisationPresent(false)));
        arguments.put("newgame", toAdd);

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(mutationType)
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        return graphQL.execute(MUTATE, (Object) null, arguments).getData();
    }
}
