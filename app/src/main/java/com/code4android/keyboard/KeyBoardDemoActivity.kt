package com.code4android.keyboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent

import com.code4android.kotlinforandroid.R
import kotlinx.android.synthetic.main.activity_key_board_demo.*

class KeyBoardDemoActivity : AppCompatActivity() {
    var keyboardUtli: KeyBoardUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_board_demo)
        setSupportActionBar(toolbar)
        keyboardUtli = KeyBoardUtil(this@KeyBoardDemoActivity)

        et_keyboard.setOnTouchListener { v, event ->
            keyboardUtli?.attachTo(et_keyboard)
            false
        }
        et_keyboard2.setOnTouchListener { v, event ->
            keyboardUtli?.attachTo(et_keyboard2)
            false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            var b = keyboardUtli?.hideSoftKeyboard() ?: false
            return if (b) true else super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event)
    }
}
