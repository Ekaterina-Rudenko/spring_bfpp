package by.rudenko.spring;

import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.database.repository.CompanyRepository;
import by.rudenko.spring.database.repository.UserRepository;
import by.rudenko.spring.ioc.Container;
import by.rudenko.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {
    public static void main(String[] args) {
        try(var context = new ClassPathXmlApplicationContext("application.xml");){
            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool);
            var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);
        }

    }
}
