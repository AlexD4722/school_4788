package com.mechanics.school.contracts.interfaces.User;

import com.mechanics.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName (String user_name);
    User findByCode(String code);
    boolean existsByUserNameOrEmail(String userName, String email);
    User findByEmail(String email);
}
