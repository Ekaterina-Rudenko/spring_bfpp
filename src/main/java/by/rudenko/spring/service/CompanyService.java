package by.rudenko.spring.service;

import by.rudenko.spring.database.repository.CompanyRepository;
import by.rudenko.spring.dto.CompanyReadDto;
import by.rudenko.spring.listener.entity.AccessType;
import by.rudenko.spring.listener.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity -> {
                  eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                  return new CompanyReadDto(entity.getId());
                });
    }
}
