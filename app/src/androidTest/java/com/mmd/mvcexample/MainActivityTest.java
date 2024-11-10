package com.mmd.mvcexample;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

import android.view.View;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testLoginSuccess() throws InterruptedException {
        onView(withId(R.id.et_name)).perform(typeText("testUser"));
        onView(withId(R.id.et_password)).perform(typeText("testPassword"));
        onView(withId(R.id.bt_submit)).perform(click());

//        // افزودن تاخیر قبل از بررسی Toast
//        Thread.sleep(2000); // 2 ثانیه صبر
        // گرفتن View اصلی Activity
        View decorView = activityRule.getActivity().getWindow().getDecorView();

        // بررسی پیام Toast
        onView(withText("Login Successful"))
                .inRoot(withDecorView(not(decorView)))  // این تضمین می‌کند که Toast نمایش داده شده است و جزوی از View اصلی نیست
                .check(matches(new ToastMatcher("Login Successful")));
    }

    @Test
    public void testLoginFailure() {
        onView(withId(R.id.et_name)).perform(typeText(""));
        onView(withId(R.id.et_password)).perform(typeText(""));
        onView(withId(R.id.bt_submit)).perform(click());

        onView(withText("Login Fail")).check(matches(isDisplayed()));
    }
}
