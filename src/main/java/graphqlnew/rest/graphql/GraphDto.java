package graphqlnew.rest.graphql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Maps.newHashMap;

public class GraphDto implements Serializable {

    public String query;
    public List<KeyValueDto> keyValueDto;

    public GraphDto(){}

    public Map<String, Object> getArgs() {
        if(keyValueDto == null){
            return newHashMap();
        }
        return keyValueDto.stream().collect(Collectors.toMap(dto -> dto.key, dto -> dto.value));
    }


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
