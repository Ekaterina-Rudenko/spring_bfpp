package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.Role;
import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.database.querydsl.QPredicates;
import by.rudenko.spring.dto.PersonalInfo;
import by.rudenko.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;

import static by.rudenko.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id = ?
            AND role = ? """;
    //динамическая имплементация запрсоов в зависисомтси от переданного фильтра и установленных полей в нём
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
       /* var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);

        var user = criteria.from(User.class);
        criteria.select(user);
        List<Predicate> predicates = new ArrayList<>();

        if(filter.firstname() != null){
           predicates.add(cb.like(user.get("firstname"), filter.firstname()));
        }

        if(filter.lastname() != null){
            predicates.add(cb.like(user.get("lastname"), filter.lastname()));
        }

        if(filter.birthDate() != null){
            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
        }

        criteria.where(predicates.toArray(Predicate[]::new));*/


        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return /*entityManager.createQuery(criteria).getResultList()*/
                new JPAQuery<User> (entityManager)
                        .select(user)
                        .from(user)
                        .where(predicate)
                        .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role){
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                (rs,rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ), companyId, role.name());
    }
}
