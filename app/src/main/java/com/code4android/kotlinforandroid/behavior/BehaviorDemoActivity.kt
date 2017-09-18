package com.code4android.kotlinforandroid.behavior

import android.os.Bundle
import android.app.Activity
import android.support.v4.view.ViewCompat
import com.code4android.kotlinforandroid.R
import kotlinx.android.synthetic.main.activity_behavior_demo.*

class BehaviorDemoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_demo)
        depentent.setOnClickListener {
            ViewCompat.offsetTopAndBottom(it, 10)
        }
    }
}
