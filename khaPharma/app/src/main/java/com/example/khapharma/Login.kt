package com.example.khapharma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.khapharma.Gerant.Sharedfreflogin
import com.example.khapharma.Gerant.welcomGerant
import com.example.khapharma.models.LoginBody
import com.example.khapharma.models.LoginUser
import com.example.khapharma.pharmacien.welcomPharmacien
import com.example.khapharma.service.RetrofitFactory
import com.example.khapharma.service.SharedPref
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*


class Login : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    lateinit var emailView :TextInputEditText
    lateinit var passwordView: TextInputEditText
    lateinit var message: TextView
    lateinit var loginBtn:Button
    lateinit var progressBar: ProgressBar;
    lateinit var  oublier :TextView

//    var isLoad: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailView = findViewById(R.id.email)
        passwordView = findViewById(R.id.password)
        loginBtn=findViewById(R.id.button1)
        message=findViewById(R.id.message)
        oublier = findViewById(R.id.oublier)
        oublier.setOnClickListener{
            val intent = Intent(this, welcomGerant::class.java)
            startActivity(intent)
        }
        progressBar = findViewById(R.id.progress_circular)
        loginBtn.setOnClickListener{this.login()}
        message.visibility = View.GONE
        progressBar.visibility = View.GONE

//        val s = Sharedfreflogin()
//
//        if (s.getUserEmail(this@Login)!!.isNotEmpty()){
//            val email = s.getUserEmail(this@Login)
//           emailView.setText(email)
//
//        }

    }


    private  fun login(){
        val password = passwordView.text
        val email = emailView.text

        if (TextUtils.isEmpty(Objects.requireNonNull(email))){
            emailView.error="l'adresse mail est obligatoire"
            emailView.requestFocus()
        }
        else if (TextUtils.isEmpty(Objects.requireNonNull(password))){
            passwordView.error=" le password est  obligatoire"
            passwordView.requestFocus()
        }
        else{


                progressBar.visibility = View.VISIBLE
                loginBtn.visibility = View.GONE
                if(loginValidate(email.toString(), password.toString())){
                    message.visibility = View.GONE;
                    val service = RetrofitFactory.makeRetrofitService(this)
                    CoroutineScope(Dispatchers.IO).launch{
                        val response = service.login(LoginBody(email.toString(),password.toString()))
                        withContext(Dispatchers.Main){
                            try {
                                if(response.isSuccessful) {
                                    val  res: LoginUser? = response.body()
//                                    sauvegarde du token de l'utilisateur authentifier
                                    sessionManager = SessionManager(this@Login)
                                    res?.token?.let { sessionManager.saveAuthToken(it) }

                                    val  intent = Intent(this@Login,welcomPharmacien::class.java)
                                        val s = SharedPref()
                                        res?.let { s.saveUserDate(this@Login, it);
                                        startActivity(intent)
                                    }
                                }
                                else{
                                    message.visibility = View.VISIBLE;
                                    loginBtn.visibility = View.VISIBLE
                                    progressBar.visibility = View.GONE
                                }
                            }

                            catch (e: HttpException){
                                println("Exception ${e.message}")
                            }

                            catch (e: Throwable){
                                message.visibility = View.VISIBLE;
                                loginBtn.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
    }

    private fun loginValidate(email: String, password: String): Boolean {
    if(email.isEmpty() || password.isEmpty()){
        return false;
    }
    return true;
    }
}





