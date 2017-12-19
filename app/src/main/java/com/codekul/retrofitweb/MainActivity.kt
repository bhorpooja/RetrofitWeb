package com.codekul.retrofitweb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.codekul.retrofitweb.domain.OtpInfo
import com.codekul.retrofitweb.domain.User
import com.codekul.retrofitweb.domain.UserInfo
import kotlinx.android.synthetic.main.activity_main.*
import com.codekul.retrofitweb.rest.ApiService
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {


    lateinit var name:EditText
    lateinit var email:EditText
    lateinit var mobileNo:EditText
    lateinit var otpNo:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name =findViewById(R.id.edtName)
        email=findViewById(R.id.edtemail)
        mobileNo=findViewById(R.id.edtMobileNo)
        otpNo=findViewById(R.id.edtOtp)

        btnVerify.setOnClickListener { getOtp()  }
    }
    fun postUser(){
       val apiService = ApiService.create()
        val call = apiService.userRegister(User(name.text.toString(),
                                                email.text.toString(),
                                                mobileNo.text.toString(),
                                                otpNo.text.toString()))
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.i("@codekul ", "User Inserted Successfully " )
            }
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.i("@codekul", "error msg " + call + " thro " + t?.message)
            }
        })
    }

    fun getUser(){
        val apiService=ApiService.create()
        val call = apiService.getUser()
        call.enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(call: Call<List<UserInfo>>?, response: Response<List<UserInfo>>?) {
                val list: List<UserInfo>? = response?.body()
                Log.i("@Codekul",list.toString())
            }
            override fun onFailure(call: Call<List<UserInfo>>?, t: Throwable?) {
                Log.i("@codekul", "error msg " + call + " thro " + t?.message)
            }
        })
    }

    fun getOtp(){
        val apiService=ApiService.create()
        val otp:String=mobileNo.text.toString()
        val call = apiService.getOtp(otp)
        call.enqueue(object : Callback<OtpInfo> {
            override fun onResponse(call: Call<OtpInfo>?, response: Response<OtpInfo>?) {
                val otp: OtpInfo? = response?.body()
                Log.i("@Codekul",otp?.toString())
            }
            override fun onFailure(call: Call<OtpInfo>?, t: Throwable?) {
                Log.i("@codekul", "error msg " + call + " thro " + t?.message)
            }
        })
    }



}
