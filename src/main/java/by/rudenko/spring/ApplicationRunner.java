package by.rudenko.spring;

import by.rudenko.spring.config.ApplicationConfiguration;
import by.rudenko.spring.config.DatabaseProperties;
import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.database.repository.CrudRepository;
import by.rudenko.spring.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan //in order not to do @component of databaseproperties class
public class ApplicationRunner {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBeanDefinitionCount());
        System.out.println(context.getBean("pool1"));
        System.out.println(context.getBean(DatabaseProperties.class));
    }
}
