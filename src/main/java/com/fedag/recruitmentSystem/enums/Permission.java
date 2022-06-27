package com.fedag.recruitmentSystem.enums;

public enum Permission {
    NO_PERMISSIONS("NONE"),
    USER("USER"),
    ADMIN("ADMIN"),
    COMPANY("COMPANY");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
