package graphqlnew.rest.game;

import graphql.annotations.GraphQLField;

import java.io.Serializable;

public class EvilTeamDto implements Serializable {

    @GraphQLField
    public String name;
    @GraphQLField
    public String primaryColor;

    public EvilTeamDto() {
    }
}
