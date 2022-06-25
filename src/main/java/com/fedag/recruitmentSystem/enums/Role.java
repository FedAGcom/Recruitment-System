package com.fedag.recruitmentSystem.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USER)),
    ADMIN(Set.of(Permission.USER, Permission.ADMIN, Permission.COMPANY)),
    USER_INACTIVE(Set.of(Permission.NO_PERMISSIONS)),
    ADMIN_INACTIVE(Set.of(Permission.NO_PERMISSIONS)),
    COMPANY(Set.of(Permission.COMPANY)),
    COMPANY_INACTIVE(Set.of(Permission.NO_PERMISSIONS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
