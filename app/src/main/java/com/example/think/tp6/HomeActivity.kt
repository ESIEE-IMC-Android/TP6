package com.example.think.tp6

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.think.tp6.essai.cnam.ListeStationVelibActivity
import com.example.think.tp6.essai.cnam.ListeStationVelibActivity1
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        list_ad.setOnClickListener {
            val intent = Intent(this@HomeActivity,ListeStationVelibActivity1::class.java)
            startActivity(intent)
        }
        list_ca.setOnClickListener {
            val intent = Intent(this@HomeActivity, ListeStationVelibActivity::class.java)
            startActivity(intent)
        }
    }
}
