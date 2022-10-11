package by.rudenko.spring.service;

import by.rudenko.spring.database.entity.Company;
import by.rudenko.spring.database.repository.CompanyRepository;
import by.rudenko.spring.database.repository.CrudRepository;
import by.rudenko.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
