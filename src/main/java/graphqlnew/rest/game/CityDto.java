package graphqlnew.rest.game;

import graphql.annotations.GraphQLField;

import java.io.Serializable;

public class CityDto implements Serializable {
    @GraphQLField
    public String name;
    @GraphQLField
    public boolean evilOrganisationPresent;
    @GraphQLField
    public String gymLeader;

    public CityDto() {
    }

}
