package com.codekul.retrofitweb.domain


/**
 * Created by pooja on 6/12/17.
 */
data class User(val name: String,
                val email: String,
                val mobileNo: String,
                val otpNo: String)

data class UserInfo (val id:String,
                     val name:String,
                     val email:String,
                     val mobileNo:String,
                     val otpNo:String)

data class OtpInfo(val MobileNo:String,
                   val otp:String)