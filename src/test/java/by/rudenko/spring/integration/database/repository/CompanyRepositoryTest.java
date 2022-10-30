package by.rudenko.spring.integration.database.repository;

import by.rudenko.spring.database.entity.Company;
import by.rudenko.spring.database.repository.CompanyRepository;
import by.rudenko.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {
    private static final Integer APPLE_ID = 4;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void delete() {
        var mayBeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(mayBeCompany.isPresent());
        mayBeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void checkFindByQueries(){
        companyRepository.findByName("google");
        companyRepository.findAllByNameContainingIgnoreCase("a");

    }
    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(
                tx -> {
                    var company = entityManager.find(Company.class, 1);
                    assertNotNull(company);
                    Assertions.assertThat(company.getLocales()).hasSize(2);
                }
        );
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}