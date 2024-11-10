package com.mmd.mvcexample;

import android.os.Handler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(RobolectricTestRunner.class)
public class LoginApiTest {

    @Mock
    LoginResultInterface loginResultInterface;

    private LoginApi loginApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginApi = new LoginApi("testUser", "testPassword", loginResultInterface);
    }

    @Test
    public void testDoLogin_Success() {
        loginApi.doLogin();
        new Handler().postDelayed(() -> {
            verify(loginResultInterface, times(1)).onLoginResult(true, 0);
        }, 5000);
    }

    @Test
    public void testDoLogin_Failure() {
        loginApi = new LoginApi("", "", loginResultInterface);
        loginApi.doLogin();
        new Handler().postDelayed(() -> {
            verify(loginResultInterface, times(1)).onLoginResult(false, -1);
        }, 5000);
    }
}
