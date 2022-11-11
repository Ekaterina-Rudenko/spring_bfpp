package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.Role;
import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.database.querydsl.QPredicates;
import by.rudenko.spring.dto.PersonalInfo;
import by.rudenko.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static by.rudenko.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
            firstname,
            lastname,
            birth_date
            FROM users
            WHERE company_id = ?
            AND role = ? """;
    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?,
            role = ?
            WHERE id = ?
            """;

    private static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId,
            role = :role
            WHERE id = :id
            """;
    //динамическая имплементация запрсоов в зависисомтси от переданного фильтра и установленных полей в нём
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
                new JPAQuery<User>(entityManager)
                        .select(user)
                        .from(user)
                        .where(predicate)
                        .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()
                ), companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        var args = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public void updateCompanyAndRoleName(List<User> users) {
        var args = users.stream()
                .map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()
                )).map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);


        namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED,args
                );
    }
}
