package com.hnp.usercore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission_core",
        uniqueConstraints = @UniqueConstraint(columnNames = "permission_name"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission_name", nullable = false, length = 100)
    private String permissionName;

    @Column(length = 1500)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleCore> roles = new HashSet<>();
}
