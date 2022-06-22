package com.fedag.recruitmentSystem.enums;

public enum Permission {
    NO_PERMISSIONS("NONE"),
    USER("USER"),
    ADMIN("ADMIN");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
