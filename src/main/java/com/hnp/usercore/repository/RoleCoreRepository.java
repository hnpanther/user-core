package com.hnp.usercore.repository;

import com.hnp.usercore.entity.RoleCore;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleCoreRepository extends JpaRepository<RoleCore, Long> {

    Optional<RoleCore> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

    @EntityGraph(attributePaths = {"permissions"})
    Optional<RoleCore> findWithPermissionsByRoleName(String roleName);
}
