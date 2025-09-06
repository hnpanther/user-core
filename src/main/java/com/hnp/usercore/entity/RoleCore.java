package com.hnp.usercore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role_core",
        uniqueConstraints = @UniqueConstraint(columnNames = "role_name"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserCore> users = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "permission_role_core",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionCore> permissions = new HashSet<>();
}
