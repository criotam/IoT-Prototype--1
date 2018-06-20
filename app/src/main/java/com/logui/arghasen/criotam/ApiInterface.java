package com.logui.arghasen.criotam;

/**
 * Created by srikanth on 21/1/17.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("/IOTGateway/rest/GatewayController/network")
    Call<String> postNetworkDetails(@Field("ssid") String ssid, @Field("password") String password);

    @GET("/fetchPlayerList")
    Call<List<PlayerPOJO>> getPlayerList();

    @GET("/fetchFiles")
    Call<List<FilesPOJO>> getFileList(@Query("player_id") String player_id,
                                      @Query("exp") String exp, @Query("parameter") String param);


}