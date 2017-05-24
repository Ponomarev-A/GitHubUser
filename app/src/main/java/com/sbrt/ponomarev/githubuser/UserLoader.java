package com.sbrt.ponomarev.githubuser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ponomarev on 24.05.2017.
 */

class UserLoader extends AsyncTaskLoader<User> {

    private static final String URL_API_GITHUB_V3_USER = "https://api.github.com/users/";
    private String mLogin;
    private User mCachedUser;

    public UserLoader(Context context) {
        super(context);
    }

    public void setLogin(String mLogin) {
        this.mLogin = mLogin;
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(User data) {
        super.deliverResult(data);
        mCachedUser = data;
    }

    @Override
    public User loadInBackground() {
        String path = generatePath(mLogin);
        String infoStr = loadUserInfoFromGitHub(path);
        User user = UserParser.parseUserInfo(infoStr);
        return user;
    }

    @NonNull
    private String generatePath(String login) {
        if (login == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(URL_API_GITHUB_V3_USER)
                .append(login);
        return builder.toString();
    }

    private String loadUserInfoFromGitHub(String path) {

        HttpURLConnection urlConnection = null;
        String s = null;
        try {
            URL url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            s = readStream(in);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return s;
    }

    private String readStream(BufferedReader in) throws IOException {
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
