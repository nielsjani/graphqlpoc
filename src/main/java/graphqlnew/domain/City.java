package graphqlnew.domain;

import graphql.annotations.GraphQLField;

public class City {
    @GraphQLField
    public String name;
    @GraphQLField
    public boolean evilOrganisationPresent;
    @GraphQLField
    public String gymLeader;

    private City(){}

    public static City city() {
        return new City();
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }

    public City withEvilOrganisationPresent(boolean evilOrganisationPresent) {
        this.evilOrganisationPresent = evilOrganisationPresent;
        return this;
    }

    public City withGymLeader(String gymLeader) {
        this.gymLeader = gymLeader;
        return this;
    }
}
