package graphqlnew.rest;

public class PokemonResourceTest {
    //-------------------------------------------------
    //QUERY TESTS
    //-------------------------------------------------
    /*
    Q-TEST1: Simple query
    URL: localhost:8080/api (PUT)
    BODY:
    {
	    "query": "{games {generation, cities {name, gymLeader}}}"
    }

    GIVEN: give me all the games. The fields I want to see are 'generation' and from the 'cities' list I want the fields 'name' and 'gymLeader'
    EXPECTED: all the games in the 'AnnotatedDB' (2 by default) but only the fields mentioned above
    */

    /*
    Q-TEST2: Query with argument (graphql filter)
    URL: localhost:8080/api (PUT)
    BODY:
    {
	    "query": "{games(generationmax:5) {generation, evilTeam {name}, cities {name, gymLeader}}}"
    }

    GIVEN: give me all the games whose generation field <= 5. The fields I want to see are 'generation', the 'name' from the 'evilTeam' and from the 'cities' list I want the fields 'name' and 'gymLeader'
    EXPECTED: all the games in the 'AnnotatedDB' whose generation <= 5 (1 by default) but only the fields mentioned above
    */

    /*
    Q-TEST3: Query with argument (graphql filter with separate arguments object)
    URL: localhost:8080/api (PUT)
    BODY:
    {
        "query": "query GetByGen($gen: Int) {games(generationmax:$gen) {generation, evilTeam {name}, cities {name, gymLeader}}}",
        "keyValueDto": [{"key": "gen", "value": "5"}]
    }

    GIVEN: same as TEST2, but the 'generationmax' is passed as a variable called '$gen'
    EXPECTED: same as TEST2
    */


    //-------------------------------------------------
    //MUTATE TESTS
    //-------------------------------------------------
    /*
    Q-TEST1: Adding a new game
    URL: localhost:8080/api (POST)
    BODY:
    {
        "query": "mutation addgame($newgame: inputPokemonGameDto) {creategame(newgame: $newgame){generation, evilTeam{primaryColor}}}",
        "keyValueDto": [{
            "key": "newgame",
            "value": {
                "generation": 2,
                "cities": [{
                        "name": "Goldenrod",
                        "evilOrganisationPresent": true,
                        "gymLeader": "Whitney"
                    },
                    {
                        "name": "Ecruteak",
                        "evilOrganisationPresent": false,
                        "gymLeader": "Morty"
                    }
                ],
                "evilTeam": {
                    "name": "Rocket",
                    "primaryColor": "Black"
                }
            }
        }]
    }

    GIVEN: calling a mutation called 'creategame' with a parameter called '$newgame'.
    EXPECTED: This mutation should add the 'newgame' and return its 'generation' and 'evilTeam' (only 'primaryColor') fields. You can check if it was really added by doing Q-TEST1 again.
    */

}
