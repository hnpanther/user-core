package com.hnp.usercore.service;


import com.hnp.usercore.dto.RoleCoreDTO;
import com.hnp.usercore.entity.RoleCore;
import com.hnp.usercore.mapper.RoleCoreMapper;
import com.hnp.usercore.repository.RoleCoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleCoreServiceTest {


    @Mock
    private RoleCoreRepository roleCoreRepository;
    @Mock
    private RoleCoreMapper roleCoreMapper;
    @Mock
    private PermissionCoreService permissionCoreService;

    @InjectMocks
    private RoleCoreServiceImpl roleCoreService;


    @Test
    void createRole_WithNoPermission_Success() {

        Long roleId = 1L;
        String roleName = "USER";

        RoleCoreDTO roleCoreDTO = new RoleCoreDTO();
        roleCoreDTO.setRoleName(roleName);

        RoleCore roleCore = new RoleCore();
        roleCore.setRoleName(roleName);

        RoleCore savedRoleCore = new RoleCore();
        savedRoleCore.setId(roleId);
        savedRoleCore.setRoleName(roleName);

        RoleCoreDTO savedRoleCoreDTO = new RoleCoreDTO();
        savedRoleCoreDTO.setId(roleId);
        savedRoleCoreDTO.setRoleName(roleName);

        when(roleCoreRepository.existsByRoleName(roleName)).thenReturn(Boolean.FALSE);
        when(roleCoreRepository.save(any())).thenReturn(savedRoleCore);
        when(roleCoreMapper.toDTO(savedRoleCore)).thenReturn(savedRoleCoreDTO);

        RoleCoreDTO role = roleCoreService.createRole(roleCoreDTO);
        assertNull(role.getPermissions());
        assertNotNull(role);
        assertEquals(roleName, role.getRoleName());
        assertEquals(roleId, role.getId());

    }
}
