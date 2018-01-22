package com.jm.newvista.util;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkUtil {
    public static MyOkHttp myOkHttp = new MyOkHttp();
    public static final String SIGN_UP_URL = "8080:/servlet.customer.SignUp";
    public static final String LOG_IN_URL= "8080:/servlet.customer.LogIn";
    public static final String GET_MOVIE_URL= "8080:/servlet.customer.GetMovie";
    public static final String GET_USER_REVIEW_URL= "8080:/servlet.customer.UserReviewManagement";
    public static final String ORDER_URL= "8080:/servlet.customer.Order";
}
