package by.rudenko.spring.integration.service;

import by.rudenko.spring.config.DatabaseProperties;
import by.rudenko.spring.dto.CompanyReadDto;
import by.rudenko.spring.integration.annotation.IT;
import by.rudenko.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationRunner.class,
initializers = ConfigDataApplicationContextInitializer.class)*/
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById(){
            var actualResult = companyService
                    .findById(COMPANY_ID);

            assertTrue(actualResult.isPresent());

            var expectedResult = new CompanyReadDto(COMPANY_ID);
            actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
        }
}
