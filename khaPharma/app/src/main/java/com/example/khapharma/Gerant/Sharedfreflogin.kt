package com.example.khapharma.Gerant

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.example.khapharma.models.LoginUser
import com.example.khapharma.models.User

class Sharedfreflogin {
    val SHARED_NAME = "NEWLOGIN"
    val EMAIL = "email"

    @SuppressLint("CommitPrefEdits")
    fun saveUserDate(activity: Activity, response: User){
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(EMAIL, response.email);

    }
    fun getUserEmail(activity: Activity): String?{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(EMAIL, null);
    }
}