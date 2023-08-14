package routing_datasource;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum DatasourceEnum {
    DATASOURCE1("datasource1", "db1"),
    DATASOURCE2("datasource2", "db2"),
    ;

    private String datasourceName;
    private String databaseName;

    DatasourceEnum(String datasourceName, String databaseName) {
        this.datasourceName = datasourceName;
        this.databaseName = databaseName;
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static DatasourceEnum getEnum(String databaseName) {
        return Arrays.stream(values())
                .filter(datasourceEnum -> datasourceEnum.getDatabaseName().equals(databaseName))
                .findFirst().orElseThrow();
    }
}
