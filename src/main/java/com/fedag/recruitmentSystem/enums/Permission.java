package com.fedag.recruitmentSystem.enums;

public enum Permission {
    NO_PERMISSIONS("NONE"),
    DEVELOPERS_READ("READ"),
    DEVELOPERS_WRITE("WRITE");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
