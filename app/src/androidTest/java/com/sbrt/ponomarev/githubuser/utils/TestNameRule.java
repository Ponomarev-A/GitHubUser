package com.sbrt.ponomarev.githubuser.utils;

import android.util.Log;
import org.junit.rules.TestName;
import org.junit.runner.Description;

public class TestNameRule extends TestName {

    @Override
    protected void starting(Description d) {
        Log.e(d.getTestClass().getSimpleName(), "Test name: " + d.getMethodName());
    }
}