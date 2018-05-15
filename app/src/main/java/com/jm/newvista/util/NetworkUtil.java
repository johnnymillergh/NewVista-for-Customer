package com.jm.newvista.util;

import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 1/21/2018.
 */

public class NetworkUtil {
    public static MyOkHttp myOkHttp = new MyOkHttp();// Aliyun server ip: 39.106.218.175
    private static final String BASE_URL = "http://140.143.144.123/";
    public static final String SIGN_UP_URL = BASE_URL + "servlet.customer.SignUp";
    public static final String LOG_IN_URL = BASE_URL + "servlet.customer.LogIn";
    public static final String USER_INFO_URL = BASE_URL + "servlet.customer.GetUserInfo";
    public static final String GET_AVATAR_URL = BASE_URL + "customer/getAvatar.jsp?id=";
    public static final String SECONDARY_LOGON_URL = BASE_URL + "servlet.customer.SecondaryLogon";
    public static final String GET_MOVIE_URL = BASE_URL + "servlet.customer.GetMovie";
    public static final String GET_MOVIE_POSTER_URL = BASE_URL + "customer/getMoviePoster.jsp?title=";
    public static final String
            GET_TOP_MOVIE_POSTER_URL = BASE_URL + "customer/getTopMoviePoster.jsp?movieTitle=";
    public static final String
            USER_REVIEW_MANAGEMENT_URL = BASE_URL + "servlet.customer.UserReviewManagement";
    public static final String ORDER_URL = BASE_URL + "servlet.customer.Order";
    public static final String GET_TOP_MOVIE_URL = BASE_URL + "servlet.customer.GetTopMovie";
    public static final String GET_MOVIE_SCHEDULE_URL = BASE_URL + "servlet.customer.GetMovieSchedule";
    public static final String GET_THEATER_LOGO_URL = BASE_URL + "customer/getTheaterLogo.jsp?id=";
    public static final String
            GET_LOWEST_PRICE_URL = BASE_URL + "customer/getLowestPrice.jsp?movieTitle=";
    public static final String GET_LOWEST_PRICE_URL2 = BASE_URL + "customer/getLowestPrice.jsp?movieId=";
    public static final String GET_NOW_IN_THEATERS_URL = BASE_URL + "customer/getNowInTheaters.jsp";
    public static final String SAT_MANAGEMENT_URL = BASE_URL + "servlet.customer.SATManagement";
    public static final String GET_TOP_RATED_URL = BASE_URL + "customer/getTopRated.jsp";
    public static final String GET_MOVIE_SCHEDULE_URL2 = BASE_URL + "customer/getMovieSchedule.jsp";
    public static final String GET_TOP_SELLING_URL = BASE_URL + "customer/getTopSelling.jsp";
    public static final String
            WATCHLIST_MANAGEMENT_URL = BASE_URL + "servlet.customer.WatchlistManagement";
    public static final String GET_GROSS_URL = BASE_URL + "customer/getGross.jsp";
}
