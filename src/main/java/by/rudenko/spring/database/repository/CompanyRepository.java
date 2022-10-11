package by.rudenko.spring.database.repository;

import by.rudenko.spring.bpp.InjectBean.Auditing;
import by.rudenko.spring.bpp.InjectBean.Transaction;
import by.rudenko.spring.database.entity.Company;
import by.rudenko.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
@Transaction
@Auditing
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyRepository implements CrudRepository<Integer, Company>{

    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(ConnectionPool connectionPool,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.connectionPool = connectionPool;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init(){
        System.out.println("Init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("Find by id method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("Delete method...");
    }
}
