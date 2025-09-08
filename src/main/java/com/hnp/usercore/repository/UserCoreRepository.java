package com.hnp.usercore.repository;

import com.hnp.usercore.entity.UserCore;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCoreRepository extends JpaRepository<UserCore, Integer> {

    Optional<UserCore> findByUsername(String username);

    Optional<UserCore> findByEmail(String email);

    Optional<UserCore> findByPhoneNumber(String phoneNumber);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);


    @EntityGraph(attributePaths = {"roles", "roles.permissions"})
    Optional<UserCore> findWithRolesAndPermissionsByUsername(String username);
}
