package routing_datasource;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface GetNameMapper {

    @Select({
            "SELECT name FROM ${database}.table1 WHERE id=#{id}"
    })
    String findByPk(String database, Integer id);
}
