package com.jm.newvista.util;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkUtil {
    public static MyOkHttp myOkHttp = new MyOkHttp();// Aliyun server ip: 39.106.218.175
    public static final String SIGN_UP_URL = "http://39.106.218.175/servlet.customer.SignUp";
    public static final String LOG_IN_URL = "http://39.106.218.175/servlet.customer.LogIn";
    public static final String USER_INFO_URL = "http://39.106.218.175/servlet.customer.GetUserInfo";
    public static final String SECONDARY_LOGON_URL = "http://39.106.218.175/servlet.customer.SecondaryLogon";
    public static final String GET_MOVIE_URL = "http://39.106.218.175/servlet.customer.GetMovie";
    public static final String GET_USER_REVIEW_URL = "http://39.106.218.175/servlet.customer.UserReviewManagement";
    public static final String ORDER_URL = "http://39.106.218.175/servlet.customer.Order";
    public static final String GET_TOP_MOVIE_URL = "http://39.106.218.175/servlet.customer.GetTopMovie";
}
