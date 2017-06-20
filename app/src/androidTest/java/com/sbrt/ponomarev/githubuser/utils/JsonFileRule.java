package com.sbrt.ponomarev.githubuser.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;
import com.sbrt.ponomarev.githubuser.test.R;
import org.junit.rules.ExternalResource;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Ponomarev on 20.06.2017.
 */

public class JsonFileRule extends ExternalResource {

    private static final String TAG = JsonFileRule.class.getSimpleName();

    private Context mContext = InstrumentationRegistry.getContext();
    private String mJsonStr;

    @Override
    protected void before() throws Throwable {
        super.before();

        Scanner scanner = null;
        try {
            InputStream in = mContext.getResources().openRawResource(R.raw.user);
            scanner = new Scanner(in).useDelimiter("\\A");
            mJsonStr = scanner.hasNext() ? scanner.next() : null;

            Log.e(TAG, "JSON prepared string: " + mJsonStr);

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public String getJsonStr() {
        return mJsonStr;
    }
}
