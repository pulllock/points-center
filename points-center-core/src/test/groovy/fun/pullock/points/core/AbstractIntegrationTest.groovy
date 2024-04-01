package fun.pullock.points.core

import groovy.sql.Sql
import jakarta.annotation.Resource
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.sql.DataSource

/**
 * 集成测试需要继承此类，会启动Spring容器
 */
@SpringBootTest
abstract class AbstractIntegrationTest extends Specification {

    /**
     * 注入容器中的数据源，用来创建Groovy的Sql实例
     */
    @Resource
    DataSource dataSource

    /**
     * 使用Groovy的Sql来执行一些sql语句
     */
    Sql sql

    /**
     * 每个集成测试启动前都会实例化一个新的Sql对象，可使用该对象来直接操作数据库
     * @return
     */
    def setup() {
        sql = new Sql(dataSource)
    }
}