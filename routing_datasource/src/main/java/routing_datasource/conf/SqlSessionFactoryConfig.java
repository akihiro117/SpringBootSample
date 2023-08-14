package routing_datasource.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import routing_datasource.CustomRoutingDatasource;

@Configuration
public class SqlSessionFactoryConfig {
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(CustomRoutingDatasource customRoutingDatasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(customRoutingDatasource);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
}
