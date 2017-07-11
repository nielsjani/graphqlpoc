package graphql.rest;

import graphql.GraphQL;
import graphql.db.Db;
import graphql.mapping.PokemonGame;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.Map;

import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class Main {

    public static void main(String[] args) {

        GraphQLObjectType game = newObject()
                .name("game")
                .field(newFieldDefinition()
                        .name("generation")
                        .type(GraphQLInt)
                        .dataFetcher(obj -> ((PokemonGame) obj.getSource()).getGeneration())
                        .build())
                .build();

        GraphQLObjectType queryType = newObject()
                .name("query")
                .field(newFieldDefinition()
                        .name("games")
                        .type(new GraphQLList(game))
                        .dataFetcher(env -> Db.getInstance().getAll()))
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();


        Map<String, Object> result2 = graphQL.execute(
                "{games {generation}}"
        ).getData();
        System.out.println(result2);
    }
}
