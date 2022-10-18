package by.rudenko.spring.integration.service;

import by.rudenko.spring.database.pool.ConnectionPool;
import by.rudenko.spring.integration.annotation.IT;
import by.rudenko.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;


    @Test
    void test(){
        System.out.println();
    }
}
