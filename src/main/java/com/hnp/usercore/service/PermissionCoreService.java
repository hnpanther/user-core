package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.entity.PermissionCore;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionCoreService {

    PermissionCoreDTO createPermission(PermissionCoreDTO dto);
    List<PermissionCoreDTO> createPermissions(List<PermissionCoreDTO> dtos);
    Optional<PermissionCoreDTO> getPermissionById(Long id);
    Optional<PermissionCoreDTO> getPermissionByName(String name);
    List<PermissionCoreDTO> getAllPermissions();
    Set<PermissionCore> findAllPermissionsByIds(Set<Long> ids);
    PermissionCoreDTO updatePermission(PermissionCoreDTO dto);
}
