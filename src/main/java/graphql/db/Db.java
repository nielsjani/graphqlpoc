package graphql.db;

import graphql.mapping.PokemonGame;

import java.util.ArrayList;
import java.util.List;

public class Db {

    private List<PokemonGame> pokemonGames = new ArrayList<>();
    private static Db INSTANCE;

    private Db() {
        this.pokemonGames.add(new PokemonGame(6));
        this.pokemonGames.add(new PokemonGame(5));
    }

    public static Db getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Db();
        }
        return INSTANCE;
    }

    public PokemonGame findByGen() {
        return pokemonGames.get(0);
    }

    public List<PokemonGame> getAll() {
        return pokemonGames;
    }
}
