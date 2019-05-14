package com.tramalho.labs.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tramalho.labs.R
import kotlinx.android.synthetic.main.activity_tweeter_list.*

class TweeterListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweeter_list)
        setSupportActionBar(toolbar)
    }
}
