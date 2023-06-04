package com.android.tutorials.recyclerviewRetrofitRx.api;

import com.android.tutorials.recyclerviewRetrofitRx.models.UserResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface ApiServices {
    @GET(ApiEndpoint.GET_USERS)
    Single<List<UserResponse>> getUsers();

    @GET(ApiEndpoint.GET_USERS)
    Single<List<UserResponse>> getUsers(@Query("page") int pageIndex);

    @GET(ApiEndpoint.GET_USER_DETAIL)
    Single<UserResponse> getUserDetail(@Path("userId") String userId);
}
