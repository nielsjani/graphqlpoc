package graphqlnew.db;

import com.google.common.collect.Lists;
import graphqlnew.domain.EvilTeam;
import graphqlnew.domain.PokemonGameAnnotated;
import graphqlnew.domain.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public class Db {

    private List<PokemonGameAnnotated> pokemonGames = new ArrayList<>();
    private static Db INSTANCE;

    private Db() {
        this.pokemonGames.add(PokemonGameAnnotated.pokemonGameAnnotated()
                .withGeneration(6)
                .withEvilTeam(EvilTeam.evilTeam().withName("Flare").withPrimaryColor("Red"))
                .withCities(Lists.newArrayList(City.city()
                        .withName("Luminose City")
                        .withGymLeader("Clement")
                        .withEvilOrganisationPresent(true))));
        this.pokemonGames.add(PokemonGameAnnotated.pokemonGameAnnotated()
                .withGeneration(5)
                .withEvilTeam(EvilTeam.evilTeam().withName("Plasma").withPrimaryColor("Grey"))
                .withCities(Lists.newArrayList(City.city()
                        .withName("Castelia City")
                        .withGymLeader("Burgh")
                        .withEvilOrganisationPresent(false))));
    }

    public static Db getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Db();
        }
        return INSTANCE;
    }

    public List<PokemonGameAnnotated> findByGen(int gen) {
        return pokemonGames.stream().filter(game -> game.generation == gen).collect(Collectors.toList());
    }

    public List<PokemonGameAnnotated> findByGenMax(int gen) {
        return pokemonGames.stream().filter(game -> game.generation <= gen).collect(Collectors.toList());
    }

    public List<PokemonGameAnnotated> getAll() {
        return pokemonGames;
    }

    public PokemonGameAnnotated add(PokemonGameAnnotated newgame) {
        pokemonGames.add(newgame);
        return newgame;
    }
}
