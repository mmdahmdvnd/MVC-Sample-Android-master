package com.mmd.mvcexample;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserModelTest {

    private UserModel userModel;

    @Before
    public void setUp() {
        userModel = new UserModel("testUser", "testPassword");
    }

    @Test
    public void testCheckUserValidity_ValidCredentials() {
        int result = userModel.checkUserValidity("testUser", "testPassword");
        assertEquals(0, result);  // انتظار داریم نتیجه 0 باشد که نشان‌دهنده اعتبار درست است.
    }

    @Test
    public void testCheckUserValidity_InvalidCredentials() {
        int result = userModel.checkUserValidity("", "");
        assertEquals(-1, result);  // انتظار داریم نتیجه -1 باشد که نشان‌دهنده ورود نادرست است.
    }
}
