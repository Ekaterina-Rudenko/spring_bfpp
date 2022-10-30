package by.rudenko.spring.service;

import by.rudenko.spring.database.repository.CompanyRepository;
import by.rudenko.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
