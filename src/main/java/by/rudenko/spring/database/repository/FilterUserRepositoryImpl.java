package by.rudenko.spring.database.repository;

import by.rudenko.spring.database.entity.User;
import by.rudenko.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    //динамическая имплементация запрсоов в зависисомтси от переданного фильтра и установленных полей в нём
    private final EntityManager entityManager;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
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

        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }

}
