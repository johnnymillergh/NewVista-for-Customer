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
    public static final String GET_AVATAR_URL = "http://johnnymiller.site/customer/getAvatar.jsp?id=";
    public static final String SECONDARY_LOGON_URL = "http://johnnymiller.site/servlet.customer.SecondaryLogon";
    public static final String GET_MOVIE_URL = "http://johnnymiller.site/servlet.customer.GetMovie";
    public static final String GET_MOVIE_POSTER_URL = "http://johnnymiller.site/customer/getMoviePoster.jsp?title=";
    public static final String
            GET_TOP_MOVIE_POSTER_URL = "http://johnnymiller.site/customer/getTopMoviePoster.jsp?movieTitle=";
    public static final String
            USER_REVIEW_MANAGEMENT_URL = "http://johnnymiller.site/servlet.customer.UserReviewManagement";
    public static final String ORDER_URL = "http://johnnymiller.site/servlet.customer.Order";
    public static final String GET_TOP_MOVIE_URL = "http://johnnymiller.site/servlet.customer.GetTopMovie";
    public static final String GET_MOVIE_SCHEDULE_URL = "http://johnnymiller.site/servlet.customer.GetMovieSchedule";
    public static final String GET_THEATER_LOGO_URL = "http://johnnymiller.site/customer/getTheaterLogo.jsp?id=";
    public static final String
            GET_LOWEST_PRICE_URL = "http://johnnymiller.site/customer/getLowestPrice.jsp?movieTitle=";
    public static final String GET_LOWEST_PRICE_URL2 = "http://johnnymiller.site/customer/getLowestPrice.jsp?movieId=";
    public static final String GET_NOW_IN_THEATERS_URL = "http://johnnymiller.site/customer/getNowInTheaters.jsp";
    public static final String SAT_MANAGEMENT_URL = "http://johnnymiller.site/servlet.customer.SATManagement";
    public static final String GET_TOP_RATED_URL = "http://johnnymiller.site/customer/getTopRated.jsp";
    public static final String GET_MOVIE_SCHEDULE_URL2 = "http://johnnymiller.site/customer/getMovieSchedule.jsp";
}
