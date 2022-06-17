package com.fedag.recruitmentSystem.utilites;

import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.exception.WrongRoleTypeException;

public class MainUtilites {

    public static Role switchRoleToOpposite(Role role) {
        switch(role) {
            case ADMIN:
                return Role.ADMIN_INACTIVE;
            case USER:
                return Role.USER_INACTIVE;
            case USER_INACTIVE:
                return Role.USER;
            case ADMIN_INACTIVE:
                return Role.ADMIN;
            default:
                throw new WrongRoleTypeException("invalid role type");
        }
    }
}
