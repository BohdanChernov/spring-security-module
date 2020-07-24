package com.springsecurity.module.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Sets.newHashSet(Permissions.DISH_WRITE, Permissions.DISH_READ,
            Permissions.CUSTOMER_WRITE, Permissions.CUSTOMER_READ)),
    CUSTOMER(Sets.newHashSet(Permissions.DISH_READ,
            Permissions.CUSTOMER_WRITE, Permissions.CUSTOMER_READ,
            Permissions.ORDER_WRITE, Permissions.ORDER_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
