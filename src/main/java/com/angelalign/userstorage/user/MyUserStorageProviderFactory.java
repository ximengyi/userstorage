/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.angelalign.userstorage.user;


import com.angelalign.userstorage.config.CustomConfigLoader;
import com.angelalign.userstorage.dao.RbacAccountDao;
import com.angelalign.userstorage.dao.UserDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.sql.DataSource;
import java.util.Objects;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */

public class MyUserStorageProviderFactory implements UserStorageProviderFactory<MyUserStorageProvider> {
    public static final String PROVIDER_ID = "iortho-user-storage-jpa";

    private static final Logger logger = Logger.getLogger(MyUserStorageProviderFactory.class);

    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;
    private  UserDao userDao;

   @Resource
   RbacAccountDao rbacAccountDao;

    @Override
    public MyUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        if(Objects.isNull(rbacAccountDao)){
            logger.info("rbacAccountDao is null");

        }else{
            logger.info("rbacAccountDao is not null");
        }

       try{

           if(dataSource == null){
               setDataSource();
               logger.info("setDataSource done");
           }

//           SqlSession sqlSession = sqlSessionFactory.openSession();
//           rbacAccountDao = sqlSession.getMapper(RbacAccountDao.class);

           if(Objects.isNull(rbacAccountDao)){
               logger.info("rbacAccountDao is  null after sql session");

           }else{
               logger.info("rbacAccountDao is not null after sql session");
           }

           if(userDao ==null){

               userDao = new UserDao(dataSource,"cn");
           }


       }catch (Exception e){
           logger.error("get custom load fail{}:",e);
       }

        if(Objects.isNull(userDao)){

            logger.info("userDao is  null after sql set");

        }else{

            logger.info("userDao is not null after sql set");
        }

        return new MyUserStorageProvider(session, model,userDao);

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "JPA Example User Storage Provider";
    }

    @Override
    public void close() {
        logger.info("<<<<<< Closing factory");

    }


    public void setDataSource() {

//        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/iortho?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
//        String username = "rootadmin";
//        String password = "rootmeng";
        String driverClassName = "com.mysql.cj.jdbc.Driver"; // 这里假设您使用的是MySQL
        // 配置HikariCP连接池
        String jdbcUrl =  CustomConfigLoader.getPropertyValue("datasource");
        String username = CustomConfigLoader.getPropertyValue("username");
        String password = CustomConfigLoader.getPropertyValue("password");

        logger.info("<<<<<< jdbcUrl:"+jdbcUrl);
        logger.info("<<<<<< username:"+username);
        logger.info("<<<<<< password:"+password);
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        // 创建数据源
        this.dataSource = new HikariDataSource(hikariConfig);
    }

    public void sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 设置Mapper XML文件的位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(resources);
        this.sqlSessionFactory = sessionFactory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.angelalign.userstorage.dao");
        return mapperScannerConfigurer;
    }
}
