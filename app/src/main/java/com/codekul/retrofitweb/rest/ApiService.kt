package com.codekul.retrofitweb.rest

import com.codekul.retrofitweb.domain.OtpInfo
import com.codekul.retrofitweb.domain.User
import com.codekul.retrofitweb.domain.UserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by pooja on 6/12/17.
 */
interface ApiService {


    @Headers("Accept: application/json", "Content-Type: application/json")

    @POST("RegisterUser")
    fun userRegister(@Body user: User): Call<ResponseBody>

    @GET("getUser")
    fun getUser():Call<List<UserInfo>>

    @GET("getOtp/{mobileNo}")
    fun getOtp(@Path("mobileNo") mobileNo:String):Call<OtpInfo>

    companion object {

        private val PROTOCOL="http"
        private val SERVER="192.168.43.53"
        private val PORT="8080"
        private val APP_NAME="User"
        val BASE_URL="$PROTOCOL://$SERVER:$PORT/$APP_NAME/"

        fun create():ApiService{
            val retrofit=Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}