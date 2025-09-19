package com.hnp.usercore.service;

import com.hnp.usercore.dto.RoleCoreDTO;

import java.util.List;

public interface RoleCoreService {

    RoleCoreDTO createRole(RoleCoreDTO roleCoreDTO);
    RoleCoreDTO getRoleById(Long id);
    RoleCoreDTO getRoleByName(String roleName);
    List<RoleCoreDTO> getAllRoles();
    RoleCoreDTO updateRole(RoleCoreDTO roleCoreDTO);
    void deleteRoleById(Long id);


}
