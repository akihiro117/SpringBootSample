package routing_datasource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
    private final GetNameMapper getNameMapper;

    public RestApiController(GetNameMapper getNameMapper) {
        this.getNameMapper = getNameMapper;
    }

    @GetMapping("/{database}/{id}")
    public ResponseEntity<String> index(@PathVariable("database") String database,
                                @PathVariable("id") Integer id) {
        String name = getNameMapper.findByPk(database, id);
        return ResponseEntity.ok("{\"name\", \"" + name + "\"}");
    }
}
