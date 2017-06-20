package com.sbrt.ponomarev.githubuser;

import com.sbrt.ponomarev.githubuser.utils.JsonFileRule;
import com.sbrt.ponomarev.githubuser.utils.TestNameRule;
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

    private JsonFileRule jsonFile = new JsonFileRule();
    private TestNameRule mTestNameRule = new TestNameRule();

    @Rule
    public TestRule rule = RuleChain
            .outerRule(jsonFile)
            .around(mTestNameRule);

    @Test
    public void testParseUserInfo() throws Exception {
        String jsonStr = jsonFile.getJsonStr();

        User expected = jsonFile.getUser();
        User actual = UserParser.parseUserInfo(jsonStr);

        assertThat(actual, is(expected));
    }

    @Test
    public void testParseUserInfoWithNullJsonString() throws Exception {

        User actual = UserParser.parseUserInfo(null);

        assertThat(actual, is(nullValue()));
    }
}
