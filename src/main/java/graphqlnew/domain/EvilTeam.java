package graphqlnew.domain;

import graphql.annotations.GraphQLField;

public class EvilTeam {
    @GraphQLField
    public String name;
    @GraphQLField
    public String primaryColor;

    private EvilTeam(){}

    public static EvilTeam evilTeam() {
        return new EvilTeam();
    }

    public EvilTeam withName(String name) {
        this.name = name;
        return this;
    }

    public EvilTeam withPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }
}
