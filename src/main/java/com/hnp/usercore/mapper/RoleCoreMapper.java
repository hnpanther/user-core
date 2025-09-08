package com.hnp.usercore.mapper;

import com.hnp.usercore.dto.RoleCoreDTO;
import com.hnp.usercore.entity.RoleCore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PermissionCoreMapper.class})
public interface RoleCoreMapper {

    @Mapping(target = "permissions", source = "permissions")
    RoleCoreDTO toDTO(RoleCore entity);

    @Mapping(target = "permissions", source = "permissions")
    RoleCore toEntity(RoleCoreDTO dto);
}
