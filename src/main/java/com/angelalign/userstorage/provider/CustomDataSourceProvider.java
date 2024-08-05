package com.angelalign.userstorage.provider;

import com.angelalign.userstorage.config.CustomConfigLoader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


public class CustomDataSourceProvider {

    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;
    private CustomConfigLoader configLoader;

    public CustomDataSourceProvider() throws Exception {

        this.configLoader = new CustomConfigLoader();
        setDataSource();
        sqlSessionFactory(dataSource);

    }


    public void setDataSource() {

        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/iortho?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
        String username = "rootadmin";
        String password = "rootmeng";
        String driverClassName = "com.mysql.cj.jdbc.Driver"; // 这里假设您使用的是MySQL
        // 配置HikariCP连接池
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        // 创建数据源
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    public void sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 设置Mapper XML文件的位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(resources);
        this.sqlSessionFactory = sessionFactory.getObject();
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
