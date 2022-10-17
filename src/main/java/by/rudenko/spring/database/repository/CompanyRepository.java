package by.rudenko.spring.database.repository;

import by.rudenko.spring.bpp.InjectBean.Auditing;
import by.rudenko.spring.bpp.InjectBean.Transaction;
import by.rudenko.spring.database.entity.Company;
import by.rudenko.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
@Transaction
@Auditing
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company>{

    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private final Integer poolSize;
    @PostConstruct
    private void init(){
        log.info("Init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("Find by id method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        log.info("Delete method...");
    }
}
