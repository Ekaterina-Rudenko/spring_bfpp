package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //Optional, Entity, Future
    Optional<Company> findByName(String name);//jpa создаст критерий апиай во время поднятия спринг контекста

    //Collection, Stream(batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);



}
