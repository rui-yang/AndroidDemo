package com.mytaxi.android_demo;

/**
 * Created by ruiyang on 2/15/2018.
 */

import android.support.test.espresso.Root;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Search Driver Name
 */
@RunWith(AndroidJUnit4.class)
public class SearchTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String mStringToSearch = "sa";
    private final String mStringExpected = "Sarah Friedrich";

    @Test
    public void testSearchDriver() throws Exception{

        //search the string
        onView(withId(R.id.textSearch)).perform(typeText(mStringToSearch));
        Thread.sleep(1000);

        //select the driver name
        Matcher<Root> rootMatcher = withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()));
        onView(withText(mStringExpected)).inRoot(rootMatcher).perform(scrollTo(), click());

        //check the details of driver's profile
        onView(withId(R.id.textViewDriverName)).check(matches(withText(mStringExpected)));

        //click call button in DriverProfileActivity
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click());
    }
}
