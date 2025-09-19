package com.hnp.usercore.service;

import com.hnp.usercore.dto.RoleCoreDTO;
import com.hnp.usercore.entity.PermissionCore;
import com.hnp.usercore.entity.RoleCore;
import com.hnp.usercore.exception.DuplicateDataException;
import com.hnp.usercore.exception.InvalidDataException;
import com.hnp.usercore.mapper.RoleCoreMapper;
import com.hnp.usercore.repository.RoleCoreRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class RoleCoreServiceImpl implements RoleCoreService {

    private static final Logger logger = LoggerFactory.getLogger(RoleCoreServiceImpl.class);


    private final RoleCoreRepository roleCoreRepository;
    private final PermissionCoreService permissionCoreService;
    private final RoleCoreMapper roleCoreMapper;

    public RoleCoreServiceImpl(RoleCoreRepository roleCoreRepository, PermissionCoreService permissionCoreService, RoleCoreMapper roleCoreMapper) {
        this.roleCoreRepository = roleCoreRepository;
        this.permissionCoreService = permissionCoreService;
        this.roleCoreMapper = roleCoreMapper;
    }

    @Override
    public RoleCoreDTO createRole(RoleCoreDTO roleCoreDTO) {
        logger.info("===> createRole with {}", roleCoreDTO);
        if(roleCoreDTO == null || roleCoreDTO.getRoleName() == null || roleCoreDTO.getRoleName().isBlank()) {
            logger.info("===> roleCoreDTO is null or roleName is blank");
            throw new InvalidDataException("Role Name is Null");
        }

        if(roleCoreRepository.existsByRoleName(roleCoreDTO.getRoleName())) {
            logger.info("===> roleCoreDTO is already exists: {}", roleCoreDTO);
            throw new DuplicateDataException("Role Name already exists");
        }

        Set<PermissionCore> allPermissionsByIds = null;
        if(roleCoreDTO.getPermissionsId() == null || roleCoreDTO.getPermissionsId().isEmpty()) {
            logger.info("===> roleCoreDTO.getPermissionsId() is null");
        } else {
            allPermissionsByIds = permissionCoreService.findAllPermissionsByIds(roleCoreDTO.getPermissionsId());

            if(allPermissionsByIds.size() != roleCoreDTO.getPermissionsId().size()) {
                logger.info("===> allPermissionsByIds are not same size");
                throw new InvalidDataException("All permissions are not same size");
            }
        }


        RoleCore roleCore = new RoleCore();
        roleCore.setRoleName(roleCoreDTO.getRoleName());
        roleCore.setDescription(roleCoreDTO.getDescription());
        roleCore.setPermissions(allPermissionsByIds);


        RoleCore savedRole = roleCoreRepository.save(roleCore);
        RoleCoreDTO dto = roleCoreMapper.toDTO(savedRole);
        logger.info("===> savedRole {}", savedRole);
        return dto;
    }

    @Override
    public RoleCoreDTO getRoleById(Long id) {
        return null;
    }

    @Override
    public RoleCoreDTO getRoleByName(String roleName) {
        return null;
    }

    @Override
    public List<RoleCoreDTO> getAllRoles() {
        return List.of();
    }

    @Override
    public RoleCoreDTO updateRole(RoleCoreDTO roleCoreDTO) {
        return null;
    }

    @Override
    public void deleteRoleById(Long id) {

    }
}
