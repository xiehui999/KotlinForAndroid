package com.code4android.kotlinforandroid

/**
 *Created by Code4Android on 2017/5/8.
 */

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout.HORIZONTAL
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        LoginUi().setContentView(this@LoginActivity)
    }

    lateinit var et_account: EditText
    lateinit var et_password: EditText
    inner class LoginUi : AnkoComponent<LoginActivity> {
        override fun createView(ui: AnkoContext<LoginActivity>) = with(ui) {
            verticalLayout {
                backgroundColor = context.resources.getColor(android.R.color.white)
                gravity = Gravity.CENTER_HORIZONTAL
                imageView(R.mipmap.ic_launcher).lparams {
                    width = dip(100)
                    height = dip(100)
                    topMargin = dip(64)
                }

                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL
                    orientation = HORIZONTAL
                    backgroundResource = R.drawable.bg_frame_corner
                    imageView {
                        image = resources.getDrawable(R.mipmap.ic_username)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        leftMargin = dip(12)
                        rightMargin = dip(15)
                    }
                    et_account = editText {
                        hint = "登录账户"
                        hintTextColor = Color.parseColor("#666666")
                        textSize = 16f
                        background = null
                    }
                }.lparams(width = dip(300), height = dip(40)) {
                    topMargin = dip(45)
                }

                linearLayout {
                    orientation = HORIZONTAL
                    backgroundResource = R.drawable.bg_frame_corner
                    gravity = Gravity.CENTER_VERTICAL
                    imageView {
                        image = resources.getDrawable(R.mipmap.ic_password)
                    }.lparams {
                        leftMargin = dip(12)
                        rightMargin = dip(15)
                    }
                    et_password = editText {
                        hint = "登录密码"
                        hintTextColor = Color.parseColor("#666666")
                        textSize = 16f
                        background = null
                    }
                }.lparams {
                    width = dip(300)
                    height = dip(40)
                    topMargin = dip(10)

                }

                button("登录") {
                    gravity = Gravity.CENTER
                    background = resources.getDrawable(R.drawable.bg_login_btn)
                    textColor = Color.parseColor("#ffffff")
                    onClick {
                        if (et_account.text.toString().isNotEmpty() && et_password.text.toString().isNotEmpty())
                            startActivity<MainActivity>("account" to et_account.text.toString(),"password" to et_password.text.toString()) else toast("请输入账户或者密码")
                    }
                }.lparams(width = dip(300), height = dip(44)) {
                    topMargin = dip(18)
                }
                linearLayout {
                    orientation = HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                    checkBox("记住密码") {
                        textColor = Color.parseColor("#666666")
                        textSize = 16f
                        leftPadding = dip(5)
                    }
                    textView("隐私协议") {
                        textColor = Color.parseColor("#1783e3")
                        gravity = Gravity.RIGHT
                        textSize = 16f
                    }.lparams(width = matchParent)
                }.lparams(width = dip(300)) {
                    topMargin = dip(18)
                }

                textView("Copyright © Code4Android") {
                    textSize = 14f
                    gravity = Gravity.CENTER or Gravity.BOTTOM

                }.lparams {
                    bottomMargin = dip(35)
                    weight = 1f
                }
            }
        }
    }

    inner class UI : AnkoComponent<LoginActivity> {
        override fun createView(ui: AnkoContext<LoginActivity>): View {
            return with(ui) {
                verticalLayout {
                    val textView = textView("我是一个TextView") {
                        textSize = sp(17).toFloat()
                        textColor = context.resources.getColor(R.color.red)
                    }.lparams {
                        margin = dip(10)
                        height = dip(40)
                        width = matchParent
                    }
                    val name = editText("EditText")
                    button("Button") {
                        onClick { view ->
                            click()
                        }
                    }
                }
            }
        }
    }

    private fun click() {
/*        toast("Hello, ${name.text}!")
        alert("我是Dialog") {
            yesButton { toast("yes") }
            noButton { toast("no") }
        }.show()*/
        doAsync {
            Thread.sleep(3000)
            uiThread { toast("线程${Thread.currentThread().name}") }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
        }
    }
}

