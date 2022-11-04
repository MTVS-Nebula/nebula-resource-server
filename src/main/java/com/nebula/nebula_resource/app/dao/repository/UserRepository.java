package com.nebula.nebula_resource.app.dao.repository;

import com.nebula.nebula_resource.app.dao.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
