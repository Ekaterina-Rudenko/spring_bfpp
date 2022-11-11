package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.Role;
import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.dto.PersonalInfo;
import by.rudenko.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

    //to get dto. JDBC doesnt use hibrnate and it persist context
    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List <User> users);
    void updateCompanyAndRoleName(List <User> users);

}
