package com.hnp.usercore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCoreDTO {

    private Long id;

    @NotBlank
    private String roleName;

    private String description;

    private Set<Long> permissionsId;

    private Set<PermissionCoreDTO> permissions;
}
