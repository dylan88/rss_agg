package com.example.wautel_l.rss_reader_android;

import android.util.Log;

import org.junit.Test;

/**
 * Created by wautel_l on 23/02/2018.
 */

public class LoginActivityUnitTest {
    @Test
    public void test_number()
    {
        LoginActivity.recupValidNumber("48         ");
        System.out.println(  LoginActivity.recupValidNumber("48         "));
    }
}
