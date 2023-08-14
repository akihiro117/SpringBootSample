package routing_datasource;

public class TargetDatasource {
    private static ThreadLocal<String> targetDatasource = new ThreadLocal<>();

    public static String getTargetDatasource() {
        return targetDatasource.get();
    }

    public static void setTargetDataSource(String datasource) {
        targetDatasource.set(datasource);
        System.out.println(targetDatasource.get());
    }
}
