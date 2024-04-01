package fun.pullock.points.core

import fun.pullock.points.core.config.DataSourceConfig
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import spock.lang.Specification

/**
 * 测试Mybatis Mapper层使用
 *
 * 继承该类来测试Mybatis Mapper层，只会创建数据源以及Mapper等相关的Bean
 */
@MybatisTest
// 使用真实的数据库
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// 导入数据库配置，主要用来扫描Mapper
@Import(DataSourceConfig.class)
class AbstractMapperIntegrationSpec extends Specification {
}
