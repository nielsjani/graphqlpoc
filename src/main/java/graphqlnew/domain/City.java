package graphqlnew.domain;

public class City {
    public String name;
    public boolean evilOrganisationPresent;
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
