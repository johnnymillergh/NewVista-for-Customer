package com.jm.newvista.util;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkUtil {
    public static MyOkHttp myOkHttp = new MyOkHttp();
    public static final String SIGN_UP_URL = "/servlet.customer.SignUp";
    public static final String LOG_IN_URL= "/servlet.customer.LogIn";
    public static final String GET_MOVIE_URL= "/servlet.customer.GetMovie";
    public static final String GET_USER_REVIEW_URL= "/servlet.customer.UserReviewManagement";
    public static final String ORDER_URL= "/servlet.customer.Order";
    public static final String GET_TOP_MOVIE_URL= "/servlet.customer.GetTopMovie";
}
