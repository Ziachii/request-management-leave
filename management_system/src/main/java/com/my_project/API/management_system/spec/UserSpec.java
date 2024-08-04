package com.my_project.API.management_system.spec;

import com.my_project.API.management_system.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSpec implements Specification<User> {

    private final UserFilter userFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<User> user, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(userFilter.getUsername() != null) {
            Predicate predicate = cb.like(cb.upper(user.get("username")),  userFilter.getUsername().toUpperCase() + "%");
            predicates.add(predicate);
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
