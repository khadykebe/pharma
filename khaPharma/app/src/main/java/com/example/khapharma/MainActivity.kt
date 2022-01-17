package com.example.khapharma


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.khapharma.pharmacien.welcomPharmacien


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val mIntent = Intent(this@MainActivity,Welcome::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)

    }
}






