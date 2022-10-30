package by.rudenko.spring.config;

import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfig.class)
@Configuration(proxyBeanMethods = true)
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
}
