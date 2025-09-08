package com.hnp.usercore.service;

import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.mapper.PermissionCoreMapper;
import com.hnp.usercore.repository.PermissionCoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PermissionsServiceImpl implements PermissionCoreService {

    private final PermissionCoreRepository permissionCoreRepository;
    private final PermissionCoreMapper permissionCoreMapper;

    public PermissionsServiceImpl(PermissionCoreRepository permissionCoreRepository, PermissionCoreMapper permissionCoreMapper) {
        this.permissionCoreRepository = permissionCoreRepository;
        this.permissionCoreMapper = permissionCoreMapper;
    }


    @Override
    public PermissionCoreDTO createPermission(PermissionCoreDTO dto) {
        return null;
    }

    @Override
    public List<PermissionCoreDTO> createPermissions(List<PermissionCoreDTO> dtos) {
        return List.of();
    }

    @Override
    public PermissionCoreDTO getPermissionById(Long id) {
        return null;
    }

    @Override
    public PermissionCoreDTO getPermissionByName(String name) {
        return null;
    }
}
