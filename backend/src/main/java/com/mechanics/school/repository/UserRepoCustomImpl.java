package com.mechanics.school.repository;

import com.mechanics.school.contracts.interfaces.User.IUserRepositoryCustom;
import com.mechanics.school.exception.EntityNotFoundException;
import com.mechanics.school.mapper.dtos.auth.AuthUserDto;
import com.mechanics.school.model.*;
import com.mechanics.school.utils.enums.Status;
import com.mechanics.school.utils.LoggerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepoCustomImpl implements IUserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

            @Override
            public AuthUserDto findUserByUser_name(String userName) {
                try {
                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Tuple> query = cb.createTupleQuery();
                    Root<User> root = query.from(User.class);

            // Join User with UserRole
            Join<User, UserRole> userRoleJoin = root.join("userRoles", JoinType.LEFT);

            // Join UserRole with Role
            Join<UserRole, Role> roleJoin = userRoleJoin.join("role", JoinType.LEFT);

            //query
            query.multiselect(
                    root.get("userName").alias("userName"),
                    root.get("password").alias("password"),
                    roleJoin.get("name").alias("roleName")
            );
            query.where(cb.and(cb.equal(root.get("userName"), userName), cb.equal(root.get("status"), Status.ACTIVE.getValue())));
            query.distinct(true); // to avoid duplicate results

            // Execute the query and get the result list
            List<Tuple> results = entityManager.createQuery(query).getResultList();
            // Check the size of the result list
            if (results.isEmpty() || results.get(0).get("userName", String.class) == null) {
                    throw new EntityNotFoundException("Expected one result but found " + results.size());
            }
            // Convert the result list to a AuthUserDto
            AuthUserDto authUserDto = new AuthUserDto();
            authUserDto.setUserName(results.get(0).get("userName", String.class));
            authUserDto.setRoles(results.stream()
                    .map(tuple -> tuple.get("roleName", String.class))
                    .collect(Collectors.toList()));
            authUserDto.setPassword(results.get(0).get("password", String.class));
            return authUserDto;
        } catch (Exception e) {
            LoggerUtils.LOGGER.error("Error occurred during finding  entity by id.", e);
            throw new EntityNotFoundException("Error occurred during finding entity by id.");
        }
    }

}
