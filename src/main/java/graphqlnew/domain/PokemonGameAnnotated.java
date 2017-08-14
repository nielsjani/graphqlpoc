package graphqlnew.domain;

import graphql.annotations.GraphQLField;

import java.util.List;

public class PokemonGameAnnotated {

    @GraphQLField
    public int id;
    @GraphQLField
    public int generation;
    @GraphQLField
    public List<City> cities;
    @GraphQLField
    public EvilTeam evilTeam;

    private PokemonGameAnnotated() {
    }

    public static PokemonGameAnnotated pokemonGameAnnotated(){
        return new PokemonGameAnnotated();
    }

    public PokemonGameAnnotated withGeneration(int generation) {
        this.generation = generation;
        return this;
    }

    public PokemonGameAnnotated withCities(List<City> cities) {
        this.cities = cities;
        return this;
    }

    public PokemonGameAnnotated withEvilTeam(EvilTeam evilTeam) {
        this.evilTeam = evilTeam;
        return this;
    }

    public PokemonGameAnnotated withId(int id) {
        this.id = id;
        return this;
    }
}
