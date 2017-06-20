package com.sbrt.ponomarev.githubuser.utils;

import com.sbrt.ponomarev.githubuser.User;
import org.junit.rules.ExternalResource;

/**
 * Created by Ponomarev on 20.06.2017.
 */

public class UserRule extends ExternalResource {

    private static final String NAME = "Ivanov Ivan";
    private static final String EMAIL = "example@mail.com";
    private static final long USER_ID = 12345678L;
    private static final String LOCATION = "USA";

    private final User mUser;

    public UserRule() {
        mUser = new User(USER_ID, NAME, EMAIL, LOCATION);
    }

    public User getUser() {
        return mUser;
    }
}
