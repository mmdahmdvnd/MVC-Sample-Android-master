package com.mmd.mvcexample;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<View> {
    private String expectedText = "";

    public ToastMatcher(String loginSuccessful) {
        this.expectedText = expectedText;
    }

    @Override
    protected boolean matchesSafely(View view) {
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString().equals(expectedText);
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with text: " + expectedText);
    }
}