package graphqlnew.rest.game;

import graphql.annotations.GraphQLField;

import java.io.Serializable;
public class PokemonGameDto implements Serializable {
    @GraphQLField
    public int generation;
    //Notice the array, not a list. When using lists, Graphql freaks out.
    @GraphQLField
    public CityDto[] cities;
    @GraphQLField
    public EvilTeamDto evilTeam;

    public PokemonGameDto() {}

}
