package miniproject.onlib.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import liquibase.integration.spring.SpringLiquibase;
import miniproject.onlib.repository.BookRepository;
import miniproject.onlib.service.BookService;
import miniproject.onlib.service.impl.BookServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public BookService bookService() {
		return new BookServiceImpl();
	}
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer(ConcurrentTaskExecutor concurrentTaskExecutor) {
	    return new WebMvcConfigurer() {
	        @Override
	        public void configureAsyncSupport(@NonNull AsyncSupportConfigurer configurer) {
	            configurer.setDefaultTimeout(-1);
	            configurer.setTaskExecutor(concurrentTaskExecutor);
	        }
	    };
	}
	
	@Bean
	public ConcurrentTaskExecutor concurrentTaskExecutor() {
	    return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(5, new ThreadFactory() {
	        private final AtomicInteger threadCounter = new AtomicInteger(0);
	        @Override
	        public Thread newThread(@NonNull Runnable runnable) {
	            return new Thread(runnable, "asyncThread-" + threadCounter.incrementAndGet());
	        }
	    }));
	}

    @Bean
    public DataSource dataSource()  {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/onlib");
        dataSource.setUsername( "root" );
        dataSource.setPassword( "root" );

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase()  {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db.changelog-master.xml");

        return liquibase;
    }
}
