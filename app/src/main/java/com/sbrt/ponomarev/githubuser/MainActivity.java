package com.sbrt.ponomarev.githubuser;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private static final int LOADER_ID = 10;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mUserIDTextView;
    private TextView mLocationTextView;
    private ImageView mInfoNotFoundImageView;
    private LinearLayout mInfoLayout;
    private Button mSearchButton;
    private EditText mLoginEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = (TextView) findViewById(R.id.name);
        mEmailTextView = (TextView) findViewById(R.id.email);
        mUserIDTextView = (TextView) findViewById(R.id.user_id);
        mLocationTextView = (TextView) findViewById(R.id.location);
        mInfoNotFoundImageView = (ImageView) findViewById(R.id.info_not_found);
        mInfoLayout = (LinearLayout) findViewById(R.id.info_layout);
        mLoginEditText = (EditText) findViewById(R.id.login);
        mSearchButton = (Button) findViewById(R.id.search);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = mLoginEditText.getText().toString().trim();
                Loader<User> loader = getSupportLoaderManager().getLoader(LOADER_ID);
                if (loader instanceof UserLoader) {
                    ((UserLoader) loader).setLogin(login);
                }
            }
        });

        getSupportLoaderManager().initLoader(LOADER_ID, null, new UserLoaderCallbacks());

        showUserInfo(false);
    }

    private void showUserInfo(boolean show) {
        mInfoNotFoundImageView.setVisibility(!show ? View.VISIBLE : View.GONE);
        mInfoLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private class UserLoaderCallbacks implements android.support.v4.app.LoaderManager.LoaderCallbacks<User> {
        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            return new UserLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User user) {

            if (user != null) {
                mNameTextView.setText(getString(R.string.name, user.getName()));
                mEmailTextView.setText(getString(R.string.email, user.getEmail()));
                mUserIDTextView.setText(getString(R.string.user_id, user.getID()));
                mLocationTextView.setText(getString(R.string.location, user.getLocation()));

                showUserInfo(true);
            } else {
                showUserInfo(false);
            }
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {

        }
    }
}
