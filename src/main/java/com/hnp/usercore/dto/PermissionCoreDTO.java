package com.hnp.usercore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionCoreDTO {

    private Long id;

    @NotBlank
    private String permissionName;

    private String description;
}
