package com.sbrt.ponomarev.githubuser;

import android.test.LoaderTestCase;
import com.sbrt.ponomarev.githubuser.utils.JsonFileRule;
import com.sbrt.ponomarev.githubuser.utils.TestNameRule;
import com.sbrt.ponomarev.githubuser.utils.UserRule;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

/**
 * Created by Ponomarev on 19.06.2017.
 */

public class UserLoaderTest extends LoaderTestCase {

    private static final String LOGIN = "Ivanov-I";

    @Mock
    private UserLoader mLoader;

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    private UserRule mUserRule = new UserRule();
    private TestNameRule mTestNameRule = new TestNameRule();
    private JsonFileRule mJsonFileRule = new JsonFileRule();

    @Rule
    public TestRule mRule = RuleChain
            .outerRule(mUserRule)
            .around(mJsonFileRule)
            .around(mTestNameRule);

    @Before
    public void setUp() throws Exception {
        mLoader = mock(UserLoader.class);
    }

    @Test
    public void testSetLogin() throws Exception {
        doCallRealMethod().when(mLoader).setLogin(LOGIN);

        mLoader.setLogin(LOGIN);

        verify(mLoader).onContentChanged();
    }

    @Test
    public void testOnStartLoading() throws Exception {
        doCallRealMethod().when(mLoader).onStartLoading();

        // Start loading at first time
        mLoader.onStartLoading();

        verify(mLoader, never()).deliverResult(mUserRule.getUser());
        verify(mLoader).forceLoad();
    }

    @Test
    public void testDeliveryResult() throws Exception {
        doCallRealMethod().when(mLoader).deliverResult(mUserRule.getUser());

        mLoader.deliverResult(mUserRule.getUser());

        verify(mLoader).isReset();
        verify(mLoader).isStarted();
    }

    @Test
    @Ignore
    public void testLoadInBackground() throws Exception {
        // TODO: How test async load?!
    }
}
