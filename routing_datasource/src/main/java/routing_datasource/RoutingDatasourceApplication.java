package routing_datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("routing_datasource")
public class RoutingDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutingDatasourceApplication.class, args);
	}

}
