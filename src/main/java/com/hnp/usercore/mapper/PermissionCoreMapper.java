package com.hnp.usercore.mapper;


import com.hnp.usercore.dto.PermissionCoreDTO;
import com.hnp.usercore.entity.PermissionCore;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionCoreMapper {

    PermissionCoreDTO toDTO(PermissionCore entity);

    PermissionCore toEntity(PermissionCoreDTO dto);

}
