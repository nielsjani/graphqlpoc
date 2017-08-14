package graphqlnew.rest.graphql;

import java.io.Serializable;

public class KeyValueDto implements Serializable{
    public String key;
    public Object value;

    public KeyValueDto(){}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
