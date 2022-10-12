package by.rudenko.spring;

import by.rudenko.spring.config.ApplicationConfiguration;
import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.database.repository.CrudRepository;
import by.rudenko.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext();) {

            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);

            var companyService = context.getBean("companyService", CompanyService.class);
            System.out.println(companyService.findById(1));
        }

    }
}
