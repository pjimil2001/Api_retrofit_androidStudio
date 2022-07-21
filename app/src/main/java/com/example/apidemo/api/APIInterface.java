package com.example.apidemo.api;

import com.example.apidemo.models.CommonResponse;
import com.example.apidemo.models.UserResponse;
import com.example.apidemo.models.bookDetails;
import com.example.apidemo.models.bookResponse;
import com.example.apidemo.models.cdResponse;
import com.example.apidemo.models.episoderesponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("all_genres")
    @Headers({"Accept: application/json"})
    Call<UserResponse> getUsers();


    @GET("all_episode")
    @Headers({"Accept: application/json"})
    Call<episoderesponse> getUser();

    @GET("all_cd")
    @Headers({"Accept: application/json"})
    Call<cdResponse> getUse();


    @GET("all_books")
    @Headers({"Accept: application/json"})
    Call<bookResponse> getUs();


//    @GET("posts")
//    Call<List<mo>> getpost();


    @GET("apiName/{id}")
    @Headers({"Accept: application/json"})
    Call<CommonResponse> apiName(@Path("id") String id);

//    @POST("apiName")
//    @Headers({"Accept: application/json"})
//    Call<CommonResponse> apiName(@Header("Authorization") String token, @Body CommonResponse request);


    @Multipart
    @POST("UploadProfilePic")
    Call<CommonResponse> uploadProfilePhoto(@PartMap Map<String, RequestBody> map);

}
