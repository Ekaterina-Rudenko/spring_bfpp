package by.rudenko.spring.config;

import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.database.repository.CrudRepository;
import by.rudenko.spring.database.repository.UserRepository;
import by.rudenko.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.*;
import org.springframework.stereotype.Component;

@Import(WebConfig.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "by.rudenko.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })
public class ApplicationConfiguration {

        @Bean("pool2")
        @Scope(BeanDefinition.SCOPE_SINGLETON)
        public ConnectionPool pool2(@Value("${db.username}") String username){
                return new ConnectionPool(username, 20);
        }
        @Bean
        public ConnectionPool pool3(){
                return new ConnectionPool("test-pool", 25);
        }

        @Bean
        @Profile("prod")
        public UserRepository userRepository2(ConnectionPool pool2){
                return new UserRepository(pool2);
        }
        @Bean
        public UserRepository userRepository3(){
                var connectionPool = pool3();
                var connectionPool2 = pool3();
                var connectionPool3 = pool3();
                return new UserRepository(pool3());
        }
}
