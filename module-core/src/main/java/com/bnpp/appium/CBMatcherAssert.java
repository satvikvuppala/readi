package com.bnpp.appium;

import com.bnpp.appium.utils.Utils;
import io.cucumber.java.Scenario;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

/**
 * This is wrapper for {@link org.hamcrest.MatcherAssert} class as a
 * convenience for taking screenshot, in case the test is a fail
 */
public class CBMatcherAssert {
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat("", actual, matcher);
    }

    public static <T> void assertThat(T actual, Matcher<? super T> matcher, Scenario scenario) {
        try {
            MatcherAssert.assertThat("", actual, matcher);
        } catch (AssertionError ex) {
            embed(scenario, ex);
        }
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        assertThat(reason, actual, matcher, null);
    }

    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher, Scenario scenario) {
        try {
            MatcherAssert.assertThat(reason, actual, matcher);
        } catch (AssertionError error) {
            embed(scenario, error);
        }
    }

    public static void assertThat(String reason, boolean assertion) {
        MatcherAssert.assertThat(reason, assertion);
    }

    public static void assertThat(String reason, boolean assertion, Scenario scenario) {
        try {
            MatcherAssert.assertThat(reason, assertion);
        } catch (AssertionError ex) {
            embed(scenario, ex);
        }

    }

    private static void embed(Scenario scenario, AssertionError error) {
        if (scenario != null) {
            Utils.takeAScreenshot(scenario);
        }

        throw error;
    }
}
