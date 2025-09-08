package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;

import java.util.List;

public interface PermissionCoreService {

    PermissionCoreDTO createPermission(PermissionCoreDTO dto);
    List<PermissionCoreDTO> createPermissions(List<PermissionCoreDTO> dtos);
    PermissionCoreDTO getPermissionById(Long id);
    PermissionCoreDTO getPermissionByName(String name);
}
