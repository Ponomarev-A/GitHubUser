package com.sbrt.ponomarev.githubuser;

import com.sbrt.ponomarev.githubuser.utils.JsonFileRule;
import com.sbrt.ponomarev.githubuser.utils.TestNameRule;
import com.sbrt.ponomarev.githubuser.utils.UserRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ponomarev on 19.06.2017.
 */

public class UserParserTest {

    private JsonFileRule mJsonFile = new JsonFileRule();
    private TestNameRule mTestNameRule = new TestNameRule();
    private UserRule mUserRule = new UserRule();

    @Rule
    public TestRule rule = RuleChain
            .outerRule(mJsonFile)
            .around(mUserRule)
            .around(mTestNameRule);

    @Test
    public void testParseUserInfo() throws Exception {
        String jsonStr = mJsonFile.getJsonStr();

        User expected = mUserRule.getUser();
        User actual = UserParser.parseUserInfo(jsonStr);

        assertThat(actual, is(expected));
    }

    @Test
    public void testParseUserInfoWithNullJsonString() throws Exception {

        User actual = UserParser.parseUserInfo(null);

        assertThat(actual, is(nullValue()));
    }
}
