package routing_datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CustomRoutingDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TargetDatasource.getTargetDatasource();
    }
}
