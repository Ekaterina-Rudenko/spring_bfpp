package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.Company;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {
    Optional<Company> findById(Integer id);

    void delete(Company entity);
}
