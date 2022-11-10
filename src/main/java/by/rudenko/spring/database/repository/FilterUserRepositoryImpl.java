package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.database.querydsl.QPredicates;
import by.rudenko.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static by.rudenko.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    //динамическая имплементация запрсоов в зависисомтси от переданного фильтра и установленных полей в нём
    private final EntityManager entityManager;
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
}
