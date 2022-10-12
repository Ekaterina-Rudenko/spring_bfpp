package by.rudenko.spring;

import by.rudenko.spring.config.ApplicationConfiguration;
import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext();) {

            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }

    }
}
