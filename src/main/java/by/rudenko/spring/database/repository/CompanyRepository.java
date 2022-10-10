package by.rudenko.spring.database.repository;

import by.rudenko.spring.bpp.InjectBean.Auditing;
import by.rudenko.spring.bpp.InjectBean.InjectBean;
import by.rudenko.spring.bpp.InjectBean.Transaction;
import by.rudenko.spring.database.entity.Company;
import by.rudenko.spring.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.Optional;
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{
    @InjectBean
    private ConnectionPool connectionPool;
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
