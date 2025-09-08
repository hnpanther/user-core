package com.hnp.usercore.mapper;

import com.hnp.usercore.dto.UserCoreDTO;
import com.hnp.usercore.entity.UserCore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleCoreMapper.class})
public interface UserCoreMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "rolesCore", source = "roles")
    UserCoreDTO toDTO(UserCore entity);

    @Mapping(target = "roles", source = "rolesCore")
    UserCore toEntity(UserCoreDTO dto);


}
