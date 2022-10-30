package by.rudenko.spring.integration.database.repository;

import by.rudenko.spring.database.repository.UserRepository;
import by.rudenko.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkQueries(){
        var users = userRepository.findAllBy("a", "ov");
        System.out.println(users);
    }

}