package graphql.mapping;

import graphql.annotations.GraphQLField;

public class EvilTeam {

    @GraphQLField
    public String name;

    public String primaryColor;

    private EvilTeam(){}

    @GraphQLField
    public String primaryColor(String bla) {
        return primaryColor;
    }

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
