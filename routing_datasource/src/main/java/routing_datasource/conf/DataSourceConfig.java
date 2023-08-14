package routing_datasource.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import routing_datasource.CustomRoutingDatasource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean(name = "datasourceProperties1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties dataSourceProperties1() {
        return new DataSourceProperties();
    }

    @Bean(name = "datasource1")
    public DataSource dataSource1(@Qualifier("datasourceProperties1") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "datasourceProperties2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties dataSourceProperties2() {
        return new DataSourceProperties();
    }

    @Bean(name = "datasource2")
    public DataSource dataSource2(@Qualifier("datasourceProperties2") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public CustomRoutingDatasource customRoutingDatasource(@Qualifier("datasource1") DataSource dataSource1,
                                                           @Qualifier("datasource2") DataSource dataSource2) {
        Map<Object, Object> targetDatasourceMap = new HashMap<>();
        targetDatasourceMap.put("datasource1", dataSource1);
        targetDatasourceMap.put("datasource2", dataSource2);

        CustomRoutingDatasource customRoutingDatasource = new CustomRoutingDatasource();
        customRoutingDatasource.setTargetDataSources(targetDatasourceMap);
        customRoutingDatasource.setDefaultTargetDataSource(dataSource1);

        return customRoutingDatasource;
    }
}
