package graphqlnew.domain;

public class EvilTeam {

    public String name;
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
