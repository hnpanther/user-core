package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;

import java.util.List;
import java.util.Optional;

public interface PermissionCoreService {

    PermissionCoreDTO createPermission(PermissionCoreDTO dto);
    List<PermissionCoreDTO> createPermissions(List<PermissionCoreDTO> dtos);
    Optional<PermissionCoreDTO> getPermissionById(Long id);
    Optional<PermissionCoreDTO> getPermissionByName(String name);

    PermissionCoreDTO updatePermission(PermissionCoreDTO dto);
}
