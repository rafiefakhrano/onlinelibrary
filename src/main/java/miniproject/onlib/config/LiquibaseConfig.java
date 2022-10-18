//package miniproject.onlib.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import liquibase.integration.spring.SpringLiquibase;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class LiquibaseConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource()  {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//            dataSource.setDriverClassName(env.getProperty("spring.datasource.drivername"));
//            dataSource.setUrl(env.getProperty("spring.datasource.url"));
//            dataSource.setUsername(env.getProperty("spring.datasource.username"));
//            dataSource.setPassword(env.getProperty("spring.datasource.password"));
//
//        return dataSource;
//    }
//
//    @Bean
//    public SpringLiquibase liquibase()  {
//        SpringLiquibase liquibase = new SpringLiquibase();
//
//        liquibase.setDataSource(dataSource());
//        liquibase.setChangeLog("classpath:db.changelog-master.xml");
//
//        return liquibase;
//    }
//}
