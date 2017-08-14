package graphqlnew.rest.game;

import graphql.annotations.GraphQLField;

import java.io.Serializable;

public class AddCityDto implements Serializable {
    @GraphQLField
    public CityDto cityDto;
    @GraphQLField
    public int id;

    public AddCityDto() {
    }
}
