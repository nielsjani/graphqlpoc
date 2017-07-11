package graphql.db;

import graphql.mapping.EvilTeam;
import graphql.mapping.PokemonGameAnnotated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static graphql.mapping.City.city;
import static graphql.mapping.EvilTeam.evilTeam;
import static graphql.mapping.PokemonGameAnnotated.pokemonGameAnnotated;

public class AnnotatedDb {

    private List<PokemonGameAnnotated> pokemonGames = new ArrayList<>();
    private static AnnotatedDb INSTANCE;

    private AnnotatedDb() {
        this.pokemonGames.add(pokemonGameAnnotated()
                .withGeneration(6)
                .withEvilTeam(evilTeam().withName("Flare").withPrimaryColor("Red"))
                .withCities(newArrayList(city()
                        .withName("Luminose City")
                        .withGymLeader("Clement")
                        .withEvilOrganisationPresent(true))));
        this.pokemonGames.add(pokemonGameAnnotated()
                .withGeneration(5)
                .withEvilTeam(evilTeam().withName("Plasma").withPrimaryColor("Grey"))
                .withCities(newArrayList(city()
                        .withName("Castelia City")
                        .withGymLeader("Burgh")
                        .withEvilOrganisationPresent(false))));
    }

    public static AnnotatedDb getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AnnotatedDb();
        }
        return INSTANCE;
    }

    public List<PokemonGameAnnotated> findByGen(int gen) {
        return pokemonGames.stream().filter(game -> game.generation == gen).collect(Collectors.toList());
    }

    public List<PokemonGameAnnotated> getAll() {
        return pokemonGames;
    }

    public PokemonGameAnnotated add(PokemonGameAnnotated newgame) {
        pokemonGames.add(newgame);
        return newgame;
    }
}
