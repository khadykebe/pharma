package com.example.khapharma.service

import android.app.Activity
import android.content.Context
import com.example.khapharma.models.LoginUser
import com.example.khapharma.models.User

class SharedPref() {
    val SHARED_NAME = "PHARMA_SHARED_NAME";
    val PRENOM = "prenom";
    val NOM = "nom";
    val EMAIL = "email";
    val TOKEN = "token";
    val TELEPHONE = "telephone";
    val ROLE = "role"
    val PHARMACIEN_ROLE = "pharmacien";
    val VENDEUR_ROLE = "vendeur";
    val GERANT_ROLE = "gérant";

    fun saveUserDate(activity: Activity, response: LoginUser){
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit();

        editor.putString(PRENOM, response.user.prenom);
        editor.putString(TOKEN, response.token);
        editor.putString(NOM, response.user.nom);
        editor.putString(ROLE, response.user.roles);
        editor.putString(EMAIL, response.user.email);
        editor.putString(TELEPHONE, response.user.telephone);

        editor.apply()
    }

    fun getUserToken(activity: Activity): String?{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN, null);
    }

    fun getUserFullName(activity: Activity): String {
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        var fullName = "";
        sharedPref.getString(PRENOM, null)?.let { fullName = it }
        sharedPref.getString(NOM, null)?.let { fullName += " $it" }
        return fullName
    }

    fun getUser(activity:Activity):String?{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(EMAIL, null)
    }
//
//    fun saveUserRoles(activity: Activity): String? {
//        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
//        return sharedPref.getString(ROLE, null)
//
//    }

    fun getUserRoles(activity: Activity): String?{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(ROLE, null)
    }

    fun isAdmin(activity: Activity): Boolean{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        if(sharedPref.contains(PHARMACIEN_ROLE)){
            return false;
        }
        return true;
    }

    fun isVendeur(activity: Activity): Boolean {
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        if(sharedPref.contains(VENDEUR_ROLE)){
            return true;
        }
        return false;
    }

    fun isGerant(activity: Activity): Boolean{
        val sharedPref = activity.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        if(sharedPref.contains(GERANT_ROLE)){
            return true;
        }
        return false;
    }
}