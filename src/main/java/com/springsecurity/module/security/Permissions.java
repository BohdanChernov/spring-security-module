package com.springsecurity.module.security;

public enum Permissions {
    CUSTOMER_WRITE("customer:write"),
    CUSTOMER_READ("customer:read"),
    ORDER_WRITE("order:write"),
    ORDER_READ("order:read"),
    DISH_WRITE("dish:write"),
    DISH_READ("dish:read");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
