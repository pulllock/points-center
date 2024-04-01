package fun.pullock.points.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"fun.pullock.points.core.dao.mapper"})
public class DataSourceConfig {
}
