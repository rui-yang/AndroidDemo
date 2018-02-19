package com.mytaxi.android_demo;

/**
 * Created by ruiyang on 2/15/2018.
 */

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Login test
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    //public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private final String mUserName = "whiteelephant261";;
    private final String mPassword = "video";

    @Test
    public void testLoginSuccess() throws Exception {

        //Enter the username
        onView(withId(R.id.edt_username)).perform(typeText(mUserName));

        //Enter the password
        onView(withId(R.id.edt_password)).perform(typeText(mPassword));

        //Click on Login button
        onView(withId(R.id.btn_login)).perform(click());

        // Open the navigation drawer
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        //Verify the user is logged in successfully with the correct username
        onView(withId(R.id.nav_username)).check(matches(withText(mUserName)));

        //Close the navigation drawer
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen(Gravity.LEFT)))
                .perform(DrawerActions.close());
    }

}
