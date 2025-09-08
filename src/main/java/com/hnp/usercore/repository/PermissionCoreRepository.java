package com.hnp.usercore.repository;

import com.hnp.usercore.entity.PermissionCore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionCoreRepository extends JpaRepository<PermissionCore, Long> {

    Optional<PermissionCore> findByPermissionName(String permissionName);

    boolean existsByPermissionName(String permissionName);
}
