package com.codegroup.challenger.constants;

import java.util.*;

/**
 * Created by Max on 25.01.2015.
 */
public class UserRoles {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

    public static final int ROLE_USER_ID = 1;
    public static final int ROLE_ADMIN_ID = 2;
    public static final int ROLE_USER_ADMIN_ID = 3;
    public static final int ROLE_MODERATOR_ID = 4;
    public static final int ROLE_USER_MODERATOR_ID = 5;
    public static final int ROLE_ADMIN_MODERATOR_ID = 6;
    public static final int ROLE_USER_ADMIN_MODERATOR_ID = 7;

    public static final Set<String> getSetOfRolesById(int id) {
        Set<String> set = new HashSet<String>();
        if ((id&1)!=0) set.add(ROLE_USER);
        if ((id&2)!=0) set.add(ROLE_ADMIN);
        if ((id&3)!=0) set.add(ROLE_MODERATOR);
        return set;
    }
}
