package com.fedag.recruitmentSystem.service.utils;

import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.exception.WrongRoleTypeException;

public class MainUtilites {

    public static Role switchRoleToOpposite(Role role) {
        switch(role) {
            case ADMIN:
                return Role.ADMIN_INACTIVE;
            case USER:
                return Role.USER_INACTIVE;
            case COMPANY:
                return Role.COMPANY_INACTIVE;
            case USER_INACTIVE:
                return Role.USER;
            case ADMIN_INACTIVE:
                return Role.ADMIN;
            case COMPANY_INACTIVE:
                return Role.COMPANY;
            default:
                throw new WrongRoleTypeException("invalid role type");
        }
    }

    public static Role switchRoleToInactive(Role role) {
        switch(role) {
            case ADMIN:
                return Role.ADMIN_INACTIVE;
            case USER:
                return Role.USER_INACTIVE;
            case COMPANY:
                return Role.COMPANY_INACTIVE;
            case USER_INACTIVE:
                return role;
            case ADMIN_INACTIVE:
                return role;
            case COMPANY_INACTIVE:
                return role;
            default:
                throw new WrongRoleTypeException("invalid role type");
        }
    }
}
