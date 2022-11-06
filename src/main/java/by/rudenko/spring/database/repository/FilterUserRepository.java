package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}