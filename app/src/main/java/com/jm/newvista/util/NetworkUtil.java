package com.jm.newvista.util;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkUtil {
    public static MyOkHttp myOkHttp = new MyOkHttp();// Aliyun server ip: 39.106.218.175
    public static final String SIGN_UP_URL = "http://johnnymiller.site/servlet.customer.SignUp";
    public static final String LOG_IN_URL = "http://johnnymiller.site/servlet.customer.LogIn";
    public static final String USER_INFO_URL = "http://johnnymiller.site/servlet.customer.GetUserInfo";
    public static final String GET_AVATAR_URL = "http://johnnymiller.site/getAvatar.jsp";
    public static final String SECONDARY_LOGON_URL = "http://johnnymiller.site/servlet.customer.SecondaryLogon";
    public static final String GET_MOVIE_URL = "http://johnnymiller.site/servlet.customer.GetMovie";
    public static final String GET_MOVIE_POSTER_URL = "http://johnnymiller.site/getMoviePoster.jsp";
    public static final String GET_TOP_MOVIE_POSTER_URL = "http://johnnymiller.site/getTopMoviePoster.jsp";
    public static final String USER_REVIEW_MANAGEMENT_URL = "http://johnnymiller.site/servlet.customer.UserReviewManagement";
    public static final String ORDER_URL = "http://johnnymiller.site/servlet.customer.Order";
    public static final String GET_TOP_MOVIE_URL = "http://johnnymiller.site/servlet.customer.GetTopMovie";
    public static final String GET_MOVIE_SCHEDULE_URL = "http://johnnymiller.site/servlet.customer.GetMovieSchedule";
    public static final String GET_THEATER_LOGO_URL = "http://johnnymiller.site/getTheaterLogo.jsp";
}
