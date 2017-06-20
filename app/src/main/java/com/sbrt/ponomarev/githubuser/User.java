package com.sbrt.ponomarev.githubuser;

/**
 * Created by Ponomarev on 24.05.2017.
 */

public class User {

    private long mId;
    private String mName;
    private String mEmail;
    private String mLocation;

    public User(long id, String name, String email, String location) {
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
        this.mLocation = location;
    }

    public long getID() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getLocation() {
        return mLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (mId != user.mId) return false;
        if (mName != null ? !mName.equals(user.mName) : user.mName != null) return false;
        if (mEmail != null ? !mEmail.equals(user.mEmail) : user.mEmail != null) return false;
        return mLocation != null ? mLocation.equals(user.mLocation) : user.mLocation == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (mLocation != null ? mLocation.hashCode() : 0);
        return result;
    }
}
