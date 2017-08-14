package graphqlnew.domain;

import java.util.List;

public class PokemonGameAnnotated {

    public int generation;
    public List<City> cities;
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
}
